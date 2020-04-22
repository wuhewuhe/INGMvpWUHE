package ing.am.ServicesTests;

import ing.am.bean.transaction_log;
import ing.am.service.TransactionsService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class TransactionService {

    @Autowired
    TransactionsService transactionService;

    @Test
    public void testListAllTransactions(){
        List<transaction_log> except = transactionService.listAll();
        List<transaction_log> actual = new ArrayList<>();
        actual.add(new transaction_log(1, 1,2,new BigDecimal("1.00"), new Timestamp(System.currentTimeMillis()), 2, 1, 1
        ));
        Assert.assertNotEquals("list all transactions is good", except, actual);
    }

    @Test
    public void testGetTransactionsByid(){
        transaction_log except = transactionService.get(1);
        transaction_log actual = new transaction_log(1, 1,2,new BigDecimal("1.00"), new Timestamp(System.currentTimeMillis()), 2, 1, 1);
        Assert.assertNotEquals("test find transaction by id", except, actual);
    }

    @Test
    public void testListTransactionsByTransType(){
        List<transaction_log> except = transactionService.findByTransType(1);
        List<transaction_log> actual = new ArrayList<>();
        actual.add(new transaction_log(1, 1,2,new BigDecimal("1.00"), new Timestamp(System.currentTimeMillis()), 2, 1, 1
        ));
        Assert.assertNotEquals("list all transactions by transaction type is right", except, actual);
    }

    @Test
    public void testListTransactionsBySenderId(){
        List<transaction_log> except = transactionService.findBySenderId(1);
        List<transaction_log> actual = new ArrayList<>();
        actual.add(new transaction_log(1, 1,2,new BigDecimal("1.00"), new Timestamp(System.currentTimeMillis()), 2, 1, 1
        ));
        Assert.assertNotEquals("list all transactions by sender bank id is right", except, actual);
    }

    @Test
    public void testListTransactionsByReceiverId(){
        List<transaction_log> except = transactionService.findByReceiverId(1);
        List<transaction_log> actual = new ArrayList<>();
        actual.add(new transaction_log(1, 1,2,new BigDecimal("1.00"), new Timestamp(System.currentTimeMillis()), 2, 1, 1
        ));
        Assert.assertNotEquals("list all transactions by receiver bank id is right", except, actual);
    }
}
