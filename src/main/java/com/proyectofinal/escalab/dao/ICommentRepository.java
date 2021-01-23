package com.proyectofinal.escalab.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectofinal.escalab.entity.Comment;

public interface ICommentRepository extends JpaRepository<Comment, Integer>{

}
