package com.proyectofinal.escalab.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectofinal.escalab.dao.ICommentRepository;
import com.proyectofinal.escalab.entity.Comment;
import com.proyectofinal.escalab.service.ICommentService;

@Service
public class CommentServiceImpl implements ICommentService{

	@Autowired
	private ICommentRepository commentRepository;

	@Override
	public Comment registrar(Comment obj) {
		return commentRepository.save(obj);
	}

	@Override
	public Comment modificar(Comment obj) {
		return commentRepository.save(obj);
	}

	@Override
	public List<Comment> listar() {
		return commentRepository.findAll();
	}

	@Override
	public Comment leerPorId(Integer id) {
		Optional<Comment> op = commentRepository.findById(id);
		return op.isPresent() ? op.get() : new Comment(); 
	}

	@Override
	public boolean eliminar(Integer id) {
		commentRepository.deleteById(id);
		return true;
	}

}
