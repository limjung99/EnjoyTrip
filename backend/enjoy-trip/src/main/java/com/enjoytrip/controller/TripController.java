package com.enjoytrip.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.enjoytrip.dto.Trip;
import com.enjoytrip.dto.Sido;
import com.enjoytrip.service.TripService;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/v1/trip")
public class TripController {

    private final TripService tripService;

    @PostMapping("/search")
    public ResponseEntity<List<Trip>> searchTripsByKeyword( @RequestBody Map<String, Object> requestBody) {
        String keyword = (String) requestBody.get("keyword");
        int sidoCode = (int) requestBody.get("sidoCode");
        int contentTypeId = (int) requestBody.get("contentTypeId");
        List<Trip> result;
        try {
            result = tripService.searchTripsByKeyword(keyword, sidoCode, contentTypeId);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (SQLException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/planTrips/{planId}")
    public ResponseEntity<List<Trip>> searchTripsByPlanIds(@PathVariable("planId") int planId) {
        try {
            List<Trip> result = tripService.searchTripsByPlanId(planId);
            System.out.println(result);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
    }

    @GetMapping("/sido")
    public ResponseEntity<List<Sido>> getSidoData() {
        try {
            List<Sido> result = tripService.getSidoData();
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/hotplace")
    public ResponseEntity<List<Trip>> getHotPlace() {
        try {
            List<Trip> result = tripService.searchHotPlaceTrip();
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
    }

}
