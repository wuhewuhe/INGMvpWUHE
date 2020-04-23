package ing.am.controller;

import ing.am.bean.bank;
import ing.am.bean.clients;
import ing.am.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @ResponseBody
    public List<clients> listAll(){
        return clientService.listAll();
    }

    @GetMapping("/findById/{id}")
    @ResponseBody
    public clients findById(@PathVariable("id") Integer id){
        return clientService.get(id);
    }

    //create bank
    @PostMapping("/create")
    @ResponseBody
    public clients create(@RequestParam(value = "age") Integer age,
                       @RequestParam(value = "clientid") Integer clientid,
                       @RequestParam(value = "sex") Boolean sex,
                       @RequestParam(value = "realname") String realname) {
        clients client = new clients();
        client.setAge(age);
        client.setRealname(realname);
        client.setSex(sex);
        client.setClientid(clientid);
        return clientService.update(client);
    }
}
