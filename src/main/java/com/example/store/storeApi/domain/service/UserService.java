package com.example.store.storeApi.domain.service;

import com.example.store.storeApi.persistence.entity.*;
import com.example.store.storeApi.persistence.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class UserService
{
    @Autowired
    UserRepository userRepository;


}
