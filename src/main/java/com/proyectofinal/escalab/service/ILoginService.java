package com.proyectofinal.escalab.service;

import com.proyectofinal.escalab.entity.AppUser;

public interface ILoginService {
	
	AppUser verificarNombreUsuario(String usuario) throws Exception;
	int cambiarClave(String clave, String nombre) throws Exception;
}
