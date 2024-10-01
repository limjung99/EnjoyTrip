package com.enjoytrip.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.enjoytrip.dto.Trip;
import com.enjoytrip.dto.Sido;

@Mapper
public interface TripMapper {
	
//	int insert(Trip trip) throws SQLException;
	@ResultMap("trip")
	@Select("SELECT content_id, content_type_id , title, sido_code, latitude, longitude, first_image, addr1 "
			+ "FROM attraction_info "
			+ "where content_type_id = #{contentTypeId} "
			+ "and sido_code = #{sidoCode} "
			+ "and title like concat(\"%\",#{keyword},\"%\")")
	List<Trip> searchTripsByKeyword(String keyword,
			@Param("sidoCode") int sidoCode,
			@Param("contentTypeId") int contentTypeId) throws SQLException;
	
	
	@ResultMap("trip")
	@Select("select plan.content_id, content_type_id, title, sido_code, latitude, longitude, first_image, addr1 \r\n"
			+ "from (select * from plan_attraction where plan_id = #{planId}) as plan join (select * from attraction_info) as trip\r\n"
			+ "on plan.content_id = trip.content_id\r\n")
	List<Trip> searchTripsByPlanId(int planId) throws SQLException;
	
	@ResultMap("sido")
	@Select("select sido_code, sido_name from sido")
	List<Sido> getSidoData() throws SQLException;


	@ResultMap("trip")
	@Select("SELECT content_id, content_type_id , title, sido_code, latitude, longitude, first_image, addr1 "
			+ "FROM attraction_info "
			+ "order by rand() "
			+ "limit 10 ")
	List<Trip> searchHotPlaceTrip() throws SQLException;
}
