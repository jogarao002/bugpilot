package com.intellect.bugpilot.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.intellect.bugpilot.domain.IssueImages;
import com.intellect.bugpilot.domain.Issues;
import com.intellect.bugpilot.domain.Modules;
import com.intellect.bugpilot.domain.Projects;
import com.intellect.bugpilot.domain.Users;
import com.intellect.bugpilot.exception.ResourceNotFoundException;
import com.intellect.bugpilot.repository.IssueImagesRepository;
import com.intellect.bugpilot.repository.IssuesRepository;
import com.intellect.bugpilot.service.IssuesService;
import com.intellect.bugpilot.service.dto.IssueStatusEnum;
import com.intellect.bugpilot.service.dto.IssuesRequestDTO;
import com.intellect.bugpilot.service.dto.ModulesRequestDTO;
import com.intellect.bugpilot.service.dto.ProjectsDTO;
import com.intellect.bugpilot.service.dto.UsersRequestDTO;
import com.intellect.bugpilot.util.FileUploadService;

@Service
public class issuesServiceImpl implements IssuesService {
	
	@Autowired
	private ProjectsServiceImpl projectsServiceImpl;
	
	@Autowired
	private ModulesServiceImpl modulesServiceImpl;
	
	@Autowired
	private UsersServiceImpl usersServiceImpl;
	
	@Autowired
	private IssuesRepository issuesRepository;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@Autowired
	private IssueImagesRepository issueImagesRepository;

	@Override
	public IssuesRequestDTO createIssue(IssuesRequestDTO issuesRequestDTO) {
		UsersRequestDTO assignedToDTO = null;
		UsersRequestDTO assignedByDTO = null;
		Users assignedBy = null;
		Users assignedTo = null;
		String filePath = null;
		ProjectsDTO projectsDTO = projectsServiceImpl.findOne(issuesRequestDTO.getProjectId());
		
		ModulesRequestDTO modulesRequestDTO = modulesServiceImpl.findOne(issuesRequestDTO.getModuleId());
		
		UsersRequestDTO raisedByDTO = usersServiceImpl.findOne(issuesRequestDTO.getRaisedBy());
		
		if (issuesRequestDTO.getIssueId() != null
				&& issuesRequestDTO.getAssignedTo() != null) {
			assignedToDTO = usersServiceImpl.findOne(issuesRequestDTO.getAssignedTo());
			assignedTo = new Users.UsersBuilder()
					.userId(assignedToDTO.getUserId())
					.build();
		}
		if (issuesRequestDTO.getIssueId() != null
				&& issuesRequestDTO.getAssignedBy() != null) {
			assignedByDTO = usersServiceImpl.findOne(issuesRequestDTO.getAssignedBy());
			assignedBy = new Users.UsersBuilder()
					.userId(assignedByDTO.getUserId())
					.build();
		}
		
		Projects projects = new Projects.ProjectsBuilder()
										.projectId(projectsDTO.getProjectId())
										.build();
		
		Modules modules = new Modules.ModulesBuilder()
										.moduleId(modulesRequestDTO.getModuleId())
										.build();
		
		Users raisedBy = new Users.UsersBuilder()
									.userId(raisedByDTO.getUserId())
									.build();
		
		Issues issues = new Issues.IssuesBuilder()
							.issueId((issuesRequestDTO.getIssueId() != null) ? issuesRequestDTO.getIssueId():null)
							.issueName(issuesRequestDTO.getIssueName())
							.projects(projects)
							.modules(modules)
							.description(issuesRequestDTO.getDescription())
							.raisedBy(raisedBy)
							.assignedTo(assignedTo)
							.assignedBy(assignedBy)
							.priority(issuesRequestDTO.getPriority())
							.status(issuesRequestDTO.getStatus())
							.build();
		
		issues = issuesRepository.save(issues);
		
		try {
	        // Decode & store image
			if(issues.getIssueId() == null) {
				
				filePath = fileUploadService.saveBase64File(issuesRequestDTO.getImageProof());
				IssueImages issueImages = new IssueImages.IssueImagesBuilder()
						.issues(issues)
						.filePath(filePath)
						.build();
				issueImagesRepository.save(issueImages);
			}
	    }
	    catch (Exception e) {
	    }
		
		return new IssuesRequestDTO.IssuesRequestDTOBuilder()
		        .issueId(issues.getIssueId())
		        .issueName(issues.getIssueName())
		        .projectId(issues.getProjects().getProjectId())
		        .moduleId(issues.getModules().getModuleId())
		        .description(issues.getDescription())
		        .raisedBy(issues.getRaisedBy().getUserId())
		        .imageProof(filePath)
		        .assignedTo(
		            issues.getAssignedTo() != null 
		                ? issues.getAssignedTo().getUserId() 
		                : null
		        )
		        .assignedBy(
		            issues.getAssignedBy() != null 
		                ? issues.getAssignedBy().getUserId() 
		                : null
		        )
		        .priority(issues.getPriority())
		        .status(issues.getStatus())
		        .build();

	}

	@Override
	public void deleteIssue(Long issueId) {
		UsersRequestDTO assignedToDTO = null;
		UsersRequestDTO assignedByDTO = null;
		Users assignedBy = null;
		Users assignedTo = null;
		IssuesRequestDTO issuesRequestDTO = getIssueById(issueId);
		Users raisedBy = new Users.UsersBuilder()
				.userId(issuesRequestDTO.getRaisedBy())
				.build();
		Projects projects = new Projects.ProjectsBuilder()
				.projectId(issuesRequestDTO.getProjectId())
				.build();
		Modules modules = new Modules.ModulesBuilder()
				.moduleId(issuesRequestDTO.getModuleId())
				.build();
		if (issuesRequestDTO.getIssueId() != null
				&& issuesRequestDTO.getAssignedTo() != null) {
			assignedToDTO = usersServiceImpl.findOne(issuesRequestDTO.getAssignedTo());
			assignedTo = new Users.UsersBuilder()
					.userId(assignedToDTO.getUserId())
					.build();
		}
		if (issuesRequestDTO.getIssueId() != null
				&& issuesRequestDTO.getAssignedBy() != null) {
			assignedByDTO = usersServiceImpl.findOne(issuesRequestDTO.getAssignedBy());
			assignedBy = new Users.UsersBuilder()
					.userId(assignedByDTO.getUserId())
					.build();
		}
		Issues issues = new Issues.IssuesBuilder()
				.issueId((issuesRequestDTO.getIssueId() != null) ? issuesRequestDTO.getIssueId():null)
				.issueName(issuesRequestDTO.getIssueName())
				.projects(projects)
				.modules(modules)
				.description(issuesRequestDTO.getDescription())
				.raisedBy(raisedBy)
				.assignedTo(assignedTo)
				.assignedBy(assignedBy)
				.priority(issuesRequestDTO.getPriority())
				.status(IssueStatusEnum.CLOSE)
				.build();
		issues = issuesRepository.save(issues);

	}

	@Override
	public IssuesRequestDTO getIssueById(Long issueId) {
		IssuesRequestDTO issuesRequestDTO =null;
		Issues issues = issuesRepository.findById(issueId).orElseThrow(() -> new ResourceNotFoundException("Issue Not Found with ID: " + issueId));
		IssueImages issueImages = issueImagesRepository.findById(issueId)
				.orElseThrow(() -> new ResourceNotFoundException("Issue Not Found with ID: " + issueId));
		if(!ObjectUtils.isEmpty(issues)) {
			issuesRequestDTO = new IssuesRequestDTO.IssuesRequestDTOBuilder()
			        .issueId(issues.getIssueId())
			        .issueName(issues.getIssueName())
			        .projectId(issues.getProjects().getProjectId())
			        .moduleId(issues.getModules().getModuleId())
			        .description(issues.getDescription())
			        .raisedBy(issues.getRaisedBy().getUserId())
			        .imageProof(issueImages.getFilePath())
			        .assignedTo(
			            issues.getAssignedTo() != null 
			                ? issues.getAssignedTo().getUserId() 
			                : null
			        )
			        .assignedBy(
			            issues.getAssignedBy() != null 
			                ? issues.getAssignedBy().getUserId() 
			                : null
			        )
			        .priority(issues.getPriority())
			        .status(issues.getStatus())
			        .build();
		}

		return issuesRequestDTO;
	}

	@Override
	public List<IssuesRequestDTO> getAllIssues() {
		List<IssuesRequestDTO> issuesRequestDTOlist = new ArrayList<>();
		List<Issues> issuesList = issuesRepository.findAll();
		if(!CollectionUtils.isEmpty(issuesList)) {
			issuesList.forEach(issues -> {
				IssueImages issueImages = issueImagesRepository.findById(issues.getIssueId())
						.orElseThrow(() -> new ResourceNotFoundException("Issue Not Found with ID: " + issues.getIssueId()));
				IssuesRequestDTO issuesRequestDTO = new IssuesRequestDTO.IssuesRequestDTOBuilder()
				        .issueId(issues.getIssueId())
				        .issueName(issues.getIssueName())
				        .projectId(issues.getProjects().getProjectId())
				        .moduleId(issues.getModules().getModuleId())
				        .description(issues.getDescription())
				        .raisedBy(issues.getRaisedBy().getUserId())
				        .imageProof(issueImages.getFilePath())
				        .assignedTo(
				            issues.getAssignedTo() != null 
				                ? issues.getAssignedTo().getUserId() 
				                : null
				        )
				        .assignedBy(
				            issues.getAssignedBy() != null 
				                ? issues.getAssignedBy().getUserId() 
				                : null
				        )
				        .priority(issues.getPriority())
				        .status(issues.getStatus())
				        .build();
				issuesRequestDTOlist.add(issuesRequestDTO);
			});
		}
		
		return issuesRequestDTOlist;
	}

}
