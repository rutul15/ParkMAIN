package com.davidoladeji.park.service.interfaces;

import com.davidoladeji.park.model.CarparkSpace;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by Daveola on 3/11/2015.
 */

@Local
public interface CarparkSpaceService {

    public void createCarparkSpace(CarparkSpace carparkSpace);

    public List<CarparkSpace> findAllCarparkSpaces();

    public void updateCarparkSpaceById(Long id);

    public CarparkSpace findCarparkSpaceById(Long id);

    public void deleteCarparkSpaceById(Long id);

    public int countAllCarparkSpaces();

    public int countAllAvailableSpaces();

    public List<CarparkSpace> findAllAvailableSpaces();

    public CarparkSpace findOneRegularAvailableSpace();

    public CarparkSpace findOneDisabledAvailableSpace();

    public CarparkSpace findOneFamilyAvailableSpace();

    public List<CarparkSpace> findAllRegularAvailableSpace();

    public List<CarparkSpace> findAllDisabledAvailableSpace();

    public List<CarparkSpace> findAllFamilyAvailableSpace();

    /**
     * Return a list of carpark space in a specified airport and a specified Type and available
     */

    public List<CarparkSpace> findAllByAirportCarparkTypeAndAvaiablity(Long airportId, Long carparkId, Long spaceTypeId, boolean availability);
}
