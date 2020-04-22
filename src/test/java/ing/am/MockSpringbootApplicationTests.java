package ing.am;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class MockSpringbootApplicationTests {


    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    //intial mock before test
    @BeforeEach
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
     * @DisplayName customize method
     * @throws Exception
     */
    @DisplayName("find solde by id")
    @Test
    void findsoldeByid() throws Exception {

        //perform, requestbuilder
        mockMvc.perform(MockMvcRequestBuilders
                //concruct
                .get("/accounts/solde/1")
                //json
                .contentType(MediaType.APPLICATION_JSON))
                //.andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());;

    }

    /**
     * @DisplayName customize method
     * it can return account
     * @throws Exception
     */
    @DisplayName("deposit money super than 0.01")
    @Test
    void deposiMoneytNormal() throws Exception {

        //perform, requestbuilder
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders
                //concruct
                .put("/accounts/deposit/1?money=1")
                //json
                .contentType(MediaType.APPLICATION_JSON))
                //.andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String str = res.getResponse().getContentAsString();
        Assert.assertNotEquals("deposit well",str,"");

    }

    /**
     * @DisplayName customize method
     * @throws Exception
     */
    @DisplayName("deposit money super than 0.01")
    @Test
    void deposiMoneytNoNormal() throws Exception {

        //perform, requestbuilder
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders
                //concruct
                .put("/accounts/deposit/1?money=0")
                //json
                .contentType(MediaType.APPLICATION_JSON))
                //.andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String str = res.getResponse().getContentAsString();
        Assert.assertEquals("deposit not well",str,"");
    }

    /**
     * @DisplayName customize method
     * it can return account
     * @throws Exception
     */
    @DisplayName("writhdraw money normal")
    @Test
    void withdrawNormal() throws Exception {

        //perform, requestbuilder
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders
                //concruct
                .put("/accounts/withdraw/1?money=1")
                //json
                .contentType(MediaType.APPLICATION_JSON))
                //.andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String str = res.getResponse().getContentAsString();
        Assert.assertNotEquals("withdraw well",str,"");

    }

    /**
     * @DisplayName customize method
     * @throws Exception
     */
    @DisplayName("withdraw money super than solde")
    @Test
    void withdrawtNoNormal() throws Exception {

        //perform, requestbuilder
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders
                //concruct
                .put("/accounts/withdraw/1?money=1")
                //json
                .contentType(MediaType.APPLICATION_JSON))
                //.andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String str = res.getResponse().getContentAsString();
        Assert.assertEquals("withdraw well",str,"");
    }

    /**
     * @DisplayName transfer
     * @throws Exception
     */
    @DisplayName("transfer money from a to b")
    @Test
    void transferNormal() throws Exception {

        //perform, requestbuilder
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders
                //concruct
                .post("/accounts/transfer/1?money=1.00&otherid=2")
                //json
                .contentType(MediaType.APPLICATION_JSON))
                //.andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String str = res.getResponse().getContentAsString();
        assertAll("assert",
                () -> assertEquals(200, res.getResponse().getStatus()),
                () -> assertTrue(!str.isEmpty())
        );
    }

    /**
     * @DisplayName transfer
     * @throws Exception
     */
    @DisplayName("transfer money from a to b")
    @Test
    void transferNoNormal() throws Exception {

        //perform, requestbuilder
        MvcResult res;
        res = mockMvc.perform(MockMvcRequestBuilders
                //concruct
                .post("/accounts/transfer/1?money=10000.00&otherid=2")
                //json
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String str = res.getResponse().getContentAsString();
        System.out.println(str);
        Assert.assertEquals("you can not transfer the money overpass your deposit",str.trim());
    }
}
