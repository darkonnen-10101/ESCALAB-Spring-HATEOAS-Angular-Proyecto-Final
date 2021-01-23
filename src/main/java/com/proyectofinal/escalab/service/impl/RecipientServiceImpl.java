package com.proyectofinal.escalab.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectofinal.escalab.dao.IRecipientRepository;
import com.proyectofinal.escalab.entity.Recipient;
import com.proyectofinal.escalab.service.IRecipientService;

@Service
public class RecipientServiceImpl implements IRecipientService{

	@Autowired
	private IRecipientRepository recipientRepository;

	@Override
	public Recipient registrar(Recipient obj) {
		return recipientRepository.save(obj);
	}

	@Override
	public Recipient modificar(Recipient obj) {
		return recipientRepository.save(obj);
	}

	@Override
	public List<Recipient> listar() {
		return recipientRepository.findAll();
	}

	@Override
	public Recipient leerPorId(Integer id) {
		Optional<Recipient> op = recipientRepository.findById(id);
		return op.isPresent() ? op.get() : new Recipient(); 
	}

	@Override
	public boolean eliminar(Integer id) {
		recipientRepository.deleteById(id);
		return true;
	}

}
