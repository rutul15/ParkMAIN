package com.davidoladeji.park.service.rest;

/**
 * Created by Daveola on 2/26/2015.
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRESTConsumer {


        private double conversionresult;
        private String message;

        public double getConversionresult() {
            return conversionresult;
        }

        public void setConversionresult(double conversionresult) {
            this.conversionresult = conversionresult;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }


}
