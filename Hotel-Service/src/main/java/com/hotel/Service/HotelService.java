package com.hotel.Service;

import java.util.List;

import com.hotel.Model.Hotel;

public interface HotelService {

	public Hotel create(Hotel h);
	public Hotel gethotel(String id);
	public List<Hotel> getallhotels();
	
	
}
