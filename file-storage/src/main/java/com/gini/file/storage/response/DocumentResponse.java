/**
 * @author vivek
 *
 * 
 */
package com.gini.file.storage.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author vivek
 *
 */
@JsonInclude(value=Include.NON_NULL)
public class DocumentResponse {
	
	private String fileUrl;

	/**
	 * @return the fileUrl
	 */
	public String getFileUrl() {
		return fileUrl;
	}

	/**
	 * @param fileUrl the fileUrl to set
	 */
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	
	

}
