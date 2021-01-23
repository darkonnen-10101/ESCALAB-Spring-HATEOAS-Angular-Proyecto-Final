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

import com.proyectofinal.escalab.entity.Group;
import com.proyectofinal.escalab.exception.ModeloNotFoundException;
import com.proyectofinal.escalab.service.impl.GroupServiceImpl;

@RestController
@RequestMapping("/groups")
public class GroupController {
	
	@Autowired
	private GroupServiceImpl groupService;
	
	@GetMapping
	public ResponseEntity<List<Group>> listar() {
		List<Group> lista = groupService.listar();
		return new ResponseEntity<List<Group>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Group> listarPorId(@PathVariable("id") Integer id) {
		Group obj = groupService.leerPorId(id);
		if (obj.getGroupId() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO" + id);
		}
		return new ResponseEntity<Group>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody Group group) {
		Group obj = groupService.registrar(group);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(group.getGroupId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Group> modificar(@Valid @RequestBody Group group) {
		Group obj = groupService.modificar(group);
		return new ResponseEntity<Group>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) {
		Group obj = groupService.leerPorId(id);
		if (obj.getGroupId() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO" + id);
		}
		groupService.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}


}
