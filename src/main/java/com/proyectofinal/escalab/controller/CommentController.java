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

@RestController
@RequestMapping("/comments")
public class CommentController {

	@Autowired
	private CommentServiceImpl commentService;
	
	@GetMapping
	public ResponseEntity<List<Comment>> listar() {
		List<Comment> lista = commentService.listar();
		return new ResponseEntity<List<Comment>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Comment> listarPorId(@PathVariable("id") Integer id) {
		Comment obj = commentService.leerPorId(id);
		if (obj.getCommentId() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO" + id);
		}
		return new ResponseEntity<Comment>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody Comment comment) {
		Comment obj = commentService.registrar(comment);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(comment.getCommentId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Comment> modificar(@Valid @RequestBody Comment comment) {
		Comment obj = commentService.modificar(comment);
		return new ResponseEntity<Comment>(obj, HttpStatus.OK);
	}
	
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
