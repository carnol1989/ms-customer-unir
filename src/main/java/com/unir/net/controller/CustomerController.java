package com.unir.net.controller;

import com.unir.net.dto.PatchDto;
import com.unir.net.entity.Customer;
import com.unir.net.entity.TypeIdentityDocument;
import com.unir.net.exception.ModelNotFoundException;
import com.unir.net.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Operation(summary = "Obtener todos los clientes.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Información de clientes encontrada.",
                content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Customer.class))})
    })
    @GetMapping
    public ResponseEntity<List<Customer>> findAllCustomerController() {
        List<Customer> listCustomers = customerService.findAllService();
        return new ResponseEntity<List<Customer>>(listCustomers, HttpStatus.OK);
    }

    @Operation(summary = "Obtener cliente por id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Información de cliente encontrada.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Customer.class))}),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado.",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Customer> findByIdCustomerController(@PathVariable("id") Long id) {
        Customer objectCustomer = customerService.findByIdService(id);
        if (objectCustomer.getIdCustomer() == null)
            throw  new ModelNotFoundException("ID no encontrado " + id);
        return new ResponseEntity<Customer>(objectCustomer, HttpStatus.OK);
    }

    @Operation(summary = "Crear nuevo cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente creado.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Customer.class))})
    })
    @PostMapping
    public ResponseEntity<Object> saveCustomerController(@RequestBody Customer objectRequest) {
        Customer objectResponse = customerService.saveService(objectRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objectResponse.getIdCustomer()).toUri();
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Actualizar datos del cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente actualizado.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Customer.class))})
    })
    @PutMapping
    public ResponseEntity<Customer> updateCustomerController(@RequestBody Customer objectRequest) {
        Customer objectResponse = customerService.updateService(objectRequest);
        return new ResponseEntity<Customer>(objectResponse, HttpStatus.OK);
    }

    @Operation(summary = "Actualizar parcialmente datos del cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Cliente actualizado parcialmente.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Customer.class))}),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado.",
                    content = @Content)
    })
    @PatchMapping("/{id}")
    public ResponseEntity<Boolean> updatePartiallyController(@PathVariable(name = "id") Long id,
                                                             @RequestBody PatchDto dto) {
        if (dto.getOp().equalsIgnoreCase("update")) {
            boolean result = customerService.pathService(id, dto);
            return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
        } else {
            throw  new ModelNotFoundException("Operacion no implementada.");
        }
    }

    @Operation(summary = "Eliminar a un cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente eliminado correctamente.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Customer.class))}),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado.",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTypeIdentiyDocumentController(@PathVariable("id") Long id) {
        Customer objectResponse = customerService.findByIdService(id);
        if (objectResponse.getIdCustomer() == null) {
            throw new ModelNotFoundException("ID no encontrado " + id);
        }
        customerService.deleteByIdService(id);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

}