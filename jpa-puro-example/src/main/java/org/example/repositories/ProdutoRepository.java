package org.example.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.example.models.Estoque;
import org.example.models.Produto;

import java.util.List;

public class ProdutoRepository {


    private EntityManager entityManager;

    public ProdutoRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Produto> findAll() {
        String jpql = "SELECT p FROM Produto p";
        Query query = entityManager.createQuery(jpql, Produto.class);
        return query.getResultList();
    }

    public List<Produto> findAllComEstoques() {
        String jpql = "SELECT p FROM Produto p INNER JOIN Estoque e ON e.IdProduto = p.Id";
        Query query = entityManager.createQuery(jpql, Produto.class);
        return query.getResultList();
    }

    public Estoque buscarEstoque(int idProduto)
    {
        String jpql = "SELECT e FROM Estoque e";
        Query query = entityManager.createQuery(jpql, Estoque.class);
        return (Estoque) query.getSingleResult();
    }

    public List<Produto> findByNome(String nome) {
        String jpql = "SELECT p FROM Produto p WHERE p.nome LIKE '%:nome%'";
        Query query = entityManager.createQuery(jpql, Produto.class);
        query.setParameter("nome", nome);
        return query.getResultList();
    }

    public List<Produto> findByPrecoBetween(Double min, Double max) {
        String jpql = "SELECT p FROM Produto p WHERE p.preco BETWEEN :min AND :max";
        Query query = entityManager.createQuery(jpql, Produto.class);
        query.setParameter("min", min);
        query.setParameter("max", max);
        return query.getResultList();
    }

    public List<Produto> findBySku(Integer Sku) {
        Query query = entityManager.createNamedQuery("Produto.findBySku", Produto.class);
        query.setParameter("Sku", Sku);
        return query.getResultList();
    }


    public List<Produto> findByNomeNative(String nome) {
        Query query = entityManager.createNativeQuery(
                "SELECT * FROM Produtos WHERE nome = :nome (nolock)", Produto.class);
        query.setParameter("nome", nome);
        return query.getResultList();
    }

    public void vincularEstoque(Produto produto, Estoque estoque) {
        entityManager.getTransaction().begin();

        try {
            produto.getEstoques().add(estoque);
            entityManager.merge(produto);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

//    public List<Produto> findAll() {
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Produto> criteriaQuery = criteriaBuilder.createQuery(Produto.class);
//        Root<Produto> root = criteriaQuery.from(Produto.class);
//        criteriaQuery.select(root);
//        return entityManager.createQuery(criteriaQuery).getResultList();
//    }

    public Produto findById(Long id) {
        Produto produto = entityManager.find(Produto.class, id);
        if (produto == null) {
            //throw new EntityNotFoundException("Produto não encontrado");
            return null;
        }
        return produto;
    }

    public void save(Produto produto) {
        entityManager.getTransaction().begin();

        try {
            entityManager.persist(produto);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void delete(Produto produto) {
        entityManager.getTransaction().begin();

        try {
            entityManager.remove(produto);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void deleteById(Long id) {
        entityManager.getTransaction().begin();

        try {
            Produto produto = entityManager.find(Produto.class, id);
            if (produto != null) {
                entityManager.remove(produto);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void RemoverProdutos(int[] ids)
    {
        entityManager.getTransaction().begin();

        try {
                for (int id:ids) {
                    Produto produto = entityManager.find(Produto.class, id);
                    if (produto != null) {
                    produto.setExcluido(true);
                    //entityManager.remove(produto);
                    entityManager.merge(produto);
                    entityManager.flush();
                }
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
}
