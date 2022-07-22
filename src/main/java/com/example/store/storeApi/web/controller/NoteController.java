package com.example.store.storeApi.web.controller;

import com.example.store.storeApi.domain.service.*;
import com.example.store.storeApi.persistence.entity.*;
import com.example.store.storeApi.persistence.repository.*;
import com.example.store.storeApi.web.security.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.awt.print.*;
import java.io.*;
import java.util.*;
import java.util.stream.*;

@RestController
@RequestMapping("/api/notes")
public class NoteController {
    @Autowired
    private NoteService noteSerivce;

    @Autowired
    private JwtProvider tokenProvider;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    protected String getJwt(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.replace("Bearer ", "");
        }

        return null;
    }



    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public List<Note> findAll(HttpServletRequest request){
        String jwt = getJwt(request);

        int userId = Integer.parseInt(tokenProvider.getUserIdFromJwtToken(jwt));
        List<Note> notes = noteSerivce.findByUserId(userId);


        return notes;
    }



}
