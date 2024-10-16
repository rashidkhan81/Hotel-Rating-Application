package com.rating.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rating.Model.Ratings;

@Repository
public interface RatingsDao extends JpaRepository<Ratings, String>{

	 List<Ratings> findByUserid(String userid); 
	 List<Ratings> findByHotelid(String hotelid);
	
}
