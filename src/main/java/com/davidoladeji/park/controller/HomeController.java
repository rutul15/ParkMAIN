package com.davidoladeji.park.controller;

import com.davidoladeji.park.model.Airport;
import com.davidoladeji.park.model.Search;
import com.davidoladeji.park.model.SpaceType;
import com.davidoladeji.park.service.interfaces.AirportService;
import com.davidoladeji.park.service.interfaces.SpaceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = {"/", "home"})
public class HomeController {

    @Autowired
    AirportService airportService;

    @Autowired
    SpaceTypeService spaceTypeService;


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView homePage(ModelAndView model, @ModelAttribute("search") Search search, BindingResult result) {

        /**
         * Send Space Type List to front-end for drop-down in form
         */
        List<SpaceType> spaceTypeList = spaceTypeService.findAllSpaceTypes();
        model.addObject("spaceTypeList", spaceTypeList);

        List<Airport> airportList = airportService.findAllAirports();
        model.addObject("airportList", airportList);

        model.setViewName("home");
        return model;
    }
}