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

import com.proyectofinal.escalab.entity.Tag;
import com.proyectofinal.escalab.exception.ModeloNotFoundException;
import com.proyectofinal.escalab.service.impl.TagServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/tags")
public class TagController {
	@Autowired
	private TagServiceImpl tagService;
	
	@ApiOperation(value = "Obtiene todos los tags", notes = "No recibe parámetros", 
			response = List.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 405, message = "No se encontraron tags en la BBDD"),
	})
	@GetMapping
	public ResponseEntity<List<Tag>> listar() {
		List<Tag> lista = tagService.listar();
		return new ResponseEntity<List<Tag>>(lista, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Obtiene tag por id", notes = "Recibe una variable Integer para buscar por id.", 
			response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code=400, message = "Bad request"),
			@ApiResponse(code = 405, message = "No se encontró el tag"),
	})
	@GetMapping("/{id}")
	public ResponseEntity<Tag> listarPorId(@PathVariable("id") Integer id) {
		Tag obj = tagService.leerPorId(id);
		if (obj.getTagId() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO" + id);
		}
		return new ResponseEntity<Tag>(obj, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Crea un tag", notes = "Recibe un body de tipo Tag.", 
			response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code=400, message = "Bad request")
	})
	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody Tag tag) {
		Tag obj = tagService.registrar(tag);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tag.getTagId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@ApiOperation(value = "Actualiza un tag", notes = "Recibe un body de tipo Tag.", 
			response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code=400, message = "Bad request")
	})
	@PutMapping
	public ResponseEntity<Tag> modificar(@Valid @RequestBody Tag tag) {
		Tag obj = tagService.modificar(tag);
		return new ResponseEntity<Tag>(obj, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Elimina un tag", notes = "Recibe una variable Integer para buscar por id.", 
			response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) {
		Tag obj = tagService.leerPorId(id);
		if (obj.getTagId() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO" + id);
		}
		tagService.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

}
