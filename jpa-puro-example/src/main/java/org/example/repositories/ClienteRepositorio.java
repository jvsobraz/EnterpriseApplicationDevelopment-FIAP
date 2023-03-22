package org.example.repositories;

import jakarta.persistence.EntityManager;
import org.example.models.Cliente;

import java.time.LocalDateTime;

public class ClienteRepositorio {
    private EntityManager entityManager;

    public ClienteRepositorio(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Iterable<Cliente> findAll()
    {
        var jpql = "SELECT c FROM Cliente c";
        var query = entityManager.createQuery(jpql, Cliente.class)
                .setHint("org.hibernate.readOnly", true)
                .setHint("jakarta.persistence.query.timeout", 200000);
        var Clientes = query.getResultList();
        return Clientes;
    }

    public Iterable<Cliente> findAllFiltrandoExcluidos(boolean incluirExcluidos)
    {
        var jpql = "SELECT c FROM Cliente c WHERE EstaExcluido=false";
        if(incluirExcluidos)
            jpql = "SELECT c FROM Cliente c";
        var query = entityManager.createQuery(jpql, Cliente.class);
        var Clientes = query.getResultList();
        return Clientes;
    }


    public Cliente findById(int id)
    {
        //JPQL
        //var jpql = "SELECT c FROM Cliente c where id = :id";
        //var query = entityManager.createQuery(jpql, Cliente.class);
        //query.setParameter("id", id);
        //return query.getSingleResult();

        //COMANDO
        return entityManager.find(Cliente.class, id);
    }

    public Cliente findByIdNamedQuery(int id)
    {
        var query = entityManager.createNamedQuery("Cliente.findById",
                Cliente.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public Cliente findByCpf(String cpf)
    {
        var jpql = "SELECT c FROM Cliente c where cpf = :cpf";
        var query = entityManager.createQuery(jpql, Cliente.class);
        query.setParameter("cpf",cpf);
        return query.getSingleResult();
    }

    public Cliente findByCpfParecido(String cpf)
    {
        var sql = "SELECT * FROM Clientes where cpf LIKE '%"+":cpf%"+"' (NOLOCK)";
        var query = entityManager.createNativeQuery(sql, Cliente.class);
        query.setParameter("cpf",cpf);
        return (Cliente) query.getSingleResult();
    }

    public Iterable<Cliente> findByDataDeNascimento(LocalDateTime inicio,
                                                    LocalDateTime fim)
    {
        var jpql = "SELECT c FROM Cliente c WHERE DataNascimento BETWEEN = :inicio AND " +
                ":fim";
        var query = entityManager.createQuery(jpql, Cliente.class);
        query.setParameter("inicio",inicio);
        query.setParameter("fim",fim);
        return query.getResultList();
    }

    public void InserirCliente(Cliente cliente)
    {
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(cliente);
            entityManager.getTransaction().commit();
        }
        catch (Exception e)
        {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void AtualizarCliente(Cliente cliente)
    {
        try{
            entityManager.getTransaction().begin();
            entityManager.merge(cliente);
            entityManager.getTransaction().commit();
        }
        catch (Exception e)
        {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void InserirOuAtualizarCliente(Cliente cliente)
    {
        try{
            entityManager.getTransaction().begin();

            var cliente_existente = findById(cliente.getId());
            if(cliente_existente != null)
                entityManager.merge(cliente);
            else
                entityManager.persist(cliente);

            entityManager.getTransaction().commit();
        }
        catch (Exception e)
        {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void RemoverCliente(Cliente cliente)
    {
        try{
            entityManager.getTransaction().begin();
            //entityManager.remove(cliente);
            cliente.setEstaExcluido(true);
            entityManager.merge(cliente);
            entityManager.getTransaction().commit();
        }
        catch (Exception e)
        {
            entityManager.getTransaction().rollback();
        }
    }

    public void RemoverClienteById(int id)
    {
        try{
            entityManager.getTransaction().begin();
            //entityManager.remove(cliente);
            var cliente = findById(id);
            cliente.setEstaExcluido(true);
            entityManager.merge(cliente);
            entityManager.getTransaction().commit();
        }
        catch (Exception e)
        {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
}
