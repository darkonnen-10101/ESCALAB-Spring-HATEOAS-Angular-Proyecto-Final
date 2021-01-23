package com.proyectofinal.escalab.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.proyectofinal.escalab.entity.AppUser;
import com.proyectofinal.escalab.exception.ModeloNotFoundException;
import com.proyectofinal.escalab.service.impl.UserServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/users")
public class AppUserController {

	@Autowired
	private UserServiceImpl userService;
	
	@ApiOperation(value = "Obtiene todos los usuarios", notes = "No recibe parámetros", 
			response = List.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 405, message = "No se encontraron usuarios en la BBDD"),
	})
	@GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AppUser>> getAllUsers(){
		List<AppUser> lista = userService.listar();
		return new ResponseEntity<List<AppUser>>(lista, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Obtiene user por id", notes = "Recibe una variable Integer para buscar por id.", 
			response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code=400, message = "Bad request"),
			@ApiResponse(code = 405, message = "No se encontró el usuario"),
	})
	@GetMapping("/{id}")
	public ResponseEntity<AppUser> listarPorId(@PathVariable("id") Integer id) {
		AppUser obj = userService.leerPorId(id);
		if (obj.getUserId() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO" + id);
		}
		return new ResponseEntity<AppUser>(obj, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Crea un usuario", notes = "Recibe un body de tipo AppUser.", 
			response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code=400, message = "Bad request")
	})
	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody AppUser user) {
		AppUser obj = userService.registrar(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getUserId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@ApiOperation(value = "Actualiza un usuario", notes = "Recibe un body de tipo AppUser.", 
			response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code=400, message = "Bad request")
	})
	@PutMapping
	public ResponseEntity<AppUser> modificar(@Valid @RequestBody AppUser user) {
		AppUser obj = userService.modificar(user);
		return new ResponseEntity<AppUser>(obj, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Elimina un usuario", notes = "Recibe una variable Integer para buscar por id.", 
			response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) {
		AppUser obj = userService.leerPorId(id);
		if (obj.getUserId() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO" + id);
		}
		userService.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	
}
