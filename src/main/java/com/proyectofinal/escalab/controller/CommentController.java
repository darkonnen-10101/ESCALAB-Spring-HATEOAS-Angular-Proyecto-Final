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

import com.proyectofinal.escalab.entity.Comment;
import com.proyectofinal.escalab.exception.ModeloNotFoundException;
import com.proyectofinal.escalab.service.impl.CommentServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/comments")
public class CommentController {

	@Autowired
	private CommentServiceImpl commentService;
	
	@ApiOperation(value = "Obtiene todos los comentarios", notes = "No recibe parámetros", 
			response = List.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 405, message = "No se encontraron comentarios en la BBDD"),
	})
	@GetMapping
	public ResponseEntity<List<Comment>> listar() {
		List<Comment> lista = commentService.listar();
		return new ResponseEntity<List<Comment>>(lista, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Obtiene comentario por id", notes = "Recibe una variable Integer para buscar por id.", 
			response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code=400, message = "Bad request"),
			@ApiResponse(code = 405, message = "No se encontró el comentario"),
	})
	@GetMapping("/{id}")
	public ResponseEntity<Comment> listarPorId(@PathVariable("id") Integer id) {
		Comment obj = commentService.leerPorId(id);
		if (obj.getCommentId() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO" + id);
		}
		return new ResponseEntity<Comment>(obj, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Crea un comentario", notes = "Recibe un body de tipo Comment.", 
			response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code=400, message = "Bad request")
	})
	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody Comment comment) {
		Comment obj = commentService.registrar(comment);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(comment.getCommentId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@ApiOperation(value = "Actualiza un comentario", notes = "Recibe un body de tipo Comment.", 
			response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code=400, message = "Bad request")
	})
	@PutMapping
	public ResponseEntity<Comment> modificar(@Valid @RequestBody Comment comment) {
		Comment obj = commentService.modificar(comment);
		return new ResponseEntity<Comment>(obj, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Elimina un comentario", notes = "Recibe una variable Integer para buscar por id.", 
			response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) {
		Comment obj = commentService.leerPorId(id);
		if (obj.getCommentId() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO" + id);
		}
		commentService.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	
}
