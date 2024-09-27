package com.example.demo.repository;

import java.util.List;
import com.example.demo.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    // Método para encontrar categorias cujo nome começa com determinado prefixo
    List<Categoria> findByNomeStartingWith(String nome);

    // Método para buscar uma categoria pelo ID e seus produtos relacionados com LEFT JOIN FETCH
    @Query("SELECT c FROM Categoria c LEFT JOIN FETCH c.produtos WHERE c.id = :id")
    Categoria findByIdWithProdutos(Long id);
}
