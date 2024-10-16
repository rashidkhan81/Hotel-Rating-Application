package com.hotel.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.Model.Hotel;
import com.hotel.Service.HotelService;

@RestController
@RequestMapping("/hotel")
public class HotelController {

	@Autowired
	private HotelService hotelservice;
	
	@PostMapping
	public ResponseEntity<Hotel> SaveHotel(@RequestBody Hotel h){
		this.hotelservice.create(h);
		return ResponseEntity.status(HttpStatus.CREATED).body(h);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Hotel> GetHotel(@PathVariable String id){
		Hotel h = this.hotelservice.gethotel(id);
		return ResponseEntity.status(HttpStatus.OK).body(h);
	}
	
	@GetMapping
	public ResponseEntity<List<Hotel>> GetAllHotel(){
		return ResponseEntity.ok(hotelservice.getallhotels());
	}
}
