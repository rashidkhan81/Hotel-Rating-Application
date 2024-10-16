package com.user.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.user.Model.Ratings;

@FeignClient(name="RATING-SERVICE")
public interface RatingService {

	 @GetMapping("/ratings/user/{userId}")
	 List<Ratings> getRatingsByUserId(@PathVariable("userId") String userId);
}
