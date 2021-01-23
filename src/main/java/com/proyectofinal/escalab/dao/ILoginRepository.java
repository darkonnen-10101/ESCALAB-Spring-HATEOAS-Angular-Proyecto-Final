package com.proyectofinal.escalab.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proyectofinal.escalab.entity.AppUser;

public interface ILoginRepository extends JpaRepository<AppUser, Integer> {
	
	@Query("FROM AppUser us where us.username = :usuario")
	AppUser verificarNombreUsuario(@Param("usuario") String usuario) throws Exception;
	
	@Transactional
	@Modifying
	@Query("UPDATE AppUser us SET us.password = :clave WHERE us.username = :nombre")
	void cambiarClave(@Param("clave") String clave, @Param("nombre") String nombre) throws Exception;
}

