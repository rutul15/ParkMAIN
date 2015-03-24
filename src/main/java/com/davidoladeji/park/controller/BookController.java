package com.davidoladeji.park.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.davidoladeji.park.model.Account;
import com.davidoladeji.park.model.Airport;
import com.davidoladeji.park.model.Booking;
import com.davidoladeji.park.model.Carpark;
import com.davidoladeji.park.model.CarparkSpace;
import com.davidoladeji.park.model.Search;
import com.davidoladeji.park.model.SpaceType;
import com.davidoladeji.park.service.interfaces.AccountService;
import com.davidoladeji.park.service.interfaces.AirportService;
import com.davidoladeji.park.service.interfaces.BookingService;
import com.davidoladeji.park.service.interfaces.CarparkService;
import com.davidoladeji.park.service.interfaces.CarparkSpaceService;
import com.davidoladeji.park.service.interfaces.SearchService;
import com.davidoladeji.park.service.interfaces.SpaceTypeService;
import com.davidoladeji.park.service.util.MyTimeDateUtil;
import com.davidoladeji.park.service.util.Pricing;

/**
 * Created by Daveola on 3/3/2015.
 * All pages navigations related to the Booking
 */

@Controller
@SessionAttributes({"search", "booking"})
public class BookController {


    @Autowired
    SearchService searchService;

    @Autowired
    CarparkSpaceService carparkSpaceService;

    @Autowired
    SpaceTypeService spaceTypeService;


    @Autowired
    CarparkService carparkService;


    @Autowired
    AirportService airportService;
    
    @Autowired
    BookingService bookingService;
    
    @Autowired
    AccountService accountService;


    @ModelAttribute("search")
    public Search createBlankSearch() {
        return new Search();
    }

    @ModelAttribute("booking")
    public Booking createBlankBooking() {
        return new Booking();
    }


    @RequestMapping(value = "/spaces/search", method = RequestMethod.GET)
    public ModelAndView searchForSpace(ModelAndView model, /*@Valid*/ @ModelAttribute("search") Search search, BindingResult result,
                                       @RequestParam(value = "airportid", required = false) Long airportid,
                                       @RequestParam(value = "traveldate", required = true) String traveldate,
                                       @RequestParam(value = "traveltime", required = true) String traveltime,
                                       @RequestParam(value = "travelenddate", required = true) String travelenddate,
                                       @RequestParam(value = "travelendtime", required = true) String travelendtime, 
                                       @RequestParam(value = "spaceTypeId", required = false) Long spaceTypeId,
                                       HttpServletRequest request
    ) {



        if (result.hasErrors()) {
        	System.out.println(result.getAllErrors());
           model.setViewName("home");
           return model;
        }
    	
    	

        model = new ModelAndView("search");
        model.addObject("search", search);


        /**
         * Convert dates to users Local time using Joda time
         * and use that to find carpark spaces available at the specified times
         */
        MyTimeDateUtil myTimeDateUtil = new MyTimeDateUtil();

        Date traveldateConverted = myTimeDateUtil.convertStringtoDate(traveldate);
        Date travelenddateConverted = myTimeDateUtil.convertStringtoDate(travelenddate);
        Date traveltimeConverted = myTimeDateUtil.convertStringtoTime(traveltime);
        Date travelendtimeConverted = myTimeDateUtil.convertStringtoTime(travelendtime);
        search.setAirportid(airportid);


        int numdays = Days.daysBetween(myTimeDateUtil.convertUtilDateToLocalDate(traveldateConverted), myTimeDateUtil.convertUtilDateToLocalDate(travelenddateConverted)).getDays();
        search.setNumdays(numdays);
        search.setTraveldate(traveldateConverted);
        search.setTravelenddate(travelenddateConverted);
        search.setTraveltime(traveltimeConverted);
        search.setTravelendtime(travelendtimeConverted);
        search.setSpaceTypeId(spaceTypeId);


        List<Airport> airportList = airportService.findAllAirports();
        model.addObject("airportList", airportList);

        /**
         *
         * Retrieve the airport that user searched
         * and forward to view
         */

        Airport airport = airportService.findAirportById(airportid);
        model.addObject("airport", airport);


        /**
         * Return List of Carparks at a airport
         * With Available Space
         *
         */


        List<Carpark> carparkList = carparkService.findAvailableCarparksInAirport(true, airport);


        searchService.saveSearch(search);

        model.addObject("carparkList", carparkList);
        model.setViewName("searchresult");
        return model;

     //   }

    }


