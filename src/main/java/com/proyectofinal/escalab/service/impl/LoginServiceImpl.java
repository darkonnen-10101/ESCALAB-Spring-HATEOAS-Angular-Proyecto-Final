package com.proyectofinal.escalab.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectofinal.escalab.dao.ILoginRepository;
import com.proyectofinal.escalab.entity.AppUser;
import com.proyectofinal.escalab.service.ILoginService;

@Service
public class LoginServiceImpl implements ILoginService {
	
	@Autowired
	private ILoginRepository repo;
	
	@Override
	public int cambiarClave(String clave, String nombre) {
		int rpta = 0;
		try {
			repo.cambiarClave(clave, nombre);
			rpta = 1;
		} catch (Exception e) {
			rpta = 0;
		}
		return rpta;
	}
	
	@Override
	public AppUser verificarNombreUsuario(String usuario) throws Exception {
		AppUser us = null;
		try {
			us = repo.verificarNombreUsuario(usuario);
			us = us != null ? us : new AppUser();
		} catch (Exception e) {
			us = new AppUser();
		}
		return us;
	}

}
