package com.hotel.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.Dao.HotelDao;
import com.hotel.Exception.ResourceNotFoundException;
import com.hotel.Model.Hotel;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelDao hoteldao;
	
	@Override
	public Hotel gethotel(String id) {
		if(hoteldao.findById(id).isEmpty()==false) {
			Hotel h = hoteldao.findById(id).get();
			return h;
		}
		else {
			throw new ResourceNotFoundException("Resource with id: "+id+" not found !");
		}
		
	}

	@Override
	public List<Hotel> getallhotels() {
		List<Hotel> l = hoteldao.findAll();
		return l;
	}

	@Override
	public Hotel create(Hotel h) {
		this.hoteldao.save(h);
		return h;
	}
	
	

}
