package com.rating.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rating.Model.Ratings;
import com.rating.Service.RatingsService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/ratings")
public class RatingsController {

	@Autowired
	private RatingsService ratingservice;
	
	@PostMapping
	public ResponseEntity<Ratings> SaveRatings(@RequestBody Ratings r){
		return ResponseEntity.status(HttpStatus.CREATED).body(this.ratingservice.Create(r));
	}
	
	@GetMapping("/{id}")
	@CircuitBreaker(name="hotelbreaker1", fallbackMethod = "hotelbreakerFallback")
	public ResponseEntity<Ratings> GetRating(@PathVariable String id){
		return ResponseEntity.ok(this.ratingservice.GetRating(id));
	}
	
	public ResponseEntity<Ratings> hotelbreakerFallback(String id){
		Ratings r = Ratings.builder().ratingid("12345").rating(0).userid("2ffewygf7r").hotelid("renfui3847bg").hotel(null).feedback("This is dummy feedback , this rating object is dummy object.").build();
		return new ResponseEntity(r,HttpStatus.OK);
	}
	
	@GetMapping
	@CircuitBreaker(name="hotelbreaker2", fallbackMethod = "listhotelfallback")
	public ResponseEntity<List<Ratings>> getAllRatings(){
		return ResponseEntity.ok(this.ratingservice.GetAllRatings());
	}
	
	public ResponseEntity<List<Ratings>> listhotelfallback(){
		List<Ratings> l = new ArrayList<>();
		return new ResponseEntity<>(l,HttpStatus.OK);
	}
	
	@GetMapping("/user/{userid}")
	public ResponseEntity<List<Ratings>> getRatingsByUserId(@PathVariable String userid){
		System.out.println(this.ratingservice.GetByUserId(userid).size());
		return ResponseEntity.ok(this.ratingservice.GetByUserId(userid));
	}
	
	@GetMapping("/hotel/{hotelid}")
	public ResponseEntity<List<Ratings>> getRatingsByHotelId(@PathVariable String hotelid){
		return ResponseEntity.ok(this.ratingservice.GetByHotelId(hotelid));
	}
	
	@DeleteMapping("/{id}")
	public void DeleteRating(@PathVariable String id){
		this.ratingservice.DeleteRatingsByRatingId(id);
	}
}
