package org.example.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.models.Produto;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ProdutoRepositoryImp implements ProdutoRepository {

    private EntityManager entityManager;
    @Override
    public Produto findById(int id) {
        return entityManager.find(Produto.class, id);
    }
    @Override
    public Produto findBySku(String Sku)
    {
        return entityManager.find(Produto.class, Sku);
    }

    @Override
    public List<Produto> findByName(String Name) {
        //TODO: implementar metodo de pesquisar por nome
        return null;
    }

    @Override
    public List<Produto> findAll() {
        TypedQuery<Produto> query = entityManager
                .createQuery("SELECT p FROM Produto p", Produto.class);
        return query.getResultList();
    }
    @Override
    public void save(Produto produto) {
        try {
            entityManager.getTransaction().begin();

            entityManager.persist(produto);
            entityManager.getTransaction().commit();
        }
        catch (Exception e)
        {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
    @Override
    public void update(Produto produto) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(produto);
            entityManager.getTransaction().commit();
        }
        catch (Exception e)
        {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
    @Override
    public void delete(Produto produto) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(produto);
            entityManager.getTransaction().commit();
        }
        catch (Exception e)
        {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
}