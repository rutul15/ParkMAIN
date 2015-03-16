/*
package com.davidoladeji.park.controller;

*/
/**
 * Created by Daveola on 3/16/2015.
 *//*



import java.util.LinkedHashMap;
import java.util.List;

import com.davidoladeji.park.service.rest.ExchangeRESTConsumer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;


@Controller

public class RESTController {

        @RequestMapping("/exchange/{symb}")
        public ModelAndView dispUser(@PathVariable("symb") String symb) {
            RestTemplate restTemplate = new RestTemplate();
            String url="http://localhost:8282/exchange/GBP/{symb}";
             ExchangeRESTConsumer exchange=restTemplate.getForObject(url, ExchangeRESTConsumer.class,symb);
            return new ModelAndView("dispUser", "user", exchange);
        }
}
*/
