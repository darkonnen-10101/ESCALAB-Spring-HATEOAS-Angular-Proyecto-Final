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

import com.proyectofinal.escalab.entity.Role;
import com.proyectofinal.escalab.exception.ModeloNotFoundException;
import com.proyectofinal.escalab.service.impl.RoleServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/roles")
public class RoleController {
	@Autowired
	private RoleServiceImpl roleService;
	
	@ApiOperation(value = "Obtiene todos los roles", notes = "No recibe parámetros", 
			response = List.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 405, message = "No se encontraron roles en la BBDD"),
	})
	@GetMapping
	public ResponseEntity<List<Role>> listar() {
		List<Role> lista = roleService.listar();
		return new ResponseEntity<List<Role>>(lista, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Obtiene role por id", notes = "Recibe una variable Integer para buscar por id.", 
			response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code=400, message = "Bad request"),
			@ApiResponse(code = 405, message = "No se encontró el rol"),
	})
	@GetMapping("/{id}")
	public ResponseEntity<Role> listarPorId(@PathVariable("id") Integer id) {
		Role obj = roleService.leerPorId(id);
		if (obj.getRoleId() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO" + id);
		}
		return new ResponseEntity<Role>(obj, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Crea un rol", notes = "Recibe un body de tipo Role.", 
			response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code=400, message = "Bad request")
	})
	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody Role role) {
		Role obj = roleService.registrar(role);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(role.getRoleId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@ApiOperation(value = "Actualiza un rol", notes = "Recibe un body de tipo Role.", 
			response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code=400, message = "Bad request")
	})
	@PutMapping
	public ResponseEntity<Role> modificar(@Valid @RequestBody Role role) {
		Role obj = roleService.modificar(role);
		return new ResponseEntity<Role>(obj, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Elimina un rol", notes = "Recibe una variable Integer para buscar por id.", 
			response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) {
		Role obj = roleService.leerPorId(id);
		if (obj.getRoleId() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO" + id);
		}
		roleService.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

}
