package com.davidoladeji.park.controller;

import com.davidoladeji.park.model.*;
import com.davidoladeji.park.service.interfaces.*;
import com.davidoladeji.park.service.util.MyTimeDateUtil;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

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
                                       @RequestParam(value = "travelendtime", required = true) String travelendtime, @RequestParam(value = "spaceTypeId", required = false) Long spaceTypeId
    ) {



        //if (result.hasErrors()) {

           // model.setViewName("/home");
           // return model;
     //   }else {

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

        if (search.getSpaceTypeId() == 1) {
            carparkSpaceList = carparkSpaceService.findAllByAirportCarparkTypeAndAvaiablity(search.getAirportid(), carpark.getId(), search.getSpaceTypeId(), false);
        } else if (search.getSpaceTypeId() == 2) {
            carparkSpaceList = carparkSpaceService.findAllByAirportCarparkTypeAndAvaiablity(search.getAirportid(), carpark.getId(), search.getSpaceTypeId(), false);
        } else if (search.getSpaceTypeId() == 3) {
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


        Carpark carpark = carparkService.findCarparkById(carparkId);
        model.addObject("carpark", carpark);

        if (search.getSpaceTypeId() == 1) {
            booking.setCarparkSpace(carparkSpaceService.findOneRegularAvailableSpace());
        } else if (search.getSpaceTypeId() == 2) {
            booking.setCarparkSpace(carparkSpaceService.findOneFamilyAvailableSpace());
        } else if (search.getSpaceTypeId() == 3) {
            booking.setCarparkSpace(carparkSpaceService.findOneDisabledAvailableSpace());
        }


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
    public ModelAndView bookingPayment(ModelAndView model, @ModelAttribute Search search, @ModelAttribute Booking booking) {


        model.setViewName("payment-form");
        return model;
    }


    @RequestMapping(value = "/booking/payment-successful", method = RequestMethod.GET)
    public ModelAndView successfulBooking(ModelAndView model, @ModelAttribute Search search, @ModelAttribute Booking booking, SessionStatus sessionStatus) {


        sessionStatus.isComplete();
        model.setViewName("payment-success");
        return model;
    }

}
