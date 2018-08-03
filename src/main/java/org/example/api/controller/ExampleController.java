package org.example.api.controller;

import javax.annotation.security.RolesAllowed;

import com.codahale.metrics.annotation.Timed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.example.api.model.response.SomeResponse;
import org.libex.additions.rest.role.PermissionsAllowed;

import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("root")
public class ExampleController {

	@Timed
	@RequestMapping(value="/", method = RequestMethod.GET)
	@ApiOperation(value = "Root Location")
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "Forbidden")
	})
	String home(){
		return "Hello world!";
	}

	@Timed
	@RequestMapping(value="/something",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	@ApiOperation(value = "Get something")
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "Forbidden")
	})
	SomeResponse something(){
		return new SomeResponse()
				.setTitle("Some title")
				.setDescription("Some description");
	}

	@Timed
	// Protected via WebSecurityConfiguration
	@RequestMapping(value = "/someAdmin",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	@ApiOperation(value = "Get something")
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "Forbidden")
	})
	SomeResponse someAdmin() {
		return new SomeResponse()
				.setTitle("Some title")
				.setDescription("Some description");
	}

	@Timed
	@Secured({"PERMISSION_DeleteRecord"})
	@RequestMapping(value = "/someAdmin2",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	@ApiOperation(value = "Get something")
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "Forbidden")
	})
	SomeResponse someAdmin2() {
		return new SomeResponse()
				.setTitle("Some title")
				.setDescription("Some description");
	}

	@Timed
	@PermissionsAllowed({"DeleteRecord"})
	@RequestMapping(value = "/permission1",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	@ApiOperation(value = "Get something")
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "Forbidden")
	})
	SomeResponse permission1() {
		return new SomeResponse()
				.setTitle("Some title")
				.setDescription("Some description");
	}

	@Timed
	@RolesAllowed("Admin") // note that we don't need "Role_" here because it is automatically added
	@RequestMapping(value = "/adminRole",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	@ApiOperation(value = "Get something")
	@ApiResponses(value = {
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "Forbidden")
	})
	SomeResponse someAdmin3() {
		return new SomeResponse()
				.setTitle("Some title")
				.setDescription("Some description");
	}
}
