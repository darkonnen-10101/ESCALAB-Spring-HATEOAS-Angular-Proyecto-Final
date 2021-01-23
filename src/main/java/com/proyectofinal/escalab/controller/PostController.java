package com.proyectofinal.escalab.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
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

import com.proyectofinal.escalab.dto.PostDTO;
import com.proyectofinal.escalab.entity.Post;
import com.proyectofinal.escalab.exception.ModeloNotFoundException;
import com.proyectofinal.escalab.service.impl.PostServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/posts")
public class PostController {

	@Autowired
	private PostServiceImpl postService;	
	@ApiOperation(value = "Obtiene todos los posts", notes = "No recibe parámetros", 
			response = List.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 405, message = "No se encontraron posts en la BBDD"),
	})
	@GetMapping
	public ResponseEntity<List<Post>> listar() {
		List<Post> lista = postService.listar();
		return new ResponseEntity<List<Post>>(lista, HttpStatus.OK);
	}

	@ApiOperation(value = "Obtiene todos los posts", notes = "No recibe parámetros. Responde al modelo de madurez de Richardson", 
			response = List.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 405, message = "No se encontraron posts en la BBDD"),
	})
	@GetMapping(value = "/hateoas", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PostDTO> listarHateoas() {
		List<Post> posts = new ArrayList<>();
		List<PostDTO> postsDto = new ArrayList<>();
		posts = postService.listar();

		for (Post post : posts) {
			PostDTO p = new PostDTO();
			p.setIdPost(post.getPostId());
			p.setUser(post.getPostCreator());
			p.setTags(post.getPostTags());

			ControllerLinkBuilder linkTo = linkTo(methodOn(PostController.class).listarPorId((post.getPostId())));
			p.add(linkTo.withSelfRel());

			ControllerLinkBuilder linkTo1 = linkTo(
					methodOn(AppUserController.class).listarPorId((post.getPostCreator().getUserId())));
			p.add(linkTo1.withSelfRel());

			ControllerLinkBuilder linkTo2 = linkTo(methodOn(TagController.class).listar());
			p.add(linkTo2.withSelfRel());

			postsDto.add(p);

		}

		return postsDto;
	}

	@ApiOperation(value = "Obtiene post por id", notes = "Recibe una variable Integer para buscar por id.", 
			response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code=400, message = "Bad request"),
			@ApiResponse(code = 405, message = "No se encontró el posteo"),
	})
	@GetMapping("/{id}")
	public ResponseEntity<Post> listarPorId(@PathVariable("id") Integer id) {
		Post obj = postService.leerPorId(id);
		if (obj.getPostId() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO" + id);
		}
		return new ResponseEntity<Post>(obj, HttpStatus.OK);
	}

	@ApiOperation(value = "Guarda un post", notes = "Recibe un body de tipo Post.", 
			response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code=400, message = "Bad request")
	})
	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody Post post) {
		Post obj = postService.registrar(post);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getPostId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@ApiOperation(value = "Actualiza un post", notes = "Recibe un body de tipo Post.", 
			response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code=400, message = "Bad request")
	})
	@PutMapping
	public ResponseEntity<Post> modificar(@Valid @RequestBody Post post) {
		Post obj = postService.modificar(post);
		return new ResponseEntity<Post>(obj, HttpStatus.OK);
	}

	@ApiOperation(value = "Elimina un post", notes = "Recibe una variable Integer para buscar por id.", 
			response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) {
		Post obj = postService.leerPorId(id);
		if (obj.getPostId() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO" + id);
		}
		postService.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

}
