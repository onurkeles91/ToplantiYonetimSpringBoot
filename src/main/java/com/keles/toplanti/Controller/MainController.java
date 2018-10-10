package com.keles.toplanti.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Toplantı yönetimi controller sınıfını temsil eder.
 *
 * @author Onur KELES
 * @since 9.10.2018
 */

@Controller
public class MainController {

    /**
     * localhost:8080'ne giriş yapıldığında index.html'i açan metod.
     */
    @RequestMapping("/")
    public String showHomePage(){
        return "index.html";
    }

}
