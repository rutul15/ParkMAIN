package com.davidoladeji.park.service.soap;

/**
 * Created by Daveola on 3/10/2015.
 */

//import com.davidoladeji.park.bookingservice.BookingServiceRequest;
//import com.davidoladeji.park.bookingservice.BookingServiceResponse;

import com.davidoladeji.park.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;

@Endpoint
public class BookingEndPoint {
    private static final String NAMESPACE_URI = "http://com/davidoladeji/park/bookingservice";

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    public BookingEndPoint(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

  /*  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "BookingServiceRequest")
    @ResponsePayload
    public BookingServiceResponse getBooking(@RequestPayload BookingServiceRequest request) {
        BookingServiceResponse response = new BookingServiceResponse();
        response.setBooking(bookingRepository.findByCarRegistration(request.getCarRegistration()));

        return response;
    }*/
}
