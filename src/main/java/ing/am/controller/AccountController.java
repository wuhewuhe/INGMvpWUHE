package ing.am.controller;

import ing.am.bean.account;
import ing.am.bean.transaction_log;
import ing.am.service.AccountService;
import ing.am.service.BankService;
import ing.am.service.TransactionsService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/accounts")
@EnableAutoConfiguration
public class AccountController {

    private final AccountService accountService;

    private final TransactionsService transactionsService;

    private final BankService bankService;

    public AccountController(AccountService accountService, TransactionsService transactionsService, BankService bankService) {
        this.accountService = accountService;
        this.transactionsService = transactionsService;
        this.bankService = bankService;
    }


    @GetMapping("/test")
    public String test(Model model){
        List<account> listAccounts = accountService.listAll();
        for (account a : listAccounts) System.out.println(a.getAccountid());
        model.addAttribute("listAccounts", listAccounts);
        return "test";
    }

    //list all accounts
    @GetMapping("/listall")
    @ResponseBody
    public List<account> listall() {
        return accountService.listAll();
    }

    //list all accounts in a special bank
    @GetMapping("/listbybank/{bankid}")
    @ResponseBody
    public List<account> listbybank(@PathVariable("bankid") Integer bankid) {
        return accountService.listbybank(bankid);
    }

    //list all accounts by userid
    @GetMapping("/listbyuser/{userid}")
    @ResponseBody
    public List<account> listbyuser(@PathVariable("userid") Integer userid) {
        return accountService.listbyuserid(userid);
    }

    @GetMapping("/listbyuser")
    @ResponseBody
    public List<account> listbyuserfront(@RequestParam(value = "userid") Integer userid) {
        return accountService.listbyuserid(userid);
    }


    //create account
    @PostMapping("/create")
    @ResponseBody
    public account create(@RequestParam(value = "cardnumber") Integer cardnumber,
                          @RequestParam(value = "password") Integer password,
                          @RequestParam(value = "balance", required = false, defaultValue = "0") BigDecimal balance,
                          @RequestParam(value = "userid") int userid,
                          @RequestParam(value = "bankid") Integer bankid) {
        account acc = new account();
        if (balance.compareTo(BigDecimal.ZERO) > 0 && bankService.get(bankid) != null) {
            acc.setBalance(balance);
            acc.setCardnumber(cardnumber);
            acc.setUserid(userid);
            acc.setPassword(password);
            acc.setBankid(bankid);
            return accountService.update(acc);
        }
        return null;
    }

    //update account
    @PutMapping("/update/{id}")
    @ResponseBody
    public account update(@PathVariable("id") Integer id,
                          @RequestParam(value = "money") BigDecimal money) {
        account acc = accountService.get(id);
        BigDecimal bd = new BigDecimal("0.01");
        if (money.compareTo(bd) > 0) {
            acc.setBalance(money);
            return accountService.update(acc);
        }
        return null;
    }

    //find account by id
    @GetMapping("/{accountid}")
    @ResponseBody
    public account findById(@PathVariable("accountid") Integer accountid) {
        return accountService.get(accountid);
    }

    //find account by id
    @GetMapping("/info")
    @ResponseBody
    public account findByIdFront(@RequestParam(value = "accountid") Integer accountid) {
        return accountService.get(accountid);
    }

    //find solde by id
    @GetMapping("/solde/{accountid}")
    @ResponseBody
    public String findSoldeById(@PathVariable("accountid") Integer accountid) {
        account acc = accountService.get(accountid);
        return acc.getBalance().toString();
    }

    //find solde by id
    @GetMapping("/solde")
    @ResponseBody
    public String findSoldeByIdFront(@RequestParam(value = "accountid") Integer accountid) {
        return accountService.findSoldeByAccountid(accountid);
    }


    //update account by id, choice 1 : save money
    @PostMapping("/deposit/{id}")
    @ResponseBody
    public account deposit(@PathVariable("id") Integer id,
                           @RequestParam(value = "money") BigDecimal money) {
        account acc = accountService.get(id);
        BigDecimal bd = new BigDecimal("0.01");
        if (money.compareTo(bd) > 0) {
            //deposit money
            acc.setBalance(acc.getBalance().add(money));

            //record in table of transaction
            transaction_log transLog = new transaction_log();
            int number = (int) transactionsService.count();
            transLog.setId(++number);
            transLog.setAccoundid(id);
            transLog.setOtherid(id);
            transLog.setSenderbankid(acc.getBankid());
            transLog.setReceiverbankid(acc.getBankid());
            transLog.setTr_money(money);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            transLog.setDatetime(timestamp);
            transLog.setTa_type(1);
            transactionsService.update(transLog);
            System.out.println(transLog);

            return accountService.update(acc);
        }
        return null;
    }

    @PostMapping("/deposit")
    @ResponseBody
    public account depositFront(@RequestParam(value = "id") int id,
                           @RequestParam(value = "money") BigDecimal money) {
        account acc = accountService.get(id);
        BigDecimal bd = new BigDecimal("0.01");
        if (money.compareTo(bd) > 0) {
            //deposit money
            acc.setBalance(acc.getBalance().add(money));

            //record in table of transaction
            transaction_log transLog = new transaction_log();
            int number = (int) transactionsService.count();
            transLog.setId(++number);
            transLog.setAccoundid(id);
            transLog.setOtherid(id);
            transLog.setSenderbankid(acc.getBankid());
            transLog.setReceiverbankid(acc.getBankid());
            transLog.setTr_money(money);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            transLog.setDatetime(timestamp);
            transLog.setTa_type(1);
            transactionsService.update(transLog);
            System.out.println(transLog);

            return accountService.update(acc);
        }
        return null;
    }


