package ing.am.ServicesTests;

import ing.am.bean.account;
import ing.am.bean.bank;
import ing.am.service.AccountService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
class AccountServiceTest {

    @Autowired
    AccountService accountService;

    @Test
    public void testGetAccountCount() {
        //account expect = new account(1,111111112,123456,new BigDecimal("119.00"),"111",1);
        long except = 5l;
        long actual = accountService.count();
        Assert.assertEquals("can not find account by id", except, actual);
    }

    @Test
    public void testGetAccountById() {
        account except = new account(1,111111113,123456,new BigDecimal("120.00"),111,1);
        account actual = accountService.get(1);
        Assert.assertEquals("can not find account by id", except.toString(), actual.toString());
    }


    @Test
    public void testDepositMoney() {
        account actual = accountService.get(1);
        BigDecimal bd = new BigDecimal("1");
        actual.setBalance(actual.getBalance().add(bd));
        Assert.assertEquals("deposit money not right", new BigDecimal("121.00"),actual.getBalance());
    }

    @Test
    public void testWirthdrawMoney() {
        account actual = accountService.get(1);
        BigDecimal bd = new BigDecimal("1");
        actual.setBalance(actual.getBalance().subtract(bd));
        Assert.assertEquals("deposit money not right", new BigDecimal("119.00"),actual.getBalance());
    }

    @Test
    public void testConsultSoldeById(){
        account actual = accountService.get(1);
        Assert.assertEquals("successful consult solde", actual.getBalance(), new BigDecimal("116.00"));
    }

    @Test
    public void testTransfer(){
        account actual = accountService.get(1);
        actual.setBalance(new BigDecimal("119.00"));
        account after = accountService.update(actual);
        Assert.assertNotEquals("successful transfer ", actual, after);
    }

    @Test
    public void testListByBank(){
        List<account> actual = accountService.listbybank(1);
        Assert.assertEquals("accounts list by bank not right", 2,actual.size());
    }

}
