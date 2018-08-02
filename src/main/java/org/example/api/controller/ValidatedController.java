package org.example.api.controller;

import javax.validation.constraints.Size;

import com.codahale.metrics.annotation.Timed;
import io.swagger.annotations.Api;
import org.hibernate.validator.constraints.Email;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@Api("Validated")
@RequestMapping(value="/validation")
public class ValidatedController {

	@Timed
	@RequestMapping(method = RequestMethod.GET)
	String validation(
			@RequestParam("email") @Size(min=5) @Email final String email	){
		return String.format("Got email %s!", email);
	}
}
