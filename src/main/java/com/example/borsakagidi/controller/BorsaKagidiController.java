package com.example.borsakagidi.controller;


import com.example.borsakagidi.controller.dto.BorsaKagidiEkleDTO;
import com.example.borsakagidi.controller.dto.GoruntuleBorsaKagidiDTO;
import com.example.borsakagidi.controller.dto.GoruntuleKulanicininBorsaKagitlariiResponseDTO;
import com.example.borsakagidi.entity.BorsaKagidi;
import com.example.borsakagidi.mapper.BorsaKagidiEkleDTOMapper;
import com.example.borsakagidi.mapper.GoruntuleBorsaKagidiMapper;
import com.example.borsakagidi.service.BorsaKagidiService;
import com.example.user.entity.User;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borsaKagidi")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BorsaKagidiController {
    private final BorsaKagidiService borsaKagidiService;

    public BorsaKagidiController(BorsaKagidiService borsaKagidiService) {
        this.borsaKagidiService = borsaKagidiService;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/getirTumBorsaKagitlari")
    public List<GoruntuleBorsaKagidiDTO> getAllAccountList(){
        List<BorsaKagidi> accountList = borsaKagidiService.getAllBorsaKagidiList();
        return GoruntuleBorsaKagidiMapper.INSTANCE.toGoruntuleBorsaKagidiDTOList(accountList);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/createBorsaKagidi")
    public void createAccount(@Valid @RequestBody BorsaKagidiEkleDTO borsaKagidiEkleDTO){
        borsaKagidiService.createBorsaKagidi(BorsaKagidiEkleDTOMapper.INSTANCE.toBorsaKagidi(borsaKagidiEkleDTO));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/getirAccountBorsaKagidi")
    public GoruntuleKulanicininBorsaKagitlariiResponseDTO getirKullaniciyaAitBorsaKagitlari(){
        Object response = SecurityContextHolder.getContext().getAuthentication().getDetails();
        User user = (User) response;

        return borsaKagidiService.getirKullaniciyaAitBorsaKagitlari(user);
    }
}
