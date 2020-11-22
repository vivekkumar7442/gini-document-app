/**
 * @author vivek
 *
 * 
 */
package com.gini.file.storage.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gini.file.storage.exception.GiniCommonException;
import com.gini.file.storage.response.UserBean;

/**
 * @author vivek
 *
 */
public class FileUtils {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);


	public static byte[] converObjectToJsonAndZip(UserBean userBean) throws GiniCommonException {
		File file = new File("test.zip");

		try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(file));
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

			ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(userBean);
			zipOutputStream.write(jsonString.getBytes());

			return byteArrayOutputStream.toByteArray();

		} catch (IOException e) {
			throw new GiniCommonException("error in uploading");
		}

	}

}
