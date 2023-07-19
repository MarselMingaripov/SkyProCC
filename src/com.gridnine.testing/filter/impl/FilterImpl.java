package com.gridnine.testing.filter.impl;

import com.gridnine.testing.filter.Filter;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class FilterImpl implements Filter {
    private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS");

    @Override
    public List<Flight> filterBeforeCurrentTime(List<Flight> flights) {
        return flights.stream()
                .filter(x -> x.getSegments().stream()
                        .anyMatch(u -> u.getDepartureDate().isBefore(LocalDateTime.now()))
                )
                .collect(Collectors.toList());
    }

    @Override
    public List<Flight> filterHasSegmentWithArrivalBeforeDeparture(List<Flight> flights) {
        return flights.stream()
                .filter(x -> x.getSegments().stream()
                        .anyMatch(u -> u.getArrivalDate().isBefore(u.getDepartureDate()))
                )
                .collect(Collectors.toList());
    }

    @Override
    public List<Flight> filterLandingMoreThanTwo(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> {
                    List<Segment> segments = flight.getSegments();
                    long time = 0;
                    for (int i = 0; i < segments.size() - 1; i++) {
                        Segment currentSegment = segments.get(i);
                        Segment nextSegment = segments.get(i + 1);
                        LocalDateTime arrivalDateTime = LocalDateTime.parse(currentSegment.getArrivalDate().toString(), fmt);
                        LocalDateTime departureDateTime = LocalDateTime.parse(nextSegment.getDepartureDate().toString(), fmt);
                        time += Duration.between(arrivalDateTime, departureDateTime).toHours();
                    }
                    return time > 2;
                })
                .collect(Collectors.toList());
    }
}
