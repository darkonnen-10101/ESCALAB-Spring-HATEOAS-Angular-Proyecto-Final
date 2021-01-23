package com.proyectofinal.escalab.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectofinal.escalab.entity.Post;

public interface IPostRepository extends JpaRepository<Post, Integer>{

}
