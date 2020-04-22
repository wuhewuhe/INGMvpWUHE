package ing.am.controller;

import ing.am.bean.account;
import ing.am.bean.bank;
import ing.am.bean.transaction_log;
import ing.am.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping("/bank")
public class BankController {

    @Autowired
    private BankService bankService;

    //list all the banks
    @GetMapping("/listall")
    @ResponseBody
    public List<bank> listAll() {
        return bankService.listAll();
    }

    //create bank
    @PostMapping("/create")
    @ResponseBody
    public bank create(@RequestParam(value = "bankname") String bankname,
                          @RequestParam(value = "adress") String adress) {
        bank bank = new bank();
        bank.setAdress(adress);
        bank.setBankname(bankname);
        return bankService.update(bank);
    }



}
