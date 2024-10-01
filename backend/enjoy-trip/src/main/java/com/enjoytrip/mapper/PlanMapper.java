package com.enjoytrip.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.enjoytrip.dto.Plan;

@Mapper
public interface PlanMapper {

        @Insert("INSERT INTO plan(user_id, plan_name) "
                        + "value(#{userId}, #{planName})")
        int makePlanList(String userId, String planName) throws SQLException;

        @ResultMap("plan")
        @Select("SELECT user_id, plan_id, plan_name from plan where user_id = #{userId}")
        List<Plan> searchAllPlanIds(String userId) throws SQLException;

        @Insert("INSERT plan_attraction(plan_id, content_id) value(#{planId}, #{tripId})")
        int addTripPlanList(int planId, int tripId) throws SQLException;

        @Delete("DELETE from plan_attraction where plan_id=${planId} and content_id = ${tripId}")
        int deleteTripInPlanListByTripId(int planId, int tripId) throws SQLException;

        @Delete("DELETE from plan where plan_id=${planId}")
        int deletePlanList(int planId) throws SQLException;
}
