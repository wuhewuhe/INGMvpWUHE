package ing.am.ServicesTests;

import ing.am.service.ClientService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
class ClientsServiceTest {

    @Autowired
    private ClientService clientsService;

    @Test
    public void testGetClientsCount() {
        //account expect = new account(1,111111112,123456,new BigDecimal("119.00"),"111",1);
        long except = 10l;
        long actual = clientsService.count();
        Assert.assertSame("can not count client", except, actual);
    }

}
