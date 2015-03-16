package com.davidoladeji.park.service.implementation;

import com.davidoladeji.park.model.Carpark;
import com.davidoladeji.park.model.Search;
import com.davidoladeji.park.service.interfaces.CarparkService;
import com.davidoladeji.park.service.interfaces.ScheduledService;
import com.davidoladeji.park.service.interfaces.SearchService;
import org.apache.log4j.Logger;
import org.jboss.spring.callback.SpringLifecycleInterceptor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import java.util.List;


/**
 * Created by Daveola on 3/3/2015.
 */


@Stateless(name = "ScheduledServiceImpl")
@Interceptors(SpringLifecycleInterceptor.class)
public class ScheduledServiceImpl implements ScheduledService {

    protected static Logger logger = Logger.getLogger("service");

    @Autowired
    private SearchService searchService;

    @Autowired
    private CarparkService carparkService;


    public void performService() {
        String threadName = Thread.currentThread().getName();
        logger.debug("   " + threadName + " cron service performing important stuff..");

        List<Search> searchList = searchService.findAllSearches();
        System.out.println("The number of current search(es): " + searchList.size());
        if (searchList.size() >= 2) {
            System.out.println("Delete all searches ...");
            searchService.deleteAllSearch();
        }
    }


    public void performCarparksUpdate() {
        List<Carpark> carparkList = carparkService.findAllCarparks();
        for (int i = 0; i < carparkList.size(); i++) {
            carparkList.get(i).setSpacesavailable();
            if (carparkList.get(i).getSpacesavailable() == 0) {
                carparkList.get(i).setAvailable(false);
            } else if (carparkList.get(i).getSpacesavailable() >= 1) {
                carparkList.get(i).setAvailable(true);
            }

        }
    }


    public void setFamilyAvailable() {
        List<Carpark> carparkList = carparkService.findAllCarparks();
        int familyavailable = 0;
        for (int i = 0; i < carparkList.size(); i++) {

            for (i = 0; i < carparkList.get(i).getCarparkSpaces().size(); i++) {

                if (carparkList.get(i).getCarparkSpaces().iterator().next().getSpaceType().getName().equalsIgnoreCase("family")) {
                    familyavailable++;
                }
                carparkList.get(i).getCarparkSpaces().iterator().next();
            }
            carparkList.get(i).setFamilyavailable(familyavailable);
        }
    }


    public void setDisabledAvailable() {
        List<Carpark> carparkList = carparkService.findAllCarparks();
        int familyavailable = 0;
        for (int i = 0; i < carparkList.size(); i++) {

            for (i = 0; i < carparkList.get(i).getCarparkSpaces().size(); i++) {

                if (carparkList.get(i).getCarparkSpaces().iterator().next().getSpaceType().getName().equalsIgnoreCase("disabled")) {
                    familyavailable++;
                }
                carparkList.get(i).getCarparkSpaces().iterator().next();
            }
            carparkList.get(i).setFamilyavailable(familyavailable);
        }
    }

}
