package br.com.otavio.educational.service.exception;

public class ResourceCodeAlreadyExistsException extends RuntimeException{
    public ResourceCodeAlreadyExistsException(String msg) {
        super(msg);
    }
}