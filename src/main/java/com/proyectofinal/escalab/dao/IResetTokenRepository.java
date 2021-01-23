package com.proyectofinal.escalab.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectofinal.escalab.entity.ResetToken;

public interface IResetTokenRepository extends JpaRepository<ResetToken, Integer> {
	
	ResetToken findByToken(String token);

}
