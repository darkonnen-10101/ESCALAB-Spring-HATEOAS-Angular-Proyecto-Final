package com.proyectofinal.escalab.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectofinal.escalab.dao.IPostRepository;
import com.proyectofinal.escalab.entity.Post;
import com.proyectofinal.escalab.service.IPostService;

@Service
public class PostServiceImpl implements IPostService{

	@Autowired
	private IPostRepository postRepository;

	@Override
	public Post registrar(Post obj) {
		return postRepository.save(obj);
	}

	@Override
	public Post modificar(Post obj) {
		return postRepository.save(obj);
	}

	@Override
	public List<Post> listar() {
		return postRepository.findAll();
	}

	@Override
	public Post leerPorId(Integer id) {
		Optional<Post> op = postRepository.findById(id);
		return op.isPresent() ? op.get() : new Post(); 
	}

	@Override
	public boolean eliminar(Integer id) {
		postRepository.deleteById(id);
		return true;
	}

}
