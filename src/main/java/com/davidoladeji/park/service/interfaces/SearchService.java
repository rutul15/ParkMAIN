package com.davidoladeji.park.service.interfaces;

import com.davidoladeji.park.model.Search;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by Daveola on 3/12/2015.
 */

@Local
public interface SearchService {

    public void saveSearch(Search search);

    public List<Search> findAllSearches();

    public void deleteSearch(Search search);

    public void deleteAllSearch();
}
