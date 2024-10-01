package com.enjoytrip.service;

import java.sql.SQLException;
import java.util.List;
import com.enjoytrip.mapper.PlanMapper;
import com.enjoytrip.mapper.TripMapper;
import com.enjoytrip.util.TripRoute;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.enjoytrip.dto.Sido;
import com.enjoytrip.dto.Trip;

@Service
@RequiredArgsConstructor
public class TripServiceImpl implements TripService {

	private final TripMapper tripDao;

	@Override
	public List<Trip> searchTripsByKeyword(String keyword, int sidoCode, int contentTypeId) throws SQLException {
		return tripDao.searchTripsByKeyword(keyword, sidoCode, contentTypeId);
	}

	@Override
	public List<Trip> searchTripsByPlanId(int planId) throws SQLException {
		List<Trip> result = tripDao.searchTripsByPlanId(planId);
		TripRoute tspResult = new TripRoute(result);
		result = tspResult.findTsp();
		return result;
	}

	@Override
	public List<Sido> getSidoData() throws SQLException {
		return tripDao.getSidoData();
	}

	@Override
    public List<Trip> searchHotPlaceTrip() throws SQLException {
        return tripDao.searchHotPlaceTrip();
    }
}