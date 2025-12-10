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
import com.intellect.bugpilot.domain.SubModules;
import com.intellect.bugpilot.domain.Users;
import com.intellect.bugpilot.exception.ResourceNotFoundException;
import com.intellect.bugpilot.repository.IssueImagesRepository;
import com.intellect.bugpilot.repository.IssuesRepository;
import com.intellect.bugpilot.service.IssuesService;
import com.intellect.bugpilot.service.dto.IssueCommentsRequestDTO;
import com.intellect.bugpilot.service.dto.IssueStatusEnum;
import com.intellect.bugpilot.service.dto.IssuesHistoryRequestDTO;
import com.intellect.bugpilot.service.dto.IssuesRequestDTO;
import com.intellect.bugpilot.service.dto.IssuesResponseDTO;
import com.intellect.bugpilot.service.dto.IssuesResponseDTO.IssuesResponseDTOBuilder;
import com.intellect.bugpilot.service.dto.ModulesRequestDTO;
import com.intellect.bugpilot.service.dto.ProjectsDTO;
import com.intellect.bugpilot.service.dto.SubModulesRequestDTO;
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
	
	@Autowired
	private SubModulesServiceImpl subModulesServiceImpl;
	
	@Autowired
	private IssuesHistoryServiceImpl issuesHistoryServiceImpl;
	
	@Autowired
	private IssueCommentsServiceImpl issueCommentsServiceImpl; 
	
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
		SubModules subModules = null;
		if (issuesRequestDTO.getSubModuleId() != null) {
		    SubModulesRequestDTO subModulesRequestDTO = subModulesServiceImpl.findOne(issuesRequestDTO.getSubModuleId());
		    subModules = new SubModules.SubModulesBuilder()
					.subModuleId(subModulesRequestDTO.getSubModuleId())
					.build();
		}

		Users raisedBy = new Users.UsersBuilder()
									.userId(raisedByDTO.getUserId())
									.build();
		
		Users raisedTo = new Users.UsersBuilder()
				.userId(issuesRequestDTO.getRaisedTo())
				.build();
		
		Issues issues = new Issues.IssuesBuilder()
							.issueId((issuesRequestDTO.getIssueId() != null) ? issuesRequestDTO.getIssueId():null)
							.issueName(issuesRequestDTO.getIssueName())
							.projects(projects)
							.modules(modules)
							.subModules((subModules != null) ? subModules : null)
							.description(issuesRequestDTO.getDescription())
							.raisedBy(raisedBy)
							.raisedTo(raisedTo)
							.assignedTo(assignedTo)
							.assignedBy(assignedBy)
							.priority(issuesRequestDTO.getPriority())
							.status(issuesRequestDTO.getStatus())
							.build();
		
		issues = issuesRepository.save(issues);
		
		try {
	        // Decode & store image
			if(issuesRequestDTO.getIssueId() == null) {
				
				filePath = fileUploadService.saveBase64File(issuesRequestDTO.getImageProof());
				IssueImages issueImages = new IssueImages.IssueImagesBuilder()
						.issues(issues)
						.filePath(filePath)
						.build();
				issueImagesRepository.save(issueImages);
			} else {
				if (isBase64(issuesRequestDTO.getImageProof())) {
					IssueImages issueImages = issueImagesRepository.findByIssues(issues);
					if (issueImages != null) {
						String oldFilePath = issueImages.getFilePath();
						fileUploadService.deleteFile(oldFilePath);
						filePath = fileUploadService.saveBase64File(issuesRequestDTO.getImageProof());

						IssueImages updatedImage = new IssueImages.IssueImagesBuilder()
								.issueImageId(issueImages.getIssueImageId()).issues(issues).filePath(filePath).build();

						issueImagesRepository.save(updatedImage);
					}
				}
			}
	    }
	    catch (Exception e) {
	    }
		
		IssuesHistoryRequestDTO issuesHistoryRequestDTO = new IssuesHistoryRequestDTO.IssuesHistoryRequestDTOBuilder()
																	.oldStatus(issues.getStatus()) 
																	.issueId(issues.getIssueId())
																	.userId(issuesRequestDTO.getUserId())
																	.build();
		
		issuesHistoryServiceImpl.createIssueHistory(issuesHistoryRequestDTO);
		
		IssueCommentsRequestDTO issueCommentsRequestDTO = new IssueCommentsRequestDTO.IssueCommentsRequestDTOBuilder()
							.commentText(issuesRequestDTO.getComment())
							.issueId(issues.getIssueId())
							.userId(issuesRequestDTO.getUserId())
							.build();		
		
		issueCommentsServiceImpl.createIssueComment(issueCommentsRequestDTO);
		
		return new IssuesRequestDTO.IssuesRequestDTOBuilder()
		        .issueId(issues.getIssueId())
		        .issueName(issues.getIssueName())
		        .projectId(issues.getProjects().getProjectId())
		        .moduleId(issues.getModules().getModuleId())
		        .description(issues.getDescription())
		        .raisedBy(issues.getRaisedBy().getUserId())
		        .raisedTo(issues.getRaisedTo().getUserId())
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
	
	private boolean isBase64(String str) {
	    if (str == null || str.isEmpty()) return false;
	    // Basic check: Base64 strings are usually long and don't start with http
	    return !str.startsWith("http");
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
				.status(IssueStatusEnum.INACTIVE)
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
			        .raisedTo(issues.getRaisedTo().getUserId())
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
	public IssuesResponseDTO getAllIssues() {
		IssuesResponseDTOBuilder issuesResponseDTOBuilder = new IssuesResponseDTO.IssuesResponseDTOBuilder();
		List<Issues> issuesList = issuesRepository.findAll();
		SettingDisplayCountDetails(issuesList, issuesResponseDTOBuilder);
		return settingIssueDetails(issuesResponseDTOBuilder, issuesList);
	}

	@Override
	public IssuesResponseDTO getIssueByRaisedById(Long raisedBy) {
		IssuesResponseDTOBuilder issuesResponseDTOBuilder = new IssuesResponseDTO.IssuesResponseDTOBuilder();
		Users users = new Users.UsersBuilder()
							.userId(raisedBy)
							.build();
		List<Issues> issuesList = issuesRepository.findByRaisedBy(users);
		SettingDisplayCountDetails(issuesList, issuesResponseDTOBuilder);
		return settingIssueDetails(issuesResponseDTOBuilder, issuesList);
	}

	@Override
	public IssuesResponseDTO getIssueByRaisedTo(Long raisedTo) {
		IssuesResponseDTOBuilder issuesResponseDTOBuilder = new IssuesResponseDTO.IssuesResponseDTOBuilder();
		Users users = new Users.UsersBuilder()
							.userId(raisedTo)
							.build();
		List<Issues> issuesList = issuesRepository.findByRaisedTo(users);
		SettingDisplayCountDetails(issuesList, issuesResponseDTOBuilder);
		return settingIssueDetails(issuesResponseDTOBuilder, issuesList);
	}

	@Override
	public IssuesResponseDTO getIssueByAssignedTo(Long assignedTo) {
		IssuesResponseDTOBuilder issuesResponseDTOBuilder = new IssuesResponseDTO.IssuesResponseDTOBuilder();
		Users users = new Users.UsersBuilder()
							.userId(assignedTo)
							.build();
		List<Issues> issuesList = issuesRepository.findByAssignedTo(users);
		SettingDisplayCountDetails(issuesList, issuesResponseDTOBuilder);
		return settingIssueDetails(issuesResponseDTOBuilder, issuesList);
	}
	
	private void SettingDisplayCountDetails(List<Issues> issuesList, IssuesResponseDTOBuilder issuesResponseDTOBuilder) {

	    if (issuesList == null || issuesList.isEmpty()) {
	        return;
	    }

	    long raisedIssues = issuesList.size();
	    long resolvedIssues = issuesList.stream()
	            .filter(i -> IssueStatusEnum.CLOSE.equals(i.getStatus()))
	            .count();

	    long assignedIssues = issuesList.stream()
	            .filter(i -> i.getStatus() != null && i.getStatus() != IssueStatusEnum.NEW)
	            .count();

	    long pendingIssues = issuesList.stream()
	            .filter(i -> i.getStatus() != null &&
	                         i.getStatus() != IssueStatusEnum.NEW &&
	                         i.getStatus() != IssueStatusEnum.CLOSE)
	            .count();
	    
	    issuesResponseDTOBuilder.assignedIssues(assignedIssues);
	    issuesResponseDTOBuilder.raisedIssues(raisedIssues);
	    issuesResponseDTOBuilder.resolvedIssues(resolvedIssues);
	    issuesResponseDTOBuilder.pendingIssues(pendingIssues);

	}

	private IssuesResponseDTO settingIssueDetails(IssuesResponseDTOBuilder issuesResponseDTOBuilder,
			List<Issues> issuesList) {
		List<IssuesRequestDTO> issuesRequestDTOlist = new ArrayList<>();
		if(!CollectionUtils.isEmpty(issuesList)) {
			issuesList.forEach(issues -> {
				ProjectsDTO projectsDTO = null;
				ModulesRequestDTO modulesRequestDTO = null;
				SubModulesRequestDTO subModulesRequestDTO = null;
				UsersRequestDTO raisedByName = null;
				UsersRequestDTO assignedToName = null;
				UsersRequestDTO assignedByName = null;
				
				Issues issuesForImage = new Issues.IssuesBuilder()
				.issueId(issues.getIssueId()).build();
				
				IssueImages issueImages = issueImagesRepository.findByIssues(issuesForImage);
				if(null != issues.getProjects().getProjectId()) {
					projectsDTO = projectsServiceImpl.findOne(issues.getProjects().getProjectId());
				}
				if(null != issues.getModules().getModuleId()) {
					modulesRequestDTO = modulesServiceImpl.findOne(issues.getModules().getModuleId());
				}
				
				if(issues.getSubModules() != null && issues.getSubModules().getSubModuleId() != null) {
					subModulesRequestDTO = subModulesServiceImpl.findOne(issues.getSubModules().getSubModuleId());
				}
				if(issues.getAssignedTo() != null && issues.getAssignedTo().getUserId() != null) {
					assignedToName = usersServiceImpl.findOne(issues.getAssignedTo().getUserId());
				}
				if(null != issues.getRaisedBy().getUserId()) {
					raisedByName = usersServiceImpl.findOne(issues.getRaisedBy().getUserId());
				}
				if(issues.getAssignedBy() != null && issues.getAssignedBy().getUserId() != null) {
					assignedByName = usersServiceImpl.findOne(issues.getAssignedBy().getUserId());
				}
				IssuesRequestDTO issuesRequestDTO = new IssuesRequestDTO.IssuesRequestDTOBuilder()
				        .issueId(issues.getIssueId())
				        .issueName(issues.getIssueName())
				        .projectId(issues.getProjects().getProjectId())
				        .moduleId(issues.getModules().getModuleId())
				        .subModuleId(issues.getSubModules() != null 
				                	? issues.getSubModules().getSubModuleId()
				                    : null)
				        .description(issues.getDescription())
				        .raisedBy(issues.getRaisedBy().getUserId())
				        .raisedTo(issues.getRaisedTo().getUserId())
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
				        .projectName(projectsDTO.getProjectName())
				        .moduleName(modulesRequestDTO.getModuleName())
				        .subModuleName(subModulesRequestDTO != null 
				        				? subModulesRequestDTO.getSubModuleName()
				        				: null)
				        .raisedByName(raisedByName.getFirstName() + raisedByName.getLastName())
				        .assignedByName(assignedByName != null 
				        						? assignedByName.getFirstName() + assignedByName.getLastName()
				        						: null)
				        .assignedToName(assignedToName != null 
				        						? assignedToName.getFirstName() + assignedToName.getLastName() 
				        						: null)
				        .build();
				issuesRequestDTOlist.add(issuesRequestDTO);
			});
		}
		return issuesResponseDTOBuilder.issues(issuesRequestDTOlist).build();
	}

}
