package br.com.otavio.educational.service.exception;

public class ResourceEmailAlreadyExistsException extends RuntimeException{
    public ResourceEmailAlreadyExistsException(String msg) {
        super(msg);
    }
}