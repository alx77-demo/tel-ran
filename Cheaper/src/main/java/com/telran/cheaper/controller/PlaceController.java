package com.telran.cheaper.controller;

import java.util.List;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.telran.cheaper.model.Place;
import com.telran.cheaper.repository.PlaceRepository;

@RestController
public class PlaceController {

	@Autowired
	private PlaceRepository placeService;

	@GetMapping("/")
	public String nearestPlaces() {
		Point point = new Point(3.5, 4.5);
		Distance distance = new Distance(100, Metrics.KILOMETERS);
		List<Place> places = placeService.getPlacesNear(point, distance);
		String json = new Gson().toJson(places);
		return json;
	}

	@GetMapping("/places")
	public Callable<String> nearestPlacesAsync(@RequestParam("xlon") Double xlon, @RequestParam("ylat") Double ylat,
			@RequestParam(value = "distkm", required = false, defaultValue = "5") Double distkm) {
		return new Callable<String>() {

			public String call() throws Exception {
				Point point = new Point(xlon, ylat);
				Distance distance = new Distance(distkm, Metrics.KILOMETERS);
				List<Place> places = placeService.getPlacesNear(point, distance);
				String json = new Gson().toJson(places);
				return json;
			}

		};

	}
}