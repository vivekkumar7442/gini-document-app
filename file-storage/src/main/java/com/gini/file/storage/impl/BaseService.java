/**
 * @author vivek
 *
 * 
 */
package com.gini.file.storage.impl;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * @author vivek
 *
 */
public class BaseService {

	
	protected String getUri(Integer name) {
		return  ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/file/")
                .path(String.valueOf(name))
                .toUriString();
	}
}
