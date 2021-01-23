package com.proyectofinal.escalab.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectofinal.escalab.dao.IRoleRepository;
import com.proyectofinal.escalab.entity.Role;
import com.proyectofinal.escalab.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService{

	@Autowired
	private IRoleRepository roleRepository;

	@Override
	public Role registrar(Role obj) {
		return roleRepository.save(obj);
	}

	@Override
	public Role modificar(Role obj) {
		return roleRepository.save(obj);
	}

	@Override
	public List<Role> listar() {
		return roleRepository.findAll();
	}

	@Override
	public Role leerPorId(Integer id) {
		Optional<Role> op = roleRepository.findById(id);
		return op.isPresent() ? op.get() : new Role(); 
	}

	@Override
	public boolean eliminar(Integer id) {
		roleRepository.deleteById(id);
		return true;
	}

}
