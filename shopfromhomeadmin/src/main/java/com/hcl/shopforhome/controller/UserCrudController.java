package com.hcl.shopforhome.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.hcl.shopforhome.bean.UserDetails;
import com.hcl.shopforhome.service.UserService;

@RestController
@RequestMapping("/UserCRUDS")
@CrossOrigin
public class UserCrudController {


	@Autowired
	UserService userService;
	
	@PostMapping(value = "createUser", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String CreateUserInfo(@RequestBody UserDetails user) {
		return userService.createUser(user);
	}

	@GetMapping(value = "users", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserDetails> retrieveUserDetails() {
		return userService.retrieveUser();
	}

	@PutMapping("/updateUser")
	public String updateUser(@RequestBody UserDetails user){
		String users=userService.updateUser(user);
		return users;
	}

	@DeleteMapping(value = "deleteUser/{id}")
	public String deleteUserDetails(@PathVariable("id") int id) {
		return userService.deleteUser(id);
	}
	/*@GetMapping(value = "getAllUsers")
	public List<Object> getAllUser() {
		String Url = "http://user-service:8282/UserActivity/retrieveUsers";
		Object[] res = restTemplate.getForObject(Url, Object[].class);
		return Arrays.asList(res);
	}

	@PostMapping(value = "storeUser", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String storeUser(@RequestBody Object object) {
		String Url = "http://user-service:8282/UserActivity/createUser/";
		ResponseEntity<String> user = restTemplate.postForEntity(Url, object, String.class);
		return user.getBody();
	}

	@PutMapping(value = "updateUserInfo", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String updateUserPassword(@RequestBody UserDetails user) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<UserDetails> entity = new HttpEntity<>(user, headers);
		String url = "http://user-service:8282/UserActivity/updateUser";
		return restTemplate.exchange(url, HttpMethod.PUT, entity, String.class).getBody();

	}

	@DeleteMapping(value = "deleteUserInfo/{id}")
	public String deleteUserInfo(@PathVariable("id") int id) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<UserDetails> entity = new HttpEntity<>(headers);
		String url = "http://user-service:8282/UserActivity/deleteUser/" + id;
		return restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class).getBody();

	}
*/
}
