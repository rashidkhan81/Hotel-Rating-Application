package com.user.feign;

import org.springframework.cloud.openfeign.FeignClient;
import com.user.Model.Hotel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@FeignClient(name="HOTEL-SERVICE")
public interface HotelFeginClient {

	@GetMapping("/hotel/{hotelid}")
	Hotel getHotel(@PathVariable String hotelid);
	
}
