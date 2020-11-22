/**
 * @author vivek
 *
 * 
 */
package com.gini.file.storage.response;

import java.io.InputStream;

import com.gini.file.storage.utils.Status;

/**
 * @author vivek
 *
 */
public class RestResponse {

	private String data;
	private Status status;
	private String errorMessage;
	private InputStream inputStream;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "RestResponse [data=" + data + ", status=" + status + ", errorMessage=" + errorMessage + ", inputStream="
				+ inputStream + "]";
	}

}
