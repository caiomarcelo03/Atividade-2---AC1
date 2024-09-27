package com.example.demo;

import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.example.demo.model.Categoria;
import com.example.demo.model.produto;
import com.example.demo.repository.CategoriaRepository;
import com.example.demo.repository.ProdutoRepository;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @SuppressWarnings("unchecked")
	@Bean
    public CommandLineRunner demo(ProdutoRepository produtoRepository, @SuppressWarnings("rawtypes") CategoriaRepository categoriaRepository) {
        return (args) -> {
            // Criando categorias
            Categoria categoria1 = new Categoria(null, "Eletrônicos");
            Categoria categoria2 = new Categoria(null, "Móveis");
            categoriaRepository.save(categoria1);
            categoriaRepository.save(categoria2);

            // Criando produtos
            produto produto1 = new produto(null, "TV", 2000.0);
            produto produto2 = new produto(null, "Sofá", 1500.0);
            produto produto3 = new produto(null, "Celular", 1200.0);

            produto1.setCategoria(categoria1);
            produto2.setCategoria(categoria2);
            produto3.setCategoria(categoria1);

            produtoRepository.save(produto1);
            produtoRepository.save(produto2);
            produtoRepository.save(produto3);

            // Testando consultas
            System.out.println("Produtos com preço maior que 1300.0:");
            List<produto> produtosCaros = produtoRepository.findByPrecoGreaterThan(1300.0);
            produtosCaros.forEach(produto -> System.out.println(produto.getNome()));

            System.out.println("Produtos com preço menor ou igual a 1300.0:");
            List<produto> produtosBaratos = produtoRepository.findByPrecoLessThanEqual(1300.0);
            produtosBaratos.forEach(produto -> System.out.println(produto.getNome()));

            System.out.println("Produtos cujo nome começa com 'C':");
            List<produto> produtosComC = produtoRepository.findByNomeStartingWith("C");
            produtosComC.forEach(produto -> System.out.println(produto.getNome()));

            System.out.println("Categorias cujo nome começa com 'E':");
            List<Categoria> categoriasComE = categoriaRepository.findByNomeStartingWith("E");
            categoriasComE.forEach(categoria -> System.out.println(categoria.getNome()));

            System.out.println("Categoria com ID 1 e seus produtos:");
            Categoria categoriaComProdutos = (Categoria) categoriaRepository.findByIdWithProdutos(1L);
            System.out.println(categoriaComProdutos.getNome() + " contém os seguintes produtos:");
            categoriaComProdutos.getProdutos().forEach(produto -> System.out.println(produto.getNome()));
        };
    }
}
