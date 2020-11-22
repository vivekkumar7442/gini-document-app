/**
 * @author vivek
 *
 * 
 */
package com.gini.file.storage.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.gini.file.storage.exception.GiniCommonException;
import com.gini.file.storage.request.DocumentRequest;
import com.gini.file.storage.response.AttachmentResponse;
import com.gini.file.storage.response.DocumentResponse;

/**
 * @author vivek
 *
 */
public interface IDocumentService {

	/**
	 * @author vivek
	 *
	 * @param file
	 * @param userId
	 * @param description
	 * @param type
	 * @return
	 */
	DocumentResponse uploadAttachment(MultipartFile file, Integer userId, String description, String type)throws GiniCommonException, IOException;

	/**
	 * @author vivek
	 *
	 * @param attachmentId
	 * @param httpServletResponse
	 * @return
	 */
	ResponseEntity<byte[]> downloadAttachment(Integer userId,Integer attachmentId, HttpServletResponse httpServletResponse)throws GiniCommonException, IOException;

	/**
	 * @author vivek
	 *
	 * @param request
	 * @return
	 */
	DocumentResponse updateDocumentDetails(DocumentRequest request)throws GiniCommonException;

	/**
	 * @author vivek
	 *
	 * @param userid
	 * @return
	 */
	AttachmentResponse getAllDocumentForUser(Integer userid) throws GiniCommonException;


}
