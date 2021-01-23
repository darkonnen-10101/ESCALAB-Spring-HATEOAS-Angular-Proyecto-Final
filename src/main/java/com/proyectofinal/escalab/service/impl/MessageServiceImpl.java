package com.proyectofinal.escalab.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectofinal.escalab.dao.IMessageRepository;
import com.proyectofinal.escalab.entity.Message;
import com.proyectofinal.escalab.service.IMessageService;

@Service
public class MessageServiceImpl implements IMessageService{

	@Autowired
	private IMessageRepository messageRepository;

	@Override
	public Message registrar(Message obj) {
		return messageRepository.save(obj);
	}

	@Override
	public Message modificar(Message obj) {
		return messageRepository.save(obj);
	}

	@Override
	public List<Message> listar() {
		return messageRepository.findAll();
	}

	@Override
	public Message leerPorId(Integer id) {
		Optional<Message> op = messageRepository.findById(id);
		return op.isPresent() ? op.get() : new Message(); 
	}

	@Override
	public boolean eliminar(Integer id) {
		messageRepository.deleteById(id);
		return true;
	}

}
