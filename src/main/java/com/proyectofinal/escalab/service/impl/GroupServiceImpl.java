package com.proyectofinal.escalab.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectofinal.escalab.dao.IGroupRepository;
import com.proyectofinal.escalab.entity.Group;
import com.proyectofinal.escalab.service.IGroupService;

@Service
public class GroupServiceImpl implements IGroupService{

	@Autowired
	private IGroupRepository groupRepository;

	@Override
	public Group registrar(Group obj) {
		return groupRepository.save(obj);
	}

	@Override
	public Group modificar(Group obj) {
		return groupRepository.save(obj);
	}

	@Override
	public List<Group> listar() {
		return groupRepository.findAll();
	}

	@Override
	public Group leerPorId(Integer id) {
		Optional<Group> op = groupRepository.findById(id);
		return op.isPresent() ? op.get() : new Group(); 
	}

	@Override
	public boolean eliminar(Integer id) {
		groupRepository.deleteById(id);
		return true;
	}

}
