
package com.davidoladeji.park.controller;


/**
 * Created by Daveola on 3/16/2015.
 */



import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.davidoladeji.park.model.Booking;
import com.davidoladeji.park.service.rest.ExchangeRESTConsumer;


@Controller

public class RESTController {

        @RequestMapping(value="/exchange", method = RequestMethod.GET)
        @ResponseBody
        public String exchange(@RequestParam(value = "symb") String symb, HttpSession session) {
            RestTemplate restTemplate = new RestTemplate();
            
            
            
            Booking booking = (Booking)(session.getAttribute("booking"));
            if(booking == null){
            	return  "";
            }
            double total =  booking.getTotal();
            System.out.println("RESTController.exchange() "+ booking.getTotal());
            
            String lastSymbol = (String) session.getAttribute("last.exchange.symbol");
            if(lastSymbol == null){
            	lastSymbol = "GBP";
            	
            }
         // assign new symbol to last symbol
        	session.setAttribute("last.exchange.symbol", symb);
            
            if(!lastSymbol.equals(symb)){
            
	            String url = "http://localhost:8282/exchange/{symbone}/{symbtwo}/{amount}";
	            //String url="http://localhost:8282/exchange/"+ lastSymbol +"/{symb}/{}";
	            
	            Object pages = restTemplate.getForObject(url, Object.class, lastSymbol, symb, booking.getTotal());
	            System.out.println("RESTController.exchange()" + pages);
	            Map<String, Object> result = (Map<String, Object>) pages;
	            
	            String msg = (String)result.get("message");
	            if (!"Unsuccessful conversion".equals(msg)){
	            
	            	double nTotal = (Double) result.get("conversionresult");
	            
	            	System.out.println("New Total: "+ nTotal);
	            	booking.setTotal(nTotal);
	            	total = nTotal;
	            }
            }
            
            //ExchangeRESTConsumer exchange=restTemplate.getForObject(url, ExchangeRESTConsumer.class,lastSymbol,symb,booking.getTotal());
            //System.out.println(exchange.getConversionresult());
             
             
             
             
            return total+"";
        }
}

