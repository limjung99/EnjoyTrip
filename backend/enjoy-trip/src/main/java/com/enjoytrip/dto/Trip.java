package com.enjoytrip.dto;

import lombok.Data;

@Data
public class Trip {
	int tripId;
	int contentTypeId;
	String title;
	int sidoCode;
	double latitude;
	double longitude;
	String img;
	String address;
}