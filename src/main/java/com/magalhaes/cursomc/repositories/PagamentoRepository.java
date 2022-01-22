package com.magalhaes.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.magalhaes.cursomc.domain.Pagamento;

//Repositório Jpa que faz ações no banco de dados
@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

}