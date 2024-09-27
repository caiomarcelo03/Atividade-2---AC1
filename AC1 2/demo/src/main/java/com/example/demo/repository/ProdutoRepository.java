package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.produto;

public interface ProdutoRepository extends JpaRepository<produto, Long> {

    // Método para encontrar produtos com preço maior que um valor específico
    List<produto> findByPrecoGreaterThan(Double valor);

    // Método para encontrar produtos com preço menor ou igual que um valor específico
    List<produto> findByPrecoLessThanEqual(Double valor);

    // Método para encontrar produtos cujo nome começa com determinado prefixo
    List<produto> findByNomeStartingWith(String nome);
}
