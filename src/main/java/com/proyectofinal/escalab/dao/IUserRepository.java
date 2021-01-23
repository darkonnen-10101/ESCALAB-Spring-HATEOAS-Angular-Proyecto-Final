package com.proyectofinal.escalab.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectofinal.escalab.entity.AppUser;

public interface IUserRepository extends JpaRepository<AppUser, Integer>{

	AppUser findOneByUsername(String username);

}
