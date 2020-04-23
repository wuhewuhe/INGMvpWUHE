package ing.am.controller;

import ing.am.bean.account;
import ing.am.bean.transaction_log;
import ing.am.service.AccountService;
import ing.am.service.BankService;
import ing.am.service.ClientService;
import ing.am.service.TransactionsService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/accounts")
@EnableAutoConfiguration
public class AccountController {

    private final AccountService accountService;

    private final TransactionsService transactionsService;

    private final BankService bankService;

    private final ClientService clientService;

    public AccountController(AccountService accountService, TransactionsService transactionsService, BankService bankService, ClientService clientService) {
        this.accountService = accountService;
        this.transactionsService = transactionsService;
        this.bankService = bankService;
        this.clientService = clientService;
    }


    @GetMapping("/test")
    public String test(Model model) {
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
    @GetMapping("/listbyuser/{clientid}")
    @ResponseBody
    public List<account> listbyuser(@PathVariable("clientid") Integer clientid) {
        return accountService.listbyuserid(clientid);
    }

    @GetMapping("/listbyuser")
    @ResponseBody
    public List<account> listbyuserfront(@RequestParam(value = "clientid") Integer clientid) {
        return accountService.listbyuserid(clientid);
    }


    //create account
    @PostMapping("/create")
    @ResponseBody
    public String create(@RequestParam(value = "cardnumber") Integer cardnumber,
                          @RequestParam(value = "password") Integer password,
                          @RequestParam(value = "balance", required = false, defaultValue = "0") BigDecimal balance,
                          @RequestParam(value = "clientid") int clientid,
                          @RequestParam(value = "bankid") Integer bankid) {
        account acc = new account();
        if (balance.compareTo(BigDecimal.ZERO) > 0 && bankService.get(bankid) != null && clientService.get(clientid) != null) {
            acc.setBalance(balance);
            acc.setCardnumber(cardnumber);
            acc.setClientid(clientid);
            acc.setPassword(password);
            acc.setBankid(bankid);
            return accountService.update(acc).toString();
        }
        return "please verify your input money or bank id or client id";
    }

    //update account
    @PutMapping("/update/{id}")
    @ResponseBody
    public String update(@PathVariable("id") Integer id,
                         @RequestParam(value = "money") BigDecimal money) {
        Optional<account> acc = accountService.get(id);
        if (acc.isPresent()) {
            account temp = acc.get();
            BigDecimal bd = new BigDecimal("0.01");
            if (money.compareTo(bd) > 0) {
                temp.setBalance(money);
                accountService.update(temp);
                return temp.toString();
            }
        }
        return "please verify your input id";
    }

    //find account by id
    @GetMapping("/{accountid}")
    @ResponseBody
    public String findById(@PathVariable("accountid") Integer accountid) {
        Optional<account> acc = accountService.get(accountid);
        if (acc.isPresent()) {
            return acc.get().toString();
        } else {
            return "please verify your input id";
        }
    }

    //find account by id
    @GetMapping("/info")
    @ResponseBody
    public String findByIdFront(@RequestParam(value = "accountid") Integer accountid) {
        return accountService.get(accountid).toString();
    }

    //find solde by id
    @GetMapping("/solde/{accountid}")
    @ResponseBody
    public String findSoldeById(@PathVariable("accountid") Integer accountid) {
        account acc = accountService.get(accountid).get();
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
    public String deposit(@PathVariable("id") Integer id,
                          @RequestParam(value = "money") BigDecimal money) {
        Optional<account> optional = accountService.get(id);
        if (optional.isPresent()) {
            BigDecimal bd = new BigDecimal("0.01");
            account acc = optional.get();
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

                return accountService.update(acc).toString();
            } else {
                return "please verify your input money super than 0.01";
            }
        }
        return "please verify your input id";
    }

    @PostMapping("/deposit")
    @ResponseBody
    public String depositFront(@RequestParam(value = "id") int id,
                               @RequestParam(value = "money") BigDecimal money) {
        Optional<account> optional = accountService.get(id);
        if (optional.isPresent()) {
            BigDecimal bd = new BigDecimal("0.01");
            account acc = optional.get();
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

                return accountService.update(acc).toString();
            } else {
                return "please verify your input money super than 0.01";
            }
        }
        return "please verify your input id";
    }


    //update account by id, choice 2 : withdraw money
    @PostMapping("/withdraw/{id}")
    @ResponseBody
    public String withdraw(@PathVariable("id") Integer id,
                           @RequestParam(value = "money") BigDecimal money) {
        Optional<account> optional = accountService.get(id);
        if (optional.isPresent()) {
            account acc = optional.get();
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

                return accountService.update(acc).toString();
            } else {
                return "please verify your withdraw not overpass your solde";
            }
        } else {
            return "please verify your input id";
        }

    }

    @PostMapping("/withdraw")
    @ResponseBody
    public String withdrawFront(@RequestParam(value = "id") int id,
                                @RequestParam(value = "money") BigDecimal money) {
        Optional<account> optional = accountService.get(id);
        if (optional.isPresent()) {
            account acc = optional.get();
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

                return accountService.update(acc).toString();
            } else {
                return "please verify your withdraw not overpass your solde";
            }
        } else {
            return "please verify your input id";
        }
    }

    //update account by id, choice 3 : transfer money
    @PostMapping("/transfer/{id}")
    @ResponseBody
    public String transfer(@PathVariable("id") Integer id,
                           @RequestParam(value = "money") BigDecimal money,
                           @RequestParam(value = "otherid") Integer otherid) {
        Optional<account> acc1 = accountService.get(id);
        Optional<account> acc2 = accountService.get(otherid);

        if (acc1.isPresent() && acc2.isPresent()) {
            account from = acc1.get();
            account to = acc2.get();
            if (from.getBalance().compareTo(money) >= 0) {
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
                return "please verify your transfer money";
            }
        }
        return "please verify your input id";
    }

    @PostMapping("/transfer")
    @ResponseBody
    public String transferFront(@RequestParam(value = "id") Integer id,
                                @RequestParam(value = "money") BigDecimal money,
                                @RequestParam(value = "otherid") Integer otherid) {
        Optional<account> acc1 = accountService.get(id);
        Optional<account> acc2 = accountService.get(otherid);

        if (acc1.isPresent() && acc2.isPresent()) {
            account from = acc1.get();
            account to = acc2.get();
            if (from.getBalance().compareTo(money) >= 0) {
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
                return "please verify your transfer money";
            }
        }
        return "please verify your input id";
    }
}
