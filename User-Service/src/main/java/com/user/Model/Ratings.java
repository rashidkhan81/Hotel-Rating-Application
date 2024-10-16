package com.user.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ratings {

	
	private String ratingid;
	private int rating;
	private String userid;
	private String hotelid;
	private String feedback;
	private Hotel hotel;
	
	
	
}
