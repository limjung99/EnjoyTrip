package com.enjoytrip.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.enjoytrip.dto.Plan;
import com.enjoytrip.dto.Trip;
import com.enjoytrip.mapper.PlanMapper;

@Service
public class PlanServiceImpl implements PlanService {
    private final PlanMapper dao;

    public PlanServiceImpl(PlanMapper dao) {
        super();
        this.dao = dao;
    }

    @Override
    public int makePlanList(String userId, String planName) throws SQLException {
        return dao.makePlanList(userId, planName);

    }

    @Override
    public List<Plan> searchAllPlanIds(String userId) throws SQLException {
        List<Plan> result = dao.searchAllPlanIds(userId);
        return result;
    }

    @Override
    public int addTripPlanList(int planId, int tripId) throws SQLException {
        
        return dao.addTripPlanList(planId, tripId);
    }

	@Override
	public int deleteTripInPlanListByTripId(int planId, int tripId) throws SQLException {
		return dao.deleteTripInPlanListByTripId(planId, tripId);
	}
////
	@Override
    public int deletePlanList(int planId) throws SQLException {
        return dao.deletePlanList(planId);
    }



    
}
