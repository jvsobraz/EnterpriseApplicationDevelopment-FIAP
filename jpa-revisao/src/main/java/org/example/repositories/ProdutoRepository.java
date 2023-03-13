package org.example.repositories;

import org.example.models.Produto;

import java.util.List;

public interface ProdutoRepository {
    public Produto findById(int id);
    public Produto findBySku(String Sku);
    public List<Produto> findByName(String Name);
    public List<Produto> findAll();
    public void save(Produto produto);
    public void update(Produto produto);
    public void delete(Produto produto);
}
