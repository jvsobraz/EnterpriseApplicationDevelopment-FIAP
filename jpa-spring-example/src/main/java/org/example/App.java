package org.example;

import jakarta.transaction.Transactional;
import org.example.DAO.Produto;
import org.example.DAO.ProdutoDetalhe;
import org.example.repository.ProdutoDetalheRepository;
import org.example.repository.ProdutoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    @Transactional
    public CommandLineRunner demo(ProdutoRepository produtorepository,
                                  ProdutoDetalheRepository produtoDetalheRepository) {
        return (args) -> {
            var NovoProduto = new Produto("Iphone 14 Pro",5100.00,"IP0");
            NovoProduto.getDetalhes().
                    add(new ProdutoDetalhe("Armazenamento","64GB",NovoProduto));
            produtorepository.save(NovoProduto);
            // save a few customers
            produtorepository.save(new Produto("Iphone 14 Pro", 5100.00,"IP1"));
            produtorepository.save(new Produto("Iphone 13 Pro", 4500.00,"IP2"));
            produtorepository.save(new Produto("Iphone 12 Pro", 4100.00,"IP3"));
            produtorepository.save(new Produto("Iphone 11 Pro", 3500.00,"IP4"));
            produtorepository.save(new Produto("Iphone 11", 3100.00,"IP5"));

            // fetch an individual customer by ID
            var Produto = produtorepository.findById(1L);
        };
    }
}