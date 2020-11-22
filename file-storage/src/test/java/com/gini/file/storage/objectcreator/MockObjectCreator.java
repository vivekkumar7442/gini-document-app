/**
 * @author vivek
 *
 * 
 */
package com.gini.file.storage.objectcreator;

import java.sql.Timestamp;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.gini.file.storage.entity.Documents;
import com.gini.file.storage.entity.UserDetails;
import com.gini.file.storage.request.DocumentRequest;

/**
 * @author vivek
 *
 */
@Service
public class MockObjectCreator {

	/**
	 * @author vivek
	 *
	 * @return
	 */
	public Documents getDocumentDetails() {
		Documents documents = new Documents();
		documents.setId(1);
		documents.setCategory("IMAGES");
		documents.setCreatedTime(new Timestamp(System.currentTimeMillis()));
		documents.setUpdatedTime(new Timestamp(System.currentTimeMillis()));
		documents.setType("jpeg");
		documents.setDocumentName("mockito");
		documents.setVerified(true);
		documents.setUserDetails(getUserDetails());
		return documents;
	}

	/**
	 * @author vivek
	 *
	 * @return
	 */
	public UserDetails getUserDetails() {
		UserDetails userDetails = new UserDetails();
		userDetails.setCreatedTime(new Timestamp(System.currentTimeMillis()));
		userDetails.setEmail("vivekkumar7442@gmail.com");
		userDetails.setUpdatedTime(new Timestamp(System.currentTimeMillis()));
		userDetails.setId(1);
		userDetails.setFirstName("vivek");
		userDetails.setLastName("kumar");
		return userDetails;
	}

	/**
	 * @author vivek
	 *
	 * @return
	 */
	public DocumentRequest getDocRequest() {
		DocumentRequest documentRequest = new DocumentRequest();
		documentRequest.setDocumentId(1);
		documentRequest.setUserId(1);
		documentRequest.setDescription("Test file");
		return documentRequest;
	}

	/**
	 * @author vivek
	 *
	 * @return
	 */
	public DocumentRequest getDocRequestWithoutId() {
		DocumentRequest documentRequest = new DocumentRequest();
		documentRequest.setDocumentId(1);
		documentRequest.setDescription("Test file");
		return documentRequest;
	}

	/**
	 * @author vivek
	 *
	 * @return
	 */
	public DocumentRequest getDocRequestWithoutDocId() {
		DocumentRequest documentRequest = new DocumentRequest();
		documentRequest.setUserId(1);
		documentRequest.setDescription("Test file");
		return documentRequest;
	}

	public @Valid DocumentRequest getDocRequestWithMinDesc() {
		DocumentRequest documentRequest = new DocumentRequest();
		documentRequest.setDocumentId(1);
		documentRequest.setUserId(1);
		documentRequest.setDescription("Te");

		return documentRequest;
	}

}
