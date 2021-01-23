package com.proyectofinal.escalab.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.proyectofinal.escalab.entity.Recipient;
import com.proyectofinal.escalab.exception.ModeloNotFoundException;
import com.proyectofinal.escalab.service.impl.RecipientServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/recipients")
public class RecipientController {
	@Autowired
	private RecipientServiceImpl recipientService;
	
	@ApiOperation(value = "Obtiene todos los destinatarios", notes = "No recibe parámetros", 
			response = List.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 405, message = "No se encontraron destinatarios en la BBDD"),
	})
	@GetMapping
	public ResponseEntity<List<Recipient>> listar() {
		List<Recipient> lista = recipientService.listar();
		return new ResponseEntity<List<Recipient>>(lista, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Obtiene un destinatario por id", notes = "Recibe una variable Integer para buscar por id.", 
			response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code=400, message = "Bad request"),
			@ApiResponse(code = 405, message = "No se encontró el destinatario"),
	})
	@GetMapping("/{id}")
	public ResponseEntity<Recipient> listarPorId(@PathVariable("id") Integer id) {
		Recipient obj = recipientService.leerPorId(id);
		if (obj.getRecipientId() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO" + id);
		}
		return new ResponseEntity<Recipient>(obj, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Crea un destinatario", notes = "Recibe un body de tipo Recipient.", 
			response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code=400, message = "Bad request")
	})
	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody Recipient recipient) {
		Recipient obj = recipientService.registrar(recipient);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(recipient.getRecipientId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@ApiOperation(value = "Actualiza un destinatario", notes = "Recibe un body de tipo Recipient.", 
			response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code=400, message = "Bad request")
	})
	@PutMapping
	public ResponseEntity<Recipient> modificar(@Valid @RequestBody Recipient recipient) {
		Recipient obj = recipientService.modificar(recipient);
		return new ResponseEntity<Recipient>(obj, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Elimina un destinatario", notes = "Recibe una variable Integer para buscar por id.", 
			response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) {
		Recipient obj = recipientService.leerPorId(id);
		if (obj.getRecipientId() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO" + id);
		}
		recipientService.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

}
