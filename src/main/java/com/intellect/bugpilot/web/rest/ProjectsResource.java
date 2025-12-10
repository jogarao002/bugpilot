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

import com.intellect.bugpilot.service.ProjectService;
import com.intellect.bugpilot.service.dto.ProjectsDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;


@RestController
@RequestMapping("/projects")
public class ProjectsResource {
	
	@Autowired
	private ProjectService projectService;
	
	@PostMapping("/create_project")
	public ResponseEntity<ProjectsDTO> createProject(@Valid @RequestBody ProjectsDTO projectsDTO) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(projectService.createProject(projectsDTO));
		
	}
	
	@PutMapping("/update_project")
    public ResponseEntity<ProjectsDTO> updateProject(@Valid @RequestBody ProjectsDTO projectsDTO) {
        ProjectsDTO updatedProject = projectService.createProject(projectsDTO);
        if (updatedProject != null) {
	        return ResponseEntity.ok().body(updatedProject);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
    }
	
	@GetMapping("/get/{projectId}")
	public ResponseEntity<ProjectsDTO> findProject(@NotNull @PathVariable Long projectId) {
		ProjectsDTO project = projectService.findOne(projectId);
		return ResponseEntity.ok(project);
	}
	
	@GetMapping("/get_all")
	public ResponseEntity<?> getAllProjects() {
		List<ProjectsDTO> projects = projectService.findAll();
		if (projects.isEmpty()) {
			Map<String, String> response = new HashMap<>();
	        response.put("message", "There are no projects");
	        return ResponseEntity.ok(response);
		}
		return ResponseEntity.ok(projects);
	}
	
	@DeleteMapping("/delete/{projectId}")
	public ResponseEntity<Map<String, String>> deleteProject(@NotNull @PathVariable Long projectId) {
		projectService.delete(projectId);
		 return ResponseEntity.ok(Map.of("message", "Role deleted successfully"));
	}
	
	@GetMapping("/get_all_Active_projects")
	public ResponseEntity<Map<String, Long>> getAllActiveProjects() {
		return ResponseEntity.ok(projectService.getAllActiveProjects());
	}

}
