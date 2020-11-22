/**
 * @author vivek
 *
 * 
 */
package com.gini.file.storage.internal.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gini.file.storage.beans.ResponseResources;
import com.gini.file.storage.constants.Constants;
import com.gini.file.storage.constants.SwaggerConstants;
import com.gini.file.storage.exception.GiniCommonException;
import com.gini.file.storage.response.AttachmentResponse;
import com.gini.file.storage.response.DocumentResponse;
import com.gini.file.storage.service.IDocumentService;
import com.gini.file.storage.utils.Status;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * InternalStakeHolderController this class is used for admin user
 * 
 * @author vivek
 *
 */

@RestController
@RequestMapping("/isv/v1/document")
public class InternalStakeHolderController {

	@Autowired
	IDocumentService documentService;

	@ApiOperation(value = SwaggerConstants.ApiOperations.DOCUMENTS.GET_ALL_USER_DOCUMENT)
	@ApiResponses(value = { @ApiResponse(code = 200, message = Constants.SUCCESS, response = DocumentResponse.class),
			@ApiResponse(code = 403, message = Constants.FORBIDDEN),
			@ApiResponse(code = 422, message = Constants.NOT_FOUND),
			@ApiResponse(code = 417, message = Constants.EXCEPTION_FAILED) })
	@GetMapping(value = "/user/{userid}")
	public ResponseResources<AttachmentResponse> downloadAttachment(@PathVariable("userid") Integer userid)
			throws GiniCommonException, IOException {
		
		AttachmentResponse response=documentService.getAllDocumentForUser(userid);
		return new ResponseResources<>(ResponseResources.R_CODE_OK, ResponseResources.RES_SUCCESS, response,
				Status.SUCCESS);
	}

}
