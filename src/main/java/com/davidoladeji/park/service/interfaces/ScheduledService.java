package com.davidoladeji.park.service.interfaces;

import javax.ejb.Local;

/**
 * Created by Daveola on 3/5/2015.
 */

@Local
public abstract interface ScheduledService {

    public abstract void performService();
}
