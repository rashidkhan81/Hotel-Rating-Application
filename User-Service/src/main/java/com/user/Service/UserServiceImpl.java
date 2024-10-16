package com.user.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.discovery.converters.Auto;
import com.user.Dao.UserDao;
import com.user.Exceptions.ResourceNotFoundException;
import com.user.Model.Hotel;
import com.user.Model.Ratings;
import com.user.Model.User;
import com.user.feign.HotelFeginClient;
import com.user.feign.RatingService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private RatingService ratingservice;
	
	@Autowired
	private HotelFeginClient hotelFeginClient;

	@Override
	public User getById(String id) {
	  if(this.userDao.findById(id).isEmpty()==false) {
		  User u = this.userDao.findById(id).get();
		  List<Ratings> l = ratingservice.getRatingsByUserId(id);
		  for(Ratings r : l) {
			  Hotel h = this.hotelFeginClient.getHotel(r.getHotelid());
			  r.setHotel(h);
		  }
		  u.setRating(l);
		  return u;
	  }
	  else {
		  throw new ResourceNotFoundException("User with Id : "+id+" not found!");
	  }
	}

	@Override
	public List<User> getAllUsers() {
		List<User> l = this.userDao.findAll();
		for(User u : l) {
			List<Ratings> listofratings = new ArrayList<>();
			listofratings = this.ratingservice.getRatingsByUserId(u.getUserid());
			for(Ratings r : listofratings) {
				  Hotel h = this.hotelFeginClient.getHotel(r.getHotelid());
				  r.setHotel(h);
			  }
			u.setRating(listofratings);
		}
		return l;
	}
	
	@Override
	public User saveUser(User user) {
		return this.userDao.save(user);
		
	}

	@Override
	public User deleteUser(String id) {
		
		if(userDao.findById(id).isEmpty()==false) {
			User u = this.userDao.getById(id);
			this.userDao.deleteById(id);
			return u;
			
		}
		else {
			throw new ResourceNotFoundException("No resource found with id = "+id+" , hence can't delete");
		}
		 
		
	}

}
