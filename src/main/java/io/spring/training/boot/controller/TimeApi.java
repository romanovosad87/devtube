package io.spring.training.boot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface TimeApi {

    @Operation(summary = "Get time")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The current time",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))})})
    @GetMapping("/time")
    String getTime();


    @Operation(summary = "Save user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Save user",
                    content = {@Content(mediaType = "application/json")})})
    @PostMapping("/user")
    void saveUser(@RequestBody User user);
}
