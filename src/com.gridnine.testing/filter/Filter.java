package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;

import java.util.List;

public interface Filter {
    List <Flight> filterBeforeCurrentTime(List<Flight> flights);
    List <Flight> filterHasSegmentWithArrivalBeforeDeparture(List<Flight> flights);
    List <Flight> filterLandingMoreThanTwo(List<Flight> flights);
}
