package com.itm.space.taskservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotFoundException extends PlatformException {
    public NotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
