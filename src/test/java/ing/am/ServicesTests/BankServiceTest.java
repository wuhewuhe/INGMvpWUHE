package ing.am.ServicesTests;

import ing.am.bean.bank;
import ing.am.service.BankService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static com.sun.tools.internal.xjc.reader.Ring.add;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
class BankServiceTest {

    @Autowired
    BankService bankService;

    @Test
    void testGetBankCount() {
        //account expect = new account(1,111111112,123456,new BigDecimal("119.00"),"111",1);
        long except = 2l;
        long actual = bankService.count();
        Assert.assertSame("can not find account by id", except, actual);
    }

    @Test
    void testListBank() {
        List<bank> except = bankService.listAll();
        List<bank> actual = new ArrayList<bank>();
        actual.add(new bank(1, "ing", "paris"));
        actual.add(new bank(2, "bnp", "lyon"));
        Assert.assertNotEquals("can list bank", except, actual);
    }


}
