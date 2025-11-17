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

import com.intellect.bugpilot.service.SubModulesService;
import com.intellect.bugpilot.service.dto.SubModulesRequestDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/sub_modules")
public class SubModulesResource {
	
	@Autowired
	private SubModulesService subModulesService;
	
	
	@PostMapping("/create_sub_module")
	public ResponseEntity<SubModulesRequestDTO> createRole(@Valid @RequestBody SubModulesRequestDTO subModulesRequestDTO){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(subModulesService.createsubModule(subModulesRequestDTO));
	}
	
	@PutMapping("/update")
	public ResponseEntity<SubModulesRequestDTO> updateUser(@Valid @RequestBody SubModulesRequestDTO subModulesRequestDTO) {
	    SubModulesRequestDTO updatedUser = subModulesService.createsubModule(subModulesRequestDTO); 
	    if (updatedUser != null) {
	        return ResponseEntity.ok().body(updatedUser);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	@GetMapping("/get/{subModuleId}")
	public ResponseEntity<SubModulesRequestDTO> getUser(@NotNull @PathVariable Long subModuleId) {
		SubModulesRequestDTO user = subModulesService.findOne(subModuleId); 
	    return ResponseEntity.ok(user); 
	}

	@GetMapping("/get_all")
	public ResponseEntity<?> getAllUsers() {
	    List<SubModulesRequestDTO> users = subModulesService.findAll();
	    if (users.isEmpty()) {
	    	Map<String, String> response = new HashMap<>();
	        response.put("message", "There are no sub-modules");
	        return ResponseEntity.ok(response);
	    }
	    return ResponseEntity.ok(users);
	}

	
	@DeleteMapping("/delete/{subModuleId}")
	public ResponseEntity<String> deleteUser(@NotNull @PathVariable Long subModuleId) {
	    subModulesService.delete(subModuleId); 
	    return ResponseEntity.ok("User with ID " + subModuleId + " has been successfully deleted.");
	}

}
