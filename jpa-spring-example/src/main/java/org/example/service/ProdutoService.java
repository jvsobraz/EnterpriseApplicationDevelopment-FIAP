package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.DAO.Produto;
import org.example.repository.ProdutoDetalheRepository;
import org.example.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ProdutoDetalheRepository produtoDetalheRepository;

    public Iterable<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public Produto findById(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Product not found with id " + id));
    }

    public Produto findBySku(String sku) {
        return produtoRepository.findBySku(sku)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with SKU " + sku));
    }

    public Produto save(Produto product) {
        return produtoRepository.save(product);
    }

    public void deleteById(Long id) {
        produtoRepository.deleteById(id);
    }
}
