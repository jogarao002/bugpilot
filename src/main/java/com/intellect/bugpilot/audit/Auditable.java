package com.intellect.bugpilot.audit;

import java.util.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable {

	@Column(updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	protected Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	protected Date updatedOn;
	
	@Column(name = "created_by")
	@CreatedBy
	protected Long createdBy;
	
	@Column(name = "updated_by")
	@LastModifiedBy
	protected Long updatedBy;

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}
	
	

}
