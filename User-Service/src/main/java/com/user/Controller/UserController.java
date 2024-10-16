package com.user.Controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.user.Model.User;
import com.user.Service.UserService;

import feign.Logger;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/app")
	public String app() {
		return "apps";
	}
	@GetMapping("/{id}")
	@CircuitBreaker(name="ratinghotelBreaker", fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<User> finduser(@PathVariable String id) {
		User user = userService.getById(id);
		return ResponseEntity.ok(user);
	} 
	
	public ResponseEntity<User> ratingHotelFallback(String id, Exception ex){
		User user = User.builder().name("Dummy").age(0).about("This user is a dummy user created because some service is down").email("Dummy@gmail.com").userid("12345").build();
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<User> RemoveUser(@PathVariable String id){
		User u = userService.deleteUser(id);
		return ResponseEntity.ok(u);
	}
	
	
	@GetMapping
	@CircuitBreaker(name="ratingHotelBreaker2", fallbackMethod = "listofratingfallback")
	public ResponseEntity<List<User>> getallUsers(){
		List<User> l = userService.getAllUsers();
		return ResponseEntity.ok(l);
	}
	
	public ResponseEntity<List<User>> listofratingfallback(Exception ex){
		List<User> l = new ArrayList<>();
		return new ResponseEntity<>(l,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<User> SaveUser(@RequestBody User user) {
		   User user1 = userService.saveUser(user);
		   return ResponseEntity.status(HttpStatus.CREATED).body(user1);
	}
}
