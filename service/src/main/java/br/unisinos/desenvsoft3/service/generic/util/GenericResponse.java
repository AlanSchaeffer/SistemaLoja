package br.unisinos.desenvsoft3.service.generic.util;

public class GenericResponse {

	public static GenericResponse ok() {
		GenericResponse response = new GenericResponse();
		response.success = true;
		return response;
	}
	
	public static GenericResponse error(String message) {
		GenericResponse response = new GenericResponse();
		response.success = false;
		response.message = message;
		return response;
	}
	
	private boolean success;
	private String message;

	public boolean isSuccess() {
		return success;
	}
	
	public String getMessage() {
		return message;
	}
}
