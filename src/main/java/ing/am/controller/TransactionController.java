package ing.am.controller;

import ing.am.bean.transaction_log;
import ing.am.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@EnableAutoConfiguration
public class TransactionController {

    @Autowired
    private TransactionsService transactionsService;

    //list all transactions
    @GetMapping("/listAll")
    @ResponseBody
    public List<transaction_log> listAll() {
        return transactionsService.listAll();
    }

    //find constraction by send id
    @GetMapping("/findBySenderId/{idaccount}")
    @ResponseBody
    public List<transaction_log> findBySenderId(@PathVariable("idaccount") Integer idaccount) {
        List<transaction_log> list = transactionsService.findBySenderId(idaccount);
        return list;
    }

    //find constraction by send id
    @GetMapping("/findBySenderId")
    @ResponseBody
    public List<transaction_log> findBySenderIdFront(@RequestParam(value = "idaccount") int idaccount) {
        List<transaction_log> list = transactionsService.findBySenderId(idaccount);
        return list;
    }

    //find constraction by receive id
    @GetMapping("/findByReceiverId/{idaccount}")
    @ResponseBody
    public List<transaction_log> findByReceiverId(@PathVariable("otherid") Integer otherid) {
        List<transaction_log> list = transactionsService.findByReceiverId(otherid);
        return list;
    }

    //find constraction by receive id
    @GetMapping("/findByReceiverId")
    @ResponseBody
    public List<transaction_log> findByReceiverId(@RequestParam(value = "otherid") int otherid) {
        List<transaction_log> list = transactionsService.findByReceiverId(otherid);
        return list;
    }

    //find constraction by transfer type
    @GetMapping("/findByTransType/{idaccount}")
    @ResponseBody
    public List<transaction_log> findByTransType(@PathVariable("idaccount") Integer idaccount) {
        List<transaction_log> list = transactionsService.findByTransType(idaccount);
        return list;
    }

    //find constraction by transfer type
    @GetMapping("/findByTransType")
    @ResponseBody
    public List<transaction_log> findByTransType(@RequestParam(value = "ta_type") int ta_type) {
        List<transaction_log> list = transactionsService.findByTransType(ta_type);
        return list;
    }
}
