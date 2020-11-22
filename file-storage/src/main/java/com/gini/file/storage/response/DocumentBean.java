/**
 * @author vivek
 *
 * 
 */
package com.gini.file.storage.response;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.gini.file.storage.entity.Documents;

/**
 * @author vivek
 *
 */
@JsonInclude(value=Include.NON_NULL)
public class DocumentBean {

	private Integer id;

	private String documentName;

	private String type;

	private String description;

	private String category;

	private byte[] data;

	private boolean isVerified;

	private Timestamp createdTime;

	private Timestamp updatedTime;
	
	public DocumentBean(){}
	
	public DocumentBean(Documents documents) {
		this.id=documents.getId();
		this.documentName=documents.getDocumentName();
		this.type=documents.getType();
		this.description=documents.getDescription();
		this.category=documents.getCategory();
		this.data=documents.getData();
		this.isVerified=documents.isVerified();
		this.createdTime=documents.getCreatedTime();
		this.updatedTime=documents.getUpdatedTime();
	}
	

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the documentName
	 */
	public String getDocumentName() {
		return documentName;
	}

	/**
	 * @param documentName the documentName to set
	 */
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

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
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the data
	 */
	public byte[] getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(byte[] data) {
		this.data = data;
	}

	/**
	 * @return the isVerified
	 */
	public boolean isVerified() {
		return isVerified;
	}

	/**
	 * @param isVerified the isVerified to set
	 */
	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}

	/**
	 * @return the createdTime
	 */
	public Timestamp getCreatedTime() {
		return createdTime;
	}

	/**
	 * @param createdTime the createdTime to set
	 */
	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	/**
	 * @return the updatedTime
	 */
	public Timestamp getUpdatedTime() {
		return updatedTime;
	}

	/**
	 * @param updatedTime the updatedTime to set
	 */
	public void setUpdatedTime(Timestamp updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	

}
