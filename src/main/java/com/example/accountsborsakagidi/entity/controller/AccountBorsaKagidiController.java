package com.example.accountsborsakagidi.entity.controller;

import com.example.accountsborsakagidi.entity.AccountsBorsaKagidi;
import com.example.accountsborsakagidi.entity.service.AccountsBorsaKagidiService;
import com.example.borsakagidi.entity.BorsaKagidi;
import com.example.user.entity.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/accountborsakagidi")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AccountBorsaKagidiController {

    private final AccountsBorsaKagidiService accountsBorsaKagidiService;

    public AccountBorsaKagidiController(AccountsBorsaKagidiService accountsBorsaKagidiService) {
        this.accountsBorsaKagidiService = accountsBorsaKagidiService;
    }
}
