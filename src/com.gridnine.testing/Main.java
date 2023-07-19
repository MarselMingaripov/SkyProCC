package com.gridnine.testing;

import com.gridnine.testing.factory.FlightBuilder;
import com.gridnine.testing.filter.Filter;
import com.gridnine.testing.filter.impl.FilterImpl;

public class Main {
    public static void main(String[] args) {
        FlightBuilder.createFlights().forEach(System.out::println);
        Filter filter = new FilterImpl();
        System.out.println("-----------------------");
        System.out.println("����� �� �������� ������� �������");
        System.out.println(filter.filterBeforeCurrentTime(
                FlightBuilder.createFlights()));

        System.out.println("-----------------------");
        System.out.println("������� �������� � ����� ������ ������ ���� ������");
        System.out.println(filter.filterHasSegmentWithArrivalBeforeDeparture(
                FlightBuilder.createFlights()));

        System.out.println("-----------------------");
        System.out.println("����� �����, ���������� �� ����� ��������� ��� ����");
        System.out.println(filter.filterLandingMoreThanTwo(
                FlightBuilder.createFlights()
        ));
    }
}