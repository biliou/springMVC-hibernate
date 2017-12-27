package com.example.core.responseFormat;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * 定义JSON响应结构 { "result": true, "message": null , "data": ... }
 */
public class Response {
	@JsonProperty
	private Boolean result;
	@JsonProperty
	private String message;
	@JsonProperty
	private Object data;

	public Response success() {
		this.result = true;
		return this;
	}

	public Response success(Object data) {
		this.result = true;
		this.data = data;
		return this;
	}

	public Response failure() {
		this.result = false;
		return this;
	}

	public Response failure(String message) {
		this.result = false;
		this.message = message;
		return this;
	}
}
