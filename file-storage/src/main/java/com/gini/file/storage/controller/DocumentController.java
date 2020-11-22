
package com.gini.file.storage.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gini.file.storage.beans.ResponseResources;
import com.gini.file.storage.constants.Constants;
import com.gini.file.storage.constants.SwaggerConstants;
import com.gini.file.storage.exception.GiniCommonException;
import com.gini.file.storage.request.DocumentRequest;
import com.gini.file.storage.response.DocumentResponse;
import com.gini.file.storage.service.IDocumentService;
import com.gini.file.storage.utils.Status;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * DocumentController this class is used for all the document related operation
 * 
 * @author vivek
 *
 */

@RestController
@RequestMapping("/v1/document")
public class DocumentController {

	@Autowired
	IDocumentService documentService;

	@ApiOperation(value = SwaggerConstants.ApiOperations.DOCUMENTS.UPLOAD_DOCUMENT)
	@ApiResponses(value = { @ApiResponse(code = 200, message = Constants.SUCCESS, response = DocumentResponse.class),
			@ApiResponse(code = 403, message = Constants.FORBIDDEN),
			@ApiResponse(code = 422, message = Constants.NOT_FOUND),
			@ApiResponse(code = 417, message = Constants.EXCEPTION_FAILED) })
	@PostMapping(value = "/upload")
	public ResponseResources<DocumentResponse> uploadAttachment(@RequestParam("file") MultipartFile file,
			@RequestParam(value = "userId", required = true) Integer userId,
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "description", required = false) String description)
			throws GiniCommonException, IOException {
		DocumentResponse response = documentService.uploadAttachment(file, userId, description, type);
		return new ResponseResources<>(ResponseResources.R_CODE_OK, ResponseResources.RES_SUCCESS, response,
				Status.SUCCESS);

	}

	@ApiOperation(value = SwaggerConstants.ApiOperations.DOCUMENTS.GET_DOCUMENT)
	@ApiResponses(value = { @ApiResponse(code = 200, message = Constants.SUCCESS, response = DocumentResponse.class),
			@ApiResponse(code = 403, message = Constants.FORBIDDEN),
			@ApiResponse(code = 422, message = Constants.NOT_FOUND),
			@ApiResponse(code = 417, message = Constants.EXCEPTION_FAILED) })
	@GetMapping(value = "/user/{userid}/attachment/{attachmentid}")
	public ResponseResources<ResponseEntity<byte[]>> downloadAttachment(@PathVariable("userid") Integer userid,
			@PathVariable("attachmentid") Integer attachmentId, HttpServletResponse httpServletResponse)
			throws GiniCommonException, IOException {
		ResponseEntity<byte[]> response = documentService.downloadAttachment(userid, attachmentId, httpServletResponse);
		return new ResponseResources<>(ResponseResources.R_CODE_OK, ResponseResources.RES_SUCCESS, response,
				Status.SUCCESS);
	}
	
	   @ApiOperation(value = SwaggerConstants.ApiOperations.DOCUMENTS.GET_DOCUMENT)
	    @ApiResponses(value = { @ApiResponse(code = 200, message = Constants.SUCCESS, response = DocumentResponse.class),
	            @ApiResponse(code = 403, message = Constants.FORBIDDEN),
	            @ApiResponse(code = 422, message = Constants.NOT_FOUND),
	            @ApiResponse(code = 417, message = Constants.EXCEPTION_FAILED) })
	    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseResources<DocumentResponse> updateDocumentDetails(@RequestBody DocumentRequest request)  throws GiniCommonException{
		   DocumentResponse response = documentService.updateDocumentDetails(request);
	        return new ResponseResources<>(ResponseResources.R_CODE_OK, ResponseResources.RES_SUCCESS, response,
	                Status.SUCCESS);
	    }

}
