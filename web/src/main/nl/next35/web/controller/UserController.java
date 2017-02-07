package nl.next35.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lesley
 */
@RestController
public final class UserController {

    @RequestMapping("/user/*")
    public String user() {
        return "lol";
    }

}
