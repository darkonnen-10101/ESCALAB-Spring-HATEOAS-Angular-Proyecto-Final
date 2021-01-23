package com.proyectofinal.escalab.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectofinal.escalab.entity.Message;

public interface IMessageRepository extends JpaRepository<Message, Integer>{

}
