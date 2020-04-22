package ing.am.service;

import ing.am.bean.account;
import ing.am.bean.clients;
import ing.am.dao.AccountDAO;
import ing.am.dao.ClientsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientsDAO repo;

    public List<clients> listAll() {
        Iterable<clients> list = repo.findAll();
        List<clients> res = new ArrayList<>();
        for (clients temp : list) {
            res.add(temp);
        }
        return res;
    }

    public clients update(clients clients) {
        return repo.save(clients);
    }

    public clients get(int id) {
        return repo.findById(id).get();
    }


    public long count(){
        return  repo.count();
    }
}
