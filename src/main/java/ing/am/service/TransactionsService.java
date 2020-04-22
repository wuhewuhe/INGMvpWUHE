package ing.am.service;

import ing.am.bean.transaction_log;
import ing.am.dao.TransactionsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionsService {
    @Autowired
    private TransactionsDAO repo;

    public List<transaction_log> listAll() {
        Iterable<transaction_log> list = repo.findAll();
        List<transaction_log> res = new ArrayList<>();
        for (transaction_log temp : list) {
            res.add(temp);
        }
        return res;
    }

    public transaction_log update(transaction_log transaction_log) {
        return repo.save(transaction_log);
    }

    public transaction_log get(int id) {
        return repo.findById(id).get();
    }

    public List<transaction_log> findBySenderId(Integer idaccount) {
        return repo.findBySenderId(idaccount);
    }

    public long count() {
        return repo.count();
    }

    public List<transaction_log> findByReceiverId(Integer idaccount) {
        return repo.findByReceiverId(idaccount);
    }

    public List<transaction_log> findByTransType(Integer ta_type) {
        return repo.findByTransType(ta_type);
    }
}
