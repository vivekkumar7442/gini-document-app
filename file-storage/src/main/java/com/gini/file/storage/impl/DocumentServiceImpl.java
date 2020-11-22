/**
 * @author vivek
 *
 * 
 */
package com.gini.file.storage.impl;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.gini.file.storage.constants.ExceptionConstants;
import com.gini.file.storage.entity.Documents;
import com.gini.file.storage.entity.UserDetails;
import com.gini.file.storage.entity.repository.DocumentRepository;
import com.gini.file.storage.entity.repository.UserRepository;
import com.gini.file.storage.exception.GiniCommonException;
import com.gini.file.storage.request.DocumentRequest;
import com.gini.file.storage.response.AttachmentResponse;
import com.gini.file.storage.response.DocumentBean;
import com.gini.file.storage.response.DocumentResponse;
import com.gini.file.storage.response.RestResponse;
import com.gini.file.storage.response.UserBean;
import com.gini.file.storage.service.IDocumentService;
import com.gini.file.storage.utils.FileUtils;
import com.gini.file.storage.utils.Status;

/**
 * @author vivek
 *
 */

@Service
public class DocumentServiceImpl extends BaseService implements IDocumentService {

	@Autowired
	private DocumentRepository documentRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestClientService restClientService;

	@Value("${doc.upload.url}")
	private String uploadUrl;

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false)
	public DocumentResponse uploadAttachment(MultipartFile file, Integer userId, String description, String type)
			throws GiniCommonException, IOException {
		DocumentResponse documentResponse = new DocumentResponse();

		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if (fileName.contains("..")) {
			throw new GiniCommonException(ExceptionConstants.INVALIDPATHE_SEQUENCE);
		}

		Optional<UserDetails> optionalUser = userRepository.findById(userId);
		if (optionalUser.isPresent()) {
			Documents document = new Documents(fileName, file.getContentType(), file.getBytes(), description);
			document.setUserDetails(optionalUser.get());
			documentRepository.save(document);

			documentResponse.setFileUrl(getUri(document.getId()));
		} else {
			throw new GiniCommonException(ExceptionConstants.USER_NOT_FOUND);
		}

		return documentResponse;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
	public ResponseEntity<byte[]> downloadAttachment(Integer userId, Integer attachmentId,
			HttpServletResponse httpServletResponse) throws GiniCommonException, IOException {

		Optional<UserDetails> optionalUser = userRepository.findById(userId);
		if (optionalUser.isPresent()) {

			Documents documents = documentRepository.findByIdAndUserDetails_id(attachmentId, userId);
			if (documents == null) {
				throw new GiniCommonException(ExceptionConstants.DOCUMENT_NOT_FOUND);

			}

			return ResponseEntity.ok().contentType(MediaType.parseMediaType(documents.getType()))
					.header(HttpHeaders.CONTENT_DISPOSITION,
							"attachment; filename=\"" + documents.getDocumentName() + "\"")
					.body(documents.getData());

		} else {
			throw new GiniCommonException(ExceptionConstants.USER_NOT_FOUND);

		}

	}

	@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false)
	public DocumentResponse updateDocumentDetails(DocumentRequest request) throws GiniCommonException {
		DocumentResponse documentResponse = new DocumentResponse();
		Optional<UserDetails> optionalUser = userRepository.findById(request.getUserId());
		if (optionalUser.isPresent()) {

			Documents documents = documentRepository.findByIdAndUserDetails_id(request.getDocumentId(),
					request.getUserId());
			if (documents == null) {
				throw new GiniCommonException(ExceptionConstants.DOCUMENT_NOT_FOUND);

			}
			if (request.getDescription() != null
					&& !request.getDescription().equalsIgnoreCase(documents.getDescription()))
				documents.setDescription(request.getDescription());
			documentRepository.save(documents);

		} else {
			throw new GiniCommonException(ExceptionConstants.USER_NOT_FOUND);

		}

		return documentResponse;

	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
	public AttachmentResponse getAllDocumentForUser(Integer userId) throws GiniCommonException {
		AttachmentResponse attachmentResponse = new AttachmentResponse();

		Optional<UserDetails> optionalUser = userRepository.findById(userId);
		if (optionalUser.isPresent()) {
			UserBean userBean = new UserBean();
			UserDetails userDetails = optionalUser.get();
			userBean.setId(userDetails.getId());

			if (!userDetails.getDocuments().isEmpty()) {
				userBean.setDocumentDetails(
						userDetails.getDocuments().stream().map(DocumentBean::new).collect(Collectors.toList()));
			}

			RestResponse response = restClientService.putStream(uploadUrl, FileUtils.converObjectToJsonAndZip(userBean),
					"userdoc");

			if (response != null && response.getStatus() != Status.SUCCESS) {
				throw new GiniCommonException(ExceptionConstants.DOCUMENT_UPLOAD_FAILED);

			}
		}
		return attachmentResponse;
	}

}
