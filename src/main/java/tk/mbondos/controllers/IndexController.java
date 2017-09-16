package tk.mbondos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {



    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/inv")
    public String invoice() {
        return "invoice";
    }


}
