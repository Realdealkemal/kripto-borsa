package com.example.account.service;

import com.example.account.controller.dto.AccountEkleDTO;
import com.example.account.controller.dto.BorsaKagidiAlDTO;
import com.example.account.controller.dto.ParaAktarDTO;
import com.example.account.entity.Account;
import com.example.account.repository.AccountRepository;
import com.example.account.service.checker.AccountServiceChecker;
import com.example.accountsborsakagidi.entity.AccountsBorsaKagidi;
import com.example.accountsborsakagidi.entity.service.AccountsBorsaKagidiService;
import com.example.borsakagidi.entity.BorsaKagidi;
import com.example.borsakagidi.service.BorsaKagidiService;
import com.example.borsakagidi.service.BorsaKagidiServiceChecker;
import com.example.user.entity.User;
import jakarta.transaction.Transactional;
import lombok.Synchronized;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountServiceChecker accountServiceChecker;
    private final BorsaKagidiService borsaKagidiService;
    private final BorsaKagidiServiceChecker borsaKagidiServiceChecker;
    private final AccountsBorsaKagidiService accountsBorsaKagidiService;

    public AccountService(AccountRepository accountRepository, AccountServiceChecker accountServiceChecker, BorsaKagidiService borsaKagidiService, BorsaKagidiServiceChecker borsaKagidiServiceChecker, AccountsBorsaKagidiService accountsBorsaKagidiService) {
        this.accountRepository = accountRepository;
        this.accountServiceChecker = accountServiceChecker;
        this.borsaKagidiService = borsaKagidiService;
        this.borsaKagidiServiceChecker = borsaKagidiServiceChecker;
        this.accountsBorsaKagidiService = accountsBorsaKagidiService;
    }

    public List<Account> getAllAccountList() {
        return accountRepository.findAll();
    }

    public void createAccount(Account account, User user) {
        if (accountServiceChecker.isAccountExits(account)
                && accountServiceChecker.isUseraAccountEklenebilir(user)) {
            yeniAccountBilgileriDoldur(account, user);
            accountRepository.save(account);
        }
    }

    private void yeniAccountBilgileriDoldur(Account account, User user) {
        account.setBudget(new BigDecimal(0.0));
        account.setUser(user);
        user.setAccount(account);
    }

    public void paraAktar(ParaAktarDTO paraAktarDTO) {
        Account aktarilacakHesapVt = accountRepository.findById(paraAktarDTO.getAktarilacakHesapId()).orElseThrow();
        Account nerdenAktarimYapilacakHesap = null;
        nerdenAktarimYapilacakHesap = Objects.nonNull(paraAktarDTO.getNerdenAktarimYapilacakHesapAdi()) ? accountRepository.findById(paraAktarDTO.getNerdenAktarimYapilacakHesapAdi()).orElse(null) : null;
        if (Objects.isNull(nerdenAktarimYapilacakHesap)) {
            aktarilacakHesapVt.setBudget(paraAktarDTO.getAktarilacakTutar());
        }
        accountRepository.save(aktarilacakHesapVt);
    }

    @Transactional
    @Synchronized
    public void borsaKagidiAl(BorsaKagidiAlDTO borsaKagidiAlDTO) {
        Account alimYapanHesapVt = accountRepository.findById(borsaKagidiAlDTO.getIslemYapanAccoundId()).orElseThrow();
        BorsaKagidi borsaKagidi = borsaKagidiService.getirBorsaKagidiById(borsaKagidiAlDTO.getIslemYapilanBorsaKagidiId()).orElseThrow();
        if(accountServiceChecker.hesaptaYetericeParavarMi(alimYapanHesapVt, borsaKagidi, borsaKagidiAlDTO.getAlimSayisi())
        && borsaKagidiServiceChecker.isYeterliStockVar(borsaKagidi.getStock(), borsaKagidiAlDTO.getAlimSayisi()))
        {
            AccountsBorsaKagidi accountsBorsaKagidi = accountsBorsaKagidiGetirYoksaOlustur(alimYapanHesapVt, borsaKagidi, borsaKagidiAlDTO.getAlimSayisi());
            accountFieldAyarlariYap(alimYapanHesapVt, borsaKagidiAlDTO.getAlimSayisi(), borsaKagidi);
            borsaKagidiFieldAyarlariYap(borsaKagidi, borsaKagidiAlDTO.getAlimSayisi());
            iliskileriEkleAccountBorsaKagidi(alimYapanHesapVt, borsaKagidi, accountsBorsaKagidi);
        }

    }

    private AccountsBorsaKagidi accountsBorsaKagidiGetirYoksaOlustur(Account account, BorsaKagidi borsaKagidi, Integer alimSayisi) {
        AccountsBorsaKagidi accountsBorsaKagidiVt = accountsBorsaKagidiService.getirAccountsBorsaKagidiByAccountAndBorsaKagidiId(account.getId(), borsaKagidi.getId()).orElse(null);
        if(Objects.isNull(accountsBorsaKagidiVt)) {
            AccountsBorsaKagidi  accountsBorsaKagidi = new AccountsBorsaKagidi();
            accountsBorsaKagidi.borsaKagidiAlimEntitysiOlustur(account, borsaKagidi, alimSayisi);
            return accountsBorsaKagidi;
        }
        Integer yeniAdet = accountsBorsaKagidiVt.getAdet() + alimSayisi;
        accountsBorsaKagidiVt.setAdet(yeniAdet);
        return accountsBorsaKagidiVt;
    }

    private void accountFieldAyarlariYap(Account alimYapanHesap,Integer alimSayisi, BorsaKagidi borsaKagidi) {
        BigDecimal yeniBudget = alimYapanHesap.getBudget().subtract(BigDecimal.valueOf(alimSayisi).multiply(borsaKagidi.getPrice()));
        alimYapanHesap.setBudget(yeniBudget);
    }

    private void borsaKagidiFieldAyarlariYap( BorsaKagidi borsaKagidi, Integer alimSayisi) {
        Integer yeniStock = borsaKagidi.getStock() - alimSayisi;
        borsaKagidi.setStock(yeniStock);
    }

    private void iliskileriEkleAccountBorsaKagidi(Account alimYapanHesap, BorsaKagidi borsaKagidi ,AccountsBorsaKagidi accountsBorsaKagidi) {
        alimYapanHesap.getAccountsBorsaKagidiSet().add(accountsBorsaKagidi);
        borsaKagidi.getAccountsBorsaKagidi().add(accountsBorsaKagidi);
    }

    public Account getirUseraAitAccountBilgileri(User user) {
        return user.getAccount();
    }
}
