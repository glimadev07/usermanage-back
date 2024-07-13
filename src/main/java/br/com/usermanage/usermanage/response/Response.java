package br.com.usermanage.usermanage.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Data;

/**
* @author gilberto.lima
*/

@Data
public class Response<T> {

    private boolean status;
    private T data;
    private ResponseError error;

    public Response() {
        this.status = true;
        this.error = null;
    }

    public void setErrors(ResponseError error) {
        this.error = error;
    }

    public static <T> ResponseEntity<Response<T>> errorResponse(int codigoStatus, HttpStatus httpStatus ,String errorMessage) {
        Response<T> response = new Response<>();
        ResponseError error = new ResponseError();
        error.setError(errorMessage);
        error.setStatusHTTP(httpStatus);
        error.setCodigoHTTP(codigoStatus);
        response.setStatus(false);
        response.setErrors(error);
        return ResponseEntity.status(httpStatus).body(response);
    }

    public static <T> ResponseEntity<Response<T>> successResponse(T data) {
        Response<T> response = new Response<>();
        response.setData(data);
        response.setStatus(true);
        return ResponseEntity.ok(response);
    }
}
