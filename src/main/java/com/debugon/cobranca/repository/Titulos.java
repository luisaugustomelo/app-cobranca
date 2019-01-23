package com.debugon.cobranca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.debugon.cobranca.model.Titulo;

public interface Titulos extends JpaRepository<Titulo, Long> {

}
