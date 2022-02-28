package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.Board;
import com.cos.blog.model.User;

//DAO
//자동으로 bean등록이 된다
//@Repository생략가능
public interface BoardRepository extends JpaRepository<Board, Integer>{

}

