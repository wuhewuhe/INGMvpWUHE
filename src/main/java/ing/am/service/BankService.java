package ing.am.service;

import ing.am.bean.account;
import ing.am.bean.bank;
import ing.am.dao.BankDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankService {
    @Autowired
    private BankDAO repo;

    public List<bank> listAll() {
        Iterable<bank> list = repo.findAll();
        List<bank> res = new ArrayList<>();
        for (bank temp : list) {
            res.add(temp);
        }
        return res;
    }

    public bank get(int id) {
        return repo.findById(id).get();
    }

    public bank update(bank bank) {
        return repo.save(bank);
    }

    public long count() {
        return  repo.count();
    }
}
