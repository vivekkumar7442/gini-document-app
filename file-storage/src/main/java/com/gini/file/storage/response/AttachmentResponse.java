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
public class AttachmentResponse {
	
	UserBean userDetails;

	/**
	 * @return the userDetails
	 */
	public UserBean getUserDetails() {
		return userDetails;
	}

	/**
	 * @param userDetails the userDetails to set
	 */
	public void setUserDetails(UserBean userDetails) {
		this.userDetails = userDetails;
	}

	
	
	
	

}
