package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.models.Produto;
import org.example.repositories.ProdutoRepository;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("Ecommerce-PU");
        EntityManager entityManager = entityManagerFactory.
                createEntityManager();

        var repository = new ProdutoRepository(entityManager);
        //repository.findById((long)1);
        repository.findAll();

        entityManager.close();
        entityManagerFactory.close();
    }
}