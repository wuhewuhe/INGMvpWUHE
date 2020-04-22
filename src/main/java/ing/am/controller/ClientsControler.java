package ing.am.controller;

import ing.am.bean.clients;
import ing.am.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@EnableAutoConfiguration
@RequestMapping("/clients")
public class ClientsControler {

    private final ClientService clientService;

    public ClientsControler(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/listall")
    public List<clients> listAll(){
        return clientService.listAll();
    }

    @GetMapping("/findById/{id}")
    public clients findById(@PathVariable("id") Integer id){
        return clientService.get(id);
    }

}
