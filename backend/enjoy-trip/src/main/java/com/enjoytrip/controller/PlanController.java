package com.enjoytrip.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.enjoytrip.dto.Plan;
import com.enjoytrip.service.PlanService;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/plan")
public class PlanController {

    private final PlanService service;

    @PostMapping("")
    public ResponseEntity<String> makePlanList(@RequestBody Map<String, String> requestBody) {
        String userId = requestBody.get("userId");
        String planName = requestBody.get("planName");
        try {
            service.makePlanList(userId, planName);
            return ResponseEntity.status(HttpStatus.OK).body("plan 생성 완료");
        } catch (SQLException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body("plan 생성 실패");
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Plan>> searchAllPlanIds(@PathVariable String userId) {
        try {
            List<Plan> result = service.searchAllPlanIds(userId);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> addTripPlanList(@RequestBody Map<String, Integer> requestBody) {
        int planId = requestBody.get("planId");
        int tripId = requestBody.get("tripId");
        try {
            service.addTripPlanList(planId, tripId);
            return ResponseEntity.status(HttpStatus.OK).body("insert success");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body("insert fail");
        }
    }
    
    @DeleteMapping("/delete/{planId}/{tripId}")
    public ResponseEntity<String> deleteTripInPlanListByTripId(@PathVariable("planId") int planId, @PathVariable("tripId") int tripId){
        try {
            service.deleteTripInPlanListByTripId(planId, tripId);
            return ResponseEntity.status(HttpStatus.OK).body("delete success");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body("delete fail");
        }
    }

    @DeleteMapping("/delete/{planId}")
    public ResponseEntity<String> deletePlanList(@PathVariable("planId") int planId){
        try {
            service.deletePlanList(planId);
            return ResponseEntity.status(HttpStatus.OK).body("delete success");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body("delete fail");
        }
    }
}
