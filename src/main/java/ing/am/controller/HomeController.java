package ing.am.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@EnableAutoConfiguration
public class HomeController {

    @GetMapping({"/","/hello"})
    public String hello(){
        return "index";
    }
}
