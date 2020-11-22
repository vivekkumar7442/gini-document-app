/**
 * @author vivek
 *
 * 
 */
package com.gini.file.storage.request;

import org.hibernate.validator.constraints.Range;
import org.springframework.lang.Nullable;

/**
 * @author vivek
 *
 */
public class DocumentRequest extends BaseRequest {

	private Integer userId;

	private Integer documentId;

	@Nullable
	@Range(min =5,message="description should be greated that four char")
	private String description;

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return the documentId
	 */
	public Integer getDocumentId() {
		return documentId;
	}

	/**
	 * @param documentId the documentId to set
	 */
	public void setDocumentId(Integer documentId) {
		this.documentId = documentId;
	}

}
