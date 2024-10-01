package com.enjoytrip.service;

import java.sql.SQLException;
import java.util.List;
import com.enjoytrip.dto.Sido;
import com.enjoytrip.dto.Trip;

public interface TripService {
    List<Trip> searchTripsByKeyword(String keyword, int sidoCode, int contentTypeId) throws SQLException;

    List<Trip> searchTripsByPlanId(int planId) throws SQLException;

    List<Sido> getSidoData() throws SQLException;

	List<Trip> searchHotPlaceTrip() throws SQLException;

}
