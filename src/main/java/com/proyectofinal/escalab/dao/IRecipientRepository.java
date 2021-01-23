package com.proyectofinal.escalab.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectofinal.escalab.entity.Recipient;

public interface IRecipientRepository extends JpaRepository<Recipient, Integer>{

}
