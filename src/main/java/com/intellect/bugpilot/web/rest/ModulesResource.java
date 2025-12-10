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

import com.intellect.bugpilot.service.ModulesService;
import com.intellect.bugpilot.service.dto.ModulesRequestDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/module")
public class ModulesResource {

	@Autowired
	private ModulesService modulesService;
	
	@PostMapping("/create_module")
	public ResponseEntity<ModulesRequestDTO> createRole(@Valid @RequestBody ModulesRequestDTO modulesRequestDTO){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(modulesService.createUser(modulesRequestDTO));
	}
	
	@PutMapping("/update")
	public ResponseEntity<ModulesRequestDTO> updateUser(@Valid @RequestBody ModulesRequestDTO modulesRequestDTO) {
		ModulesRequestDTO updateModule = modulesService.createUser(modulesRequestDTO); 
	    if (updateModule != null) {
	        return ResponseEntity.ok().body(updateModule);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	@GetMapping("/get/{moduleId}")
	public ResponseEntity<ModulesRequestDTO> getUser(@NotNull @PathVariable Long moduleId) {
		ModulesRequestDTO module = modulesService.findOne(moduleId); 
	    return ResponseEntity.ok(module); 
	}

	@GetMapping("/get_all")
	public ResponseEntity<?> getAllUsers() {
	    List<ModulesRequestDTO> users = modulesService.findAll();
	    if (users.isEmpty()) {
	    	Map<String, String> response = new HashMap<>();
	        response.put("message", "There are no users");
	        return ResponseEntity.ok(response); 
	    }
	    return ResponseEntity.ok(users);
	}

	
	@DeleteMapping("/delete/{moduleId}")
	public ResponseEntity<Void> deleteUser(@NotNull @PathVariable Long moduleId) {
		modulesService.delete(moduleId);  
	    return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/get_all_modules")
	public ResponseEntity<Map<String, Long>> getAllModules() {
		return ResponseEntity.ok(modulesService.getAllModules());
	}
	
	@GetMapping("/get_all_modules_by_project_id/{projectId}")
	public ResponseEntity<Map<String, Long>> getAllModulesByProjectId(@NotNull @PathVariable Long projectId) {
		return ResponseEntity.ok(modulesService.getAllModulesByProjectId(projectId));
		
	    
	}
	
	
}
