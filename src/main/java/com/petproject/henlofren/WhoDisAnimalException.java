package com.petproject.henlofren;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(code = NOT_FOUND, reason = "Sory we no found dat animal")
public class WhoDisAnimalException extends RuntimeException {

}