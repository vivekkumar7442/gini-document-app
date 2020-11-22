/**
 * @author vivek
 *
 * 
 */
package com.gini.file.storage.controller;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import com.gini.file.storage.beans.ResponseResources;
import com.gini.file.storage.entity.repository.DocumentRepository;
import com.gini.file.storage.entity.repository.UserRepository;
import com.gini.file.storage.exception.GiniCommonException;
import com.gini.file.storage.objectcreator.MockObjectCreator;
import com.gini.file.storage.response.DocumentResponse;
import com.gini.file.storage.utils.Status;

/**
 * @author vivek
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration
public class DocumetControllerTest {

	@MockBean
	private DocumentRepository documentRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MockObjectCreator mockObjectCreator;

	@Autowired
	private DocumentController documentController;

	@Before
	public void init() {
		Mockito.when(documentRepository.findByIdAndUserDetails_id(Mockito.anyInt(), Mockito.anyInt()))
				.thenReturn(mockObjectCreator.getDocumentDetails());
		Mockito.when(userRepository.findById(Mockito.anyInt()))
				.thenReturn(Optional.of(mockObjectCreator.getUserDetails()));
	}

	/**
	 * test case to get the document details-positive case
	 **/

	@Test
	public void testgetDocumentDetails() throws GiniCommonException, IOException {
		HttpServletResponse httpServletResponse = Mockito.mock(HttpServletResponse.class);
		ResponseResources<ResponseEntity<byte[]>> response = documentController.downloadAttachment(Mockito.anyInt(),
				Mockito.anyInt(), httpServletResponse);
		assertTrue(response != null && response.getStatus() == Status.SUCCESS);
	}

	/**
	 * test case to get the document details without user id -negative case
	 **/
	@Test
	public void testgetDocumentDetailsWithoutValidUserId() throws GiniCommonException, IOException {
		HttpServletResponse httpServletResponse = Mockito.mock(HttpServletResponse.class);
		ResponseResources<ResponseEntity<byte[]>> response = documentController.downloadAttachment(null,
				Mockito.anyInt(), httpServletResponse);
		assertTrue(response != null && response.getStatus() == Status.FAILURE);
	}

	/**
	 * test case to get the document details without doc id-negative case
	 **/
	@Test
	public void testgetDocumentDetailsWithoutValidDocId() throws GiniCommonException, IOException {
		HttpServletResponse httpServletResponse = Mockito.mock(HttpServletResponse.class);
		ResponseResources<ResponseEntity<byte[]>> response = documentController.downloadAttachment(Mockito.anyInt(),
				null, httpServletResponse);
		assertTrue(response != null && response.getStatus() == Status.FAILURE);
	}

	/**
	 * test case to get the document details without user details present -negative
	 * case
	 **/
	@Test
	public void testgetDocumentDetailsWithoutUserDetails() throws GiniCommonException, IOException {
		Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(null));
		HttpServletResponse httpServletResponse = Mockito.mock(HttpServletResponse.class);
		ResponseResources<ResponseEntity<byte[]>> response = documentController.downloadAttachment(Mockito.anyInt(),
				null, httpServletResponse);
		assertTrue(response != null && response.getStatus() == Status.FAILURE);
	}

	/**
	 * test case to get the document details without user Doc present -negative case
	 **/
	@Test
	public void testgetDocumentDetailsWithoutDocDetails() throws GiniCommonException, IOException {
		Mockito.when(documentRepository.findByIdAndUserDetails_id(Mockito.anyInt(), Mockito.anyInt())).thenReturn(null);
		HttpServletResponse httpServletResponse = Mockito.mock(HttpServletResponse.class);
		ResponseResources<ResponseEntity<byte[]>> response = documentController.downloadAttachment(Mockito.anyInt(),
				null, httpServletResponse);
		assertTrue(response != null && response.getStatus() == Status.FAILURE);

	}

	/**
	 * test case to UpdateDocument details-positive case
	 **/
	@Test
	public void testUpdateDocument() throws GiniCommonException, IOException {
		ResponseResources<DocumentResponse> response = documentController
				.updateDocumentDetails(mockObjectCreator.getDocRequest());
		assertTrue(response != null && response.getStatus() == Status.SUCCESS);
	}

	/**
	 * test case to UpdateDocument details-negative case
	 **/
	@Test
	public void testUpdateDocumentInValidUserId() throws GiniCommonException, IOException {
		Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(null));
		ResponseResources<DocumentResponse> response = documentController
				.updateDocumentDetails(mockObjectCreator.getDocRequestWithoutId());
		assertTrue(response != null && response.getStatus() == Status.FAILURE);

	}

	/**
	 * test case to UpdateDocument details-negative case
	 **/
	@Test
	public void testUpdateDocumentWithNullRequest() throws GiniCommonException, IOException {
		ResponseResources<DocumentResponse> response = documentController.updateDocumentDetails(null);
		assertTrue(response != null && response.getStatus() == Status.FAILURE);

	}

	/**
	 * test case to UpdateDocument details-negative case
	 **/
	@Test
	public void testUpdateDocumentInValidDocId() throws GiniCommonException, IOException {
		Mockito.when(documentRepository.findByIdAndUserDetails_id(Mockito.anyInt(), Mockito.anyInt())).thenReturn(null);

		ResponseResources<DocumentResponse> response = documentController
				.updateDocumentDetails(mockObjectCreator.getDocRequestWithoutDocId());
		assertTrue(response != null && response.getStatus() == Status.FAILURE);

	}

	/**
	 * test case to UploadDoc details-positive case
	 **/
	@Test
	public void testUploadDoc() throws GiniCommonException, IOException {

		MultipartFile firstFile = new MockMultipartFile("data", "filename.txt", "text/plain", "some xml".getBytes());
		ResponseResources<DocumentResponse> response = documentController.uploadAttachment(firstFile, Mockito.anyInt(),
				Mockito.anyString(), Mockito.anyString());
		assertTrue(response != null && response.getStatus() == Status.SUCCESS);
	}

	/**
	 * test case to UploadDoc user details null details-negative case
	 **/
	@Test
	public void testUploadDocWithoutValidUserId() throws GiniCommonException, IOException {
		Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(null));
		MultipartFile firstFile = new MockMultipartFile("data", "filename.txt", "text/plain", "some xml".getBytes());
		ResponseResources<DocumentResponse> response = documentController.uploadAttachment(firstFile, null,
				Mockito.anyString(), Mockito.anyString());
		assertTrue(response != null && response.getStatus() == Status.FAILURE);

	}

	/**
	 * test case to UploadDoc details with type empty-negative case
	 **/
	@Test
	public void testUploadDocWithoutValidType() throws GiniCommonException, IOException {
		MultipartFile firstFile = new MockMultipartFile("data", "filename.txt", "text/plain", "some xml".getBytes());
		ResponseResources<DocumentResponse> response = documentController.uploadAttachment(firstFile, Mockito.anyInt(),
				null, Mockito.anyString());
		assertTrue(response != null && response.getStatus() == Status.FAILURE);

	}

	/**
	 * test case to UploadDoc details with min char will throw error-negative case
	 **/
	@Test
	public void testUploadDocWithMinDescLength() throws GiniCommonException, IOException {
		MultipartFile firstFile = new MockMultipartFile("data", "filename.txt", "text/plain", "some xml".getBytes());
		ResponseResources<DocumentResponse> response = documentController.uploadAttachment(firstFile, Mockito.anyInt(),
				null, "Te");
		assertTrue(response != null && response.getStatus() == Status.FAILURE);

	}

}
