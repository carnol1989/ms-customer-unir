package com.unir.net.controller;

import com.unir.net.entity.Customer;
import com.unir.net.entity.TypeIdentityDocument;
import com.unir.net.exception.ModelNotFoundException;
import com.unir.net.service.TypeIdentityDocumentService;
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
@RequestMapping("/types-identity-documents")
public class TypeIdentityDocumentController {

    @Autowired
    private TypeIdentityDocumentService typeIdentityDocumentService;

    @Operation(summary = "Obtener todos los tipo de documentos de identidad.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Información de los tipos documentos encontrada.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TypeIdentityDocument.class))})
    })
    @GetMapping
    public ResponseEntity<List<TypeIdentityDocument>> findAllTypeIdentityDocumentController() {
        List<TypeIdentityDocument> listTypeIdentityDocuments = typeIdentityDocumentService.findAllService();
        return new ResponseEntity<List<TypeIdentityDocument>>(listTypeIdentityDocuments, HttpStatus.OK);
    }

    @Operation(summary = "Obtener tipo de documento de identidad por ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de documento de identidad encontrado.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TypeIdentityDocument.class))}),
            @ApiResponse(responseCode = "404", description = "Tipo de documento de identidad no encontrado.",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<TypeIdentityDocument> findByIdTypeIdentityDocumentController(@PathVariable("id") Long id) {
        TypeIdentityDocument objectTypeIdentityDocument = typeIdentityDocumentService.findByIdService(id);
        if (objectTypeIdentityDocument.getIdTypeIdentityDocument() == null)
            throw  new ModelNotFoundException("ID no encontrado " + id);
        return new ResponseEntity<TypeIdentityDocument>(objectTypeIdentityDocument, HttpStatus.OK);
    }

    @Operation(summary = "Crear nuevo tipo de documento de identidad")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tipo de documento de identidad creado.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TypeIdentityDocument.class))})
    })
    @PostMapping
    public ResponseEntity<Object> saveTypeIdentityDocumentController(@RequestBody TypeIdentityDocument objectRequest) {
        TypeIdentityDocument objectResponse = typeIdentityDocumentService.saveService(objectRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objectResponse.getIdTypeIdentityDocument()).toUri();
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Actualizar datos del tipo de documento de identidad")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de documento de identidad actualizado.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Customer.class))})
    })
    @PutMapping
    public ResponseEntity<TypeIdentityDocument> updateTypeIdentityDocumentController(@RequestBody TypeIdentityDocument objectRequest) {
        TypeIdentityDocument objectResponse = typeIdentityDocumentService.updateService(objectRequest);
        return new ResponseEntity<TypeIdentityDocument>(objectResponse, HttpStatus.OK);
    }

    @Operation(summary = "Eliminar un tipo de documento de identidad.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de documento de identidad eliminado correctamente.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TypeIdentityDocument.class))}),
            @ApiResponse(responseCode = "404", description = "Tipo de documento de identidad no encontrado.",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTypeIdentiyDocumentController(@PathVariable("id") Long id) {
        TypeIdentityDocument objectResponse = typeIdentityDocumentService.findByIdService(id);
        if (objectResponse.getIdTypeIdentityDocument() == null) {
            throw new ModelNotFoundException("ID no encontrado " + id);
        }
        typeIdentityDocumentService.deleteByIdService(id);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

}
