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

@RestController
@RequestMapping("/tags")
public class TagController {
	@Autowired
	private TagServiceImpl tagService;
	
	@GetMapping
	public ResponseEntity<List<Tag>> listar() {
		List<Tag> lista = tagService.listar();
		return new ResponseEntity<List<Tag>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Tag> listarPorId(@PathVariable("id") Integer id) {
		Tag obj = tagService.leerPorId(id);
		if (obj.getTagId() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO" + id);
		}
		return new ResponseEntity<Tag>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody Tag tag) {
		Tag obj = tagService.registrar(tag);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tag.getTagId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Tag> modificar(@Valid @RequestBody Tag tag) {
		Tag obj = tagService.modificar(tag);
		return new ResponseEntity<Tag>(obj, HttpStatus.OK);
	}
	
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
