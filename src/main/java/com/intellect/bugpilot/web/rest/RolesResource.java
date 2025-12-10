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

import com.intellect.bugpilot.service.RolesService;
import com.intellect.bugpilot.service.dto.RolesDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/roles")
public class RolesResource {
	
	@Autowired
	private RolesService rolesService;
	
	@PostMapping("/create_role")
	public ResponseEntity<RolesDTO> createRole(@Valid @RequestBody RolesDTO rolesDTO){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(rolesService.createRole(rolesDTO));
	}
	
	@PutMapping("/update_role")
	public ResponseEntity<RolesDTO> updateRole(@Valid @RequestBody RolesDTO rolesDTO) {
	    RolesDTO updatedRole = rolesService.createRole(rolesDTO);
	    if (updatedRole != null) {
	        return ResponseEntity.ok().body(updatedRole);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}

	
	@GetMapping("/get/{roleId}")
	public ResponseEntity<RolesDTO> findRole(@NotNull @PathVariable Integer roleId){
		return ResponseEntity.status(HttpStatus.FOUND)
				.body(rolesService.findOne(roleId));
	}
	
	@GetMapping("/get_all")
	public ResponseEntity<?> getAllRoles() {
	    List<RolesDTO> roles = rolesService.findAll(); 
	    if (roles.isEmpty()) {
	    	Map<String, String> response = new HashMap<>();
	        response.put("message", "There are no roles");
	        return ResponseEntity.ok(response);
	    }
	    return ResponseEntity.ok(roles); 
	}
	
	@DeleteMapping("/delete/{roleId}")
	public ResponseEntity<Map<String, String>> deleteRole(@NotNull @PathVariable Integer roleId) {
	    rolesService.delete(roleId); 
	    return ResponseEntity.ok(Map.of("message", "Role deleted successfully"));

	}
	
	@GetMapping("/get_all_active_roles")
	public ResponseEntity<Map<String,Integer> > findActiveRoles(){
	    return ResponseEntity.ok(rolesService.findActiveRoles());
	}

}
