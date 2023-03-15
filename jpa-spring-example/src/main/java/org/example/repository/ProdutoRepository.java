package org.example.repository;

import org.example.DAO.Produto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository
        extends CrudRepository<Produto, Long> {
    @Query(value = "SELECT * FROM produtos WHERE sku = ?1 ", nativeQuery = true)
    Optional<Produto> findBySku(String sku);

    @Query(value = "SELECT * FROM produtos WHERE nome like %?1% ", nativeQuery = true)
    Iterable<Produto> findByNome(String Nome);


    Optional<Produto> findById(long id);
}
