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

@RestController
@RequestMapping("/users")
public class AppUserController {

	@Autowired
	private UserServiceImpl userService;
	
	@GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Lista todos los usuarios", notes = "Lista todos los usuarios")
	public ResponseEntity<List<AppUser>> getAllUsers(){
		List<AppUser> lista = userService.listar();
		return new ResponseEntity<List<AppUser>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AppUser> listarPorId(@PathVariable("id") Integer id) {
		AppUser obj = userService.leerPorId(id);
		if (obj.getUserId() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO" + id);
		}
		return new ResponseEntity<AppUser>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody AppUser user) {
		AppUser obj = userService.registrar(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getUserId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<AppUser> modificar(@Valid @RequestBody AppUser user) {
		AppUser obj = userService.modificar(user);
		return new ResponseEntity<AppUser>(obj, HttpStatus.OK);
	}
	
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