    @RequestMapping(value = "/spaces/{carparkId}", method = RequestMethod.GET)
    public ModelAndView gotoSpacesList(ModelAndView model, @ModelAttribute("search") Search search, @ModelAttribute Booking booking, @PathVariable("carparkId") Long carparkId) {

        model.addObject("search", search);
        
        Carpark carpark = carparkService.findCarparkById(carparkId);
        model.addObject("carpark", carpark);
        List<CarparkSpace> carparkSpaceList = null;

        if (search.getSpaceTypeId().intValue() == 1) {
            carparkSpaceList = carparkSpaceService.findAllByAirportCarparkTypeAndAvaiablity(search.getAirportid(), carpark.getId(), search.getSpaceTypeId(), false);
        } else if (search.getSpaceTypeId().intValue() == 2) {
            carparkSpaceList = carparkSpaceService.findAllByAirportCarparkTypeAndAvaiablity(search.getAirportid(), carpark.getId(), search.getSpaceTypeId(), false);
        } else if (search.getSpaceTypeId().intValue() == 3) {
            carparkSpaceList = carparkSpaceService.findAllByAirportCarparkTypeAndAvaiablity(search.getAirportid(), carpark.getId(), search.getSpaceTypeId(), false);
        }

        /*if (carparkSpaceList.isEmpty()) {
            String noSpaceMessage = "No Spaces available at Carpark";
        } else if (!carparkSpaceList.isEmpty()) {*/
        model.addObject("carparkSpaceList", carparkSpaceList);
        //}


        booking.setArrivalDate(search.getTraveldate());
        booking.setArrivalTime(search.getTraveltime());
        booking.setDepartureDate(search.getTravelenddate());
        booking.setDepartureTime(search.getTravelendtime());
        
       


        model.setViewName("spacesatcarpark");
        return model;
    }


    @RequestMapping(value = "/booking/{carparkSpaceId}", method = RequestMethod.GET)
    public ModelAndView gotoBookingForm(ModelAndView model, @ModelAttribute Search search, @ModelAttribute Booking booking, @PathVariable("carparkSpaceId") Long carparkId) {

    	 model.addObject("search", search);
         

        Carpark carpark = carparkService.findCarparkById(carparkId);
        model.addObject("carpark", carpark);

        booking.setSpaceType(spaceTypeService.findSpaceTypeById(search.getSpaceTypeId()));
        
        if (search.getSpaceTypeId().intValue() == 1) {
            booking.setCarparkSpace(carparkSpaceService.findOneRegularAvailableSpace());
            
        } else if (search.getSpaceTypeId().intValue() == 2) {
            booking.setCarparkSpace(carparkSpaceService.findOneFamilyAvailableSpace());
            
        } else if (search.getSpaceTypeId().intValue() == 3) {
            booking.setCarparkSpace(carparkSpaceService.findOneDisabledAvailableSpace());
            
        }
        
        
        // calculate the price using pricing class
        // We need to find 3 diff price and then whichever is lower but not 0 we display that.
        
        double price1 = Pricing.getPriceByType(booking.getCarparkSpace().getCarpark().getRegularprice(), search.getSpaceTypeId().intValue());
        
        double price2 = Pricing.getPriceByBookingNumDays(booking.getCarparkSpace().getCarpark().getRegularprice(), 
        		new LocalDate(booking.getArrivalDate()), new LocalDate(booking.getDepartureDate()));
        
        double price3 = Pricing.getPriceByHowAdvance(booking.getCarparkSpace().getCarpark().getRegularprice(), 
        		new LocalDate(booking.getArrivalDate()));
        
        // we are going to calculate min price
        List<Double> prices = new ArrayList<Double>();
        if(price1 > 0) prices.add(price1); if(price2 > 0) prices.add(price2); if(price3 > 0) prices.add(price3);
        
        booking.setBase_price(Collections.min(prices));
        booking.setTotal(Pricing.getTotalTripPrice(booking.getBase_price(), new LocalDate(booking.getArrivalDate()), new LocalDate(booking.getDepartureDate())));
        
        booking.setArrivalDate(search.getTraveldate());
        booking.setArrivalTime(search.getTraveltime());
        booking.setDepartureDate(search.getTravelenddate());
        booking.setDepartureTime(search.getTravelendtime());

        

        /**
         * Send Space Type List to front-end for drop-down in form
         */
        List<SpaceType> spaceTypeList = spaceTypeService.findAllSpaceTypes();
        model.addObject("spaceTypeList", spaceTypeList);


        model.setViewName("book");
        return model;
    }
   

    @RequestMapping(value = "/booking/payment-form", method = RequestMethod.GET)
    public ModelAndView bookingPayment(ModelAndView model, HttpServletRequest request,
    		@ModelAttribute Booking booking) {

    	System.out.println("BookController.bookingPayment()" + request.getSession().getAttribute("booking"));
        model.setViewName("payment-form");
        return model;
    }


    @RequestMapping(value = "/booking/payment-successful", method = RequestMethod.GET)
    public ModelAndView successfulBooking(ModelAndView model, SessionStatus sessionStatus, HttpServletRequest request,
    		@ModelAttribute Booking booking) {


        sessionStatus.setComplete();
        model.setViewName("payment-success");
       // Booking booking = (Booking)request.getSession().getAttribute("booking");
        
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountService.findByAccountname(auth.getName());
        model.addObject("account", account);
        System.out.println("BookController.successfulBooking() "+ booking.getBase_price());
        booking.setAccount(account);
        bookingService.createBooking(booking);
        
        return model;
    }

}
