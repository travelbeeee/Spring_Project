package travelbeeee.typeconverter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HelloController {

    @GetMapping("/hello-v1")
    public String helloV1(HttpServletRequest request) {
        return "ok";
    }

    @GetMapping("/hello-v2")
    public String helloV2(@RequestParam(required = false) Integer data){
        return "ok";
    }
}
