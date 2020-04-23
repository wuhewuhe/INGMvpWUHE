package ing.am.service;

import ing.am.bean.account;
import ing.am.bean.bank;
import ing.am.dao.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService  {
    @Autowired
    private AccountDAO repo;

    public List<account> listAll() {
        Iterable<account> list = repo.findAll();
        List<account> res = new ArrayList<>();
        for (account temp : list) {
            res.add(temp);
        }
        return res;
    }

    public account update(account account) {
        return repo.save(account);
    }

    public Optional<account> get(int accountid) {
        return repo.findById(accountid);
    }

    public List<account> listbybank(int bankid) {
        return  repo.listbybank(bankid);
    }

    public long count(){
        return  repo.count();
    }

    public List<account> listbyuserid(int userid) {
        return  repo.listbyuserid(userid);
    }

    public String findSoldeByAccountid(int accountid){
        return  repo.findSoldeByAccountid(accountid).toString();
    }
}
