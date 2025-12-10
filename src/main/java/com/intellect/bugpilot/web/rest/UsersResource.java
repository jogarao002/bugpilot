package com.intellect.bugpilot.web.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intellect.bugpilot.service.UsersService;
import com.intellect.bugpilot.service.dto.UsersRequestDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/users")
public class UsersResource {
	
	@Autowired
	private UsersService usersService;
	
	@PostMapping("/create_users")
	public ResponseEntity<UsersRequestDTO> createRole(@Valid @RequestBody UsersRequestDTO usersRequestDTO){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(usersService.createUser(usersRequestDTO));
	}
	
	@PutMapping("/update")
	public ResponseEntity<UsersRequestDTO> updateUser(@Valid @RequestBody UsersRequestDTO usersRequestDTO) {
	    UsersRequestDTO updatedUser = usersService.createUser(usersRequestDTO); 
	    if (updatedUser != null) {
	        return ResponseEntity.ok().body(updatedUser);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	@GetMapping("/get/{userId}")
	public ResponseEntity<UsersRequestDTO> getUser(@NotNull @PathVariable Long userId) {
		UsersRequestDTO user = usersService.findOne(userId); 
	    return ResponseEntity.ok(user); 
	}

	@GetMapping("/get_all")
	public ResponseEntity<?> getAllUsers() {
	    List<UsersRequestDTO> users = usersService.findAll();
	    if (users.isEmpty()) {
	    	Map<String, String> response = new HashMap<>();
	        response.put("message", "There are no users");
	        return ResponseEntity.ok(response);
	    }
	    return ResponseEntity.ok(users);
	}

	
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<Map<String, String>> deleteUser(@NotNull @PathVariable Long userId) {
	    usersService.delete(userId); 
	    return ResponseEntity.ok(Map.of("message", "Role deleted successfully"));
	}
	
	@GetMapping("/get_all_Active_users")
	public ResponseEntity<Map<String, Long>> getAllActiveUsers() {
		return ResponseEntity.ok(usersService.getAllActiveUsers());
	}
	
	@GetMapping("/get_all_Active_team_lead_users")
	public ResponseEntity<Map<String, Long>> getAllActiveTeamLeadUsers() {
		return ResponseEntity.ok(usersService.getAllActiveTeamLeadUsers());
	}

}
