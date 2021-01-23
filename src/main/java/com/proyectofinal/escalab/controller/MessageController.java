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

import com.proyectofinal.escalab.entity.Message;
import com.proyectofinal.escalab.exception.ModeloNotFoundException;
import com.proyectofinal.escalab.service.impl.MessageServiceImpl;

@RestController
@RequestMapping("/messages")
public class MessageController {
	@Autowired
	private MessageServiceImpl messageService;
	
	@GetMapping
	public ResponseEntity<List<Message>> listar() {
		List<Message> lista = messageService.listar();
		return new ResponseEntity<List<Message>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Message> listarPorId(@PathVariable("id") Integer id) {
		Message obj = messageService.leerPorId(id);
		if (obj.getMessageId() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO" + id);
		}
		return new ResponseEntity<Message>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody Message message) {
		Message obj = messageService.registrar(message);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(message.getMessageId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Message> modificar(@Valid @RequestBody Message message) {
		Message obj = messageService.modificar(message);
		return new ResponseEntity<Message>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) {
		Message obj = messageService.leerPorId(id);
		if (obj.getMessageId() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO" + id);
		}
		messageService.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

}
