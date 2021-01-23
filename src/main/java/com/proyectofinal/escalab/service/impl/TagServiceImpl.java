package com.proyectofinal.escalab.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectofinal.escalab.dao.ITagRepository;
import com.proyectofinal.escalab.entity.Tag;
import com.proyectofinal.escalab.service.ITagService;

@Service
public class TagServiceImpl implements ITagService{

	@Autowired
	private ITagRepository tagRepository;

	@Override
	public Tag registrar(Tag obj) {
		return tagRepository.save(obj);
	}

	@Override
	public Tag modificar(Tag obj) {
		return tagRepository.save(obj);
	}

	@Override
	public List<Tag> listar() {
		return tagRepository.findAll();
	}

	@Override
	public Tag leerPorId(Integer id) {
		Optional<Tag> op = tagRepository.findById(id);
		return op.isPresent() ? op.get() : new Tag(); 
	}

	@Override
	public boolean eliminar(Integer id) {
		tagRepository.deleteById(id);
		return true;
	}

}
