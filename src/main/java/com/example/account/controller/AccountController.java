package com.example.account.controller;

import com.example.account.controller.dto.AccountEkleDTO;
import com.example.account.controller.dto.BorsaKagidiAlDTO;
import com.example.account.controller.dto.GoruntuleAccountDTO;
import com.example.account.controller.dto.ParaAktarDTO;
import com.example.account.entity.Account;
import com.example.account.mapper.AccacuntEkleDTOMapper;
import com.example.account.mapper.GoruntuleAllAccountMapper;
import com.example.account.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/getirTumAccountlar")
    public List<GoruntuleAccountDTO> getAllAccountList(){
        List<Account> accountList = accountService.getAllAccountList();
        return GoruntuleAllAccountMapper.INSTANCE.toGoruntuleAllAccountDTOList(accountList);
    }

    @PostMapping("/createAccount")
    public void createAccount(@RequestBody AccountEkleDTO accountEkleDTO){
         accountService.createAccount(AccacuntEkleDTOMapper.INSTANCE.toAccount(accountEkleDTO));
    }

    @PostMapping("/hesabaParaAktar")
    public void accountaparaAktar(@Valid @RequestBody ParaAktarDTO paraAktarDTO){
        accountService.paraAktar(paraAktarDTO);
    }

    @PostMapping("/borsaKagidiAl")
    public void borsaKagidiAl(@Valid @RequestBody BorsaKagidiAlDTO borsaKagidiAlDTO){
        accountService.borsaKagidiAl(borsaKagidiAlDTO);
    }

}
