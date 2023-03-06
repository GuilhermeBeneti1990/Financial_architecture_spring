package com.beneti.transactionbff.api;

import com.beneti.transactionbff.services.TransactionService;
import com.beneti.transactionbff.dto.RequestTransactionDto;
import com.beneti.transactionbff.dto.TransactionDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RestController
@RequestMapping("/api/transactions")
@Tag(name = "/transactions", description = "Financial transactions API")
public class TransactionController {

    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Operation(description = "Create transaction")
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Return OK - Transaction Created"),
            @ApiResponse(responseCode = "401", description = "Authentication error"),
            @ApiResponse(responseCode = "403", description = "Authorization error"),
            @ApiResponse(responseCode = "404", description = "Resource not found"),
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<RequestTransactionDto> sendTransaction(@RequestBody final RequestTransactionDto requestTransactionDto) {
        return transactionService.save(requestTransactionDto);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Get transactions by id")
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return OK - Transaction"),
            @ApiResponse(responseCode = "401", description = "Authentication error"),
            @ApiResponse(responseCode = "403", description = "Authorization error"),
            @ApiResponse(responseCode = "404", description = "Resource not found"),
    })
    @Parameters(value = {@Parameter(name = "id", in = ParameterIn.PATH)})
    public Mono<TransactionDto> getTransaction(@PathVariable("id") final String uuid) {
        final Optional<TransactionDto> transactionDto = transactionService.findById(uuid);
        if(transactionDto.isPresent()) {
            return Mono.just(transactionDto.get());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
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
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Confirm transaction")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return OK - Transaction Confirmed"),
            @ApiResponse(responseCode = "401", description = "Authentication error"),
            @ApiResponse(responseCode = "403", description = "Authorization error"),
            @ApiResponse(responseCode = "404", description = "Resource not found"),
    })
    @Parameters(value = {@Parameter(name = "id", in = ParameterIn.PATH)})
    public Mono<TransactionDto> confirmTransaction(@PathVariable("id") final String uuid) {
        return Mono.empty();
    }

}