    //update account by id, choice 2 : withdraw money
    @PostMapping("/withdraw/{id}")
    @ResponseBody
    public account withdraw(@PathVariable("id") Integer id,
                            @RequestParam(value = "money") BigDecimal money) {
        account acc = accountService.get(id);
        if (acc.getBalance().compareTo(money) >= 0) {
            //withdraw money from account
            BigDecimal bd = acc.getBalance().subtract(money);
            acc.setBalance(bd);

            //record in table of transaction
            transaction_log transLog = new transaction_log();
            int number = (int) transactionsService.count();
            transLog.setId(++number);
            transLog.setAccoundid(id);
            transLog.setOtherid(id);
            transLog.setSenderbankid(acc.getBankid());
            transLog.setReceiverbankid(acc.getBankid());
            transLog.setTr_money(money);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            transLog.setDatetime(timestamp);
            transLog.setTa_type(2);
            transactionsService.update(transLog);
            System.out.println(transLog);

            return accountService.update(acc);
        } else {
            return null;
        }
    }

    @PostMapping("/withdraw")
    @ResponseBody
    public account withdrawFront(@RequestParam(value = "id") int id,
                            @RequestParam(value = "money") BigDecimal money) {
        account acc = accountService.get(id);
        if (acc.getBalance().compareTo(money) >= 0) {
            //withdraw money from account
            BigDecimal bd = acc.getBalance().subtract(money);
            acc.setBalance(bd);

            //record in table of transaction
            transaction_log transLog = new transaction_log();
            int number = (int) transactionsService.count();
            transLog.setId(++number);
            transLog.setAccoundid(id);
            transLog.setOtherid(id);
            transLog.setSenderbankid(acc.getBankid());
            transLog.setReceiverbankid(acc.getBankid());
            transLog.setTr_money(money);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            transLog.setDatetime(timestamp);
            transLog.setTa_type(2);
            transactionsService.update(transLog);
            System.out.println(transLog);

            return accountService.update(acc);
        } else {
            return null;
        }
    }

    //update account by id, choice 3 : transfer money
    @PostMapping("/transfer/{id}")
    @ResponseBody
    public String transfer(@PathVariable("id") Integer id,
                           @RequestParam(value = "money") BigDecimal money,
                           @RequestParam(value = "otherid") Integer otherid) {
        account from = accountService.get(id);
        account to = accountService.get(otherid);

        if (from.getBalance().compareTo(money) >= 0 && accountService.get(otherid) != null) {
            //record the money
            BigDecimal bd = from.getBalance().subtract(money);
            BigDecimal bd2 = to.getBalance().add(money);

            //from acount substract money
            from.setBalance(bd);
            accountService.update(from);
            System.out.println(from);

            //to account add money
            to.setBalance(bd2);
            accountService.update(to);
            System.out.println(to);

            // record in transaction table
            transaction_log transLog = new transaction_log();
            int number = (int) transactionsService.count();
            transLog.setId(++number);
            transLog.setAccoundid(id);
            transLog.setOtherid(otherid);
            transLog.setSenderbankid(from.getBankid());
            transLog.setReceiverbankid(to.getBankid());
            transLog.setTr_money(money);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            transLog.setDatetime(timestamp);
            transLog.setTa_type(3);
            transactionsService.update(transLog);

            return from.toString() + '\n' + to.toString() + '\n' + transLog.toString();
        } else {
            return "you can not transfer the money overpass your deposit";
        }
    }

    @PostMapping("/transfer")
    @ResponseBody
    public String transferFront(@RequestParam(value = "id") Integer id,
                           @RequestParam(value = "money") BigDecimal money,
                           @RequestParam(value = "otherid") Integer otherid) {
        account from = accountService.get(id);
        account to = accountService.get(otherid);

        if (from.getBalance().compareTo(money) >= 0 && accountService.get(otherid) != null) {
            //record the money
            BigDecimal bd = from.getBalance().subtract(money);
            BigDecimal bd2 = to.getBalance().add(money);

            //from acount substract money
            from.setBalance(bd);
            accountService.update(from);
            System.out.println(from);

            //to account add money
            to.setBalance(bd2);
            accountService.update(to);
            System.out.println(to);

            // record in transaction table
            transaction_log transLog = new transaction_log();
            int number = (int) transactionsService.count();
            transLog.setId(++number);
            transLog.setAccoundid(id);
            transLog.setOtherid(otherid);
            transLog.setSenderbankid(from.getBankid());
            transLog.setReceiverbankid(to.getBankid());
            transLog.setTr_money(money);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            transLog.setDatetime(timestamp);
            transLog.setTa_type(3);
            transactionsService.update(transLog);

            return from.toString() + '\n' + to.toString() + '\n' + transLog.toString();
        } else {
            return "you can not transfer the money overpass your deposit";
        }
    }
}
