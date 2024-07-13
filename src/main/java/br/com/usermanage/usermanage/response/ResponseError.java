package br.com.usermanage.usermanage.response;

import org.springframework.http.HttpStatus;

import lombok.Data;

/**
* @author gilberto.lima
*/

@Data
public class ResponseError {

	private HttpStatus statusHTTP;
	private int codigoHTTP;
	private String error;
}
