package com.example.store.storeApi.web.controller;

import com.example.store.storeApi.domain.event.*;
import com.example.store.storeApi.domain.service.*;
import com.example.store.storeApi.persistence.data.*;
import com.example.store.storeApi.persistence.entity.*;
import com.example.store.storeApi.persistence.repository.*;
import com.example.store.storeApi.web.config.exception.*;
import com.example.store.storeApi.web.response.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private UserDeviceService userDeviceService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @GetMapping("/me")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserProfile> getUserProfile(@RequestParam(value = "email", required = false) Optional<String> email) {
        List<UserProfile> userProfiles = new ArrayList<>();
        if (email.isPresent()) {
            User user = userRepository.findByEmail(email.get())
                    .orElseThrow(() -> new ResourceNotFoundException("User", "email", email.get()));
            UserProfile userProfile = new UserProfile(user.getId(), user.getEmail(), user.getName(),  user.getActive());
            userProfiles.add(userProfile);
        } else {
            List<User> users = userRepository.findAll();
            for (User u: users) {
                UserProfile userProfile = new UserProfile(u.getId(), u.getEmail(), u.getName(),  u.getActive());
                userProfiles.add(userProfile);
            }
        }
        return userProfiles;
    }

    @GetMapping("/byID/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserProfile getUserProfileById(@PathVariable(value = "id") Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        UserProfile userProfile = new UserProfile(user.getId(), user.getEmail(), user.getName(), user.getActive());

        return userProfile;
    }

    @PutMapping("/byID/{id}/deactivate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> deactivateUserById(@PathVariable(value = "id") Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        user.deactivate();
        userRepository.save(user);
        return ResponseEntity.ok(new ApiResponse(true, "User deactivated successfully!"));
    }

    @PutMapping("/byID/{id}/activate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> activateUserById(@PathVariable(value = "id") Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        user.activate();
        userRepository.save(user);
        return ResponseEntity.ok(new ApiResponse(true, "User activated successfully!"));
    }

    @DeleteMapping("/byID/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable(value = "id") Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        userRepository.delete(user);
        return ResponseEntity.ok(new ApiResponse(true, "User deleted successfully!"));
    }

    @PutMapping("/logout")
    public ResponseEntity<ApiResponse> logoutUser(@CurrentUser UserPrincipal currentUser,
                                                  @RequestBody LogOutRequest logOutRequest) {
        String deviceId = logOutRequest.getDeviceInfo().getDeviceId();
        UserDevice userDevice = userDeviceService.findByUserId(currentUser.getId())
                .filter(device -> device.getDeviceId().equals(deviceId))
                .orElseThrow(() -> new UserLogoutException(logOutRequest.getDeviceInfo().getDeviceId(), "Invalid device Id supplied. No matching device found for the given user "));
        refreshTokenService.deleteById(userDevice.getRefreshToken().getId());

        OnUserLogoutSuccessEvent logoutSuccessEvent = new OnUserLogoutSuccessEvent(currentUser.getEmail(), logOutRequest.getToken(), logOutRequest);
        applicationEventPublisher.publishEvent(logoutSuccessEvent);
        return ResponseEntity.ok(new ApiResponse(true, "User has successfully logged out from the system!"));
    }

}