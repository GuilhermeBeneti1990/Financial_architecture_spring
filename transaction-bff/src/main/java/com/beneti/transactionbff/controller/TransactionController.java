package com.beneti.transactionbff.controller;

import com.beneti.transactionbff.dto.RequestTransactionDto;
import com.beneti.transactionbff.dto.TransactionDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Create transaction")
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Return OK - Transaction Created"),
            @ApiResponse(responseCode = "401", description = "Authentication error"),
            @ApiResponse(responseCode = "403", description = "Authorization error"),
            @ApiResponse(responseCode = "404", description = "Resource not found"),
    })
    public Mono<TransactionDto> sendTransaction(@RequestBody final RequestTransactionDto requestTransactionDto) {
        return Mono.empty();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Get transactions by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return OK - Transaction"),
            @ApiResponse(responseCode = "401", description = "Authentication error"),
            @ApiResponse(responseCode = "403", description = "Authorization error"),
            @ApiResponse(responseCode = "404", description = "Resource not found"),
    })
    @Parameters(value = {@Parameter(name = "id", in = ParameterIn.PATH)})
    public Mono<TransactionDto> getTransaction(@PathVariable("id") final String uuid) {
        return Mono.empty();
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Remove transaction by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Return OK - Transaction Deleted"),
            @ApiResponse(responseCode = "401", description = "Authentication error"),
            @ApiResponse(responseCode = "403", description = "Authorization error"),
            @ApiResponse(responseCode = "404", description = "Resource not found"),
    })
    @Parameters(value = {@Parameter(name = "id", in = ParameterIn.PATH)})
    public Mono<TransactionDto> deleteTransaction(@PathVariable("id") final String uuid) {
        return Mono.empty();
    }

    @PatchMapping(value = "/{id}/confirm")
    public Mono<TransactionDto> confirmTransaction(@PathVariable("id") final String uuid) {
        return Mono.empty();
    }

}
