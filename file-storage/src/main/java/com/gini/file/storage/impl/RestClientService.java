/**
 * @author vivek
 *
 * 
 */
package com.gini.file.storage.impl;

import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.gini.file.storage.response.RestResponse;
import com.gini.file.storage.utils.Status;

/**
 * @author vivek
 *
 */
@Service
public class RestClientService {
	private static final Logger LOGGER = LoggerFactory.getLogger(RestClientService.class);

	public RestResponse putStream(String url, byte[] data, String fileName) {
		LOGGER.info("RestClientService.post method invoked with parameter {}.", url);
		RestResponse restResponse = new RestResponse();
		try {
			MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
			parts.add("file", new ByteArrayResource(data));
			parts.add("filename", fileName);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.MULTIPART_FORM_DATA);

			HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(
					parts, headers);
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
			LOGGER.info("restTemplate after restTemplate.exchange");
			restResponse.setData(response.getBody());
			if (HttpStatus.OK == response.getStatusCode() || HttpStatus.CREATED == response.getStatusCode()) {
				restResponse.setStatus(Status.SUCCESS);
			} else {
				restResponse.setStatus(Status.FAILURE);
			}
		} catch (Exception e) {
			LOGGER.info("Error in put Stream {}", e);
			restResponse.setStatus(Status.ERROR);
		}

		LOGGER.info("RestClientService.post method ends.");
		return restResponse;

	}

}
