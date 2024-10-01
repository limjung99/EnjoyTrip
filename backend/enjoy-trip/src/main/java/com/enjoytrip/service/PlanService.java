package com.enjoytrip.service;

import java.sql.SQLException;
import java.util.List;


import com.enjoytrip.dto.Plan;
import com.enjoytrip.dto.Trip;


public interface PlanService {
	int makePlanList(String userId, String planName) throws SQLException;
	List<Plan> searchAllPlanIds(String userId)throws SQLException;
	int addTripPlanList(int planId, int tripId) throws SQLException;
	int deleteTripInPlanListByTripId(int planId, int tripId) throws SQLException;

	int deletePlanList(int planId) throws SQLException;
}
