package com.proyectofinal.escalab.service;

import com.proyectofinal.escalab.entity.ResetToken;

public interface IResetTokenService {
	
	ResetToken findByToken(String token);
	
	void guardar(ResetToken token);
	
	void eliminar(ResetToken token);
}
