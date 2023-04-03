package org.example.repositories;

import jakarta.persistence.EntityManager;
import org.example.models.Artist;

import java.time.LocalDateTime;

public class ArtistRepository {
    private EntityManager entityManager;

    public ArtistRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Iterable<Artist> findAll()
    {
        var jpql = "SELECT ar FROM Artist ar";
        var query = entityManager.createQuery(jpql, Artist.class)
                .setHint("org.hibernate.readOnly", true)
                .setHint("jakarta.persistence.query.timeout", 200000);
        var Artists = query.getResultList();
        return Artists;
    }

    public Iterable<Artist> findAllFiltrandoExcluidos(boolean incluirExcluidos)
    {
        var jpql = "SELECT ar FROM Artist ar WHERE EstaExcluido=false";
        if(incluirExcluidos)
            jpql = "SELECT ar FROM Artist ar";
        var query = entityManager.createQuery(jpql, Artist.class);
        var Artists = query.getResultList();
        return Artists;
    }


    public Artist findById(int id)
    {
        return entityManager.find(Artist.class, id);
    }

    public Artist findByIdNamedQuery(int id)
    {
        var query = entityManager.createNamedQuery("Artist.findById",
                Artist.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public Artist findByName(String name)
    {
        var jpql = "SELECT ar FROM Artist ar where name = :name";
        var query = entityManager.createQuery(jpql, Artist.class);
        query.setParameter("name",name);
        return query.getSingleResult();
    }

    public Artist findByNameParecido(String name)
    {
        var sql = "SELECT * FROM Artists where name LIKE '%"+":name%"+"' (NOLOCK)";
        var query = entityManager.createNativeQuery(sql, Artist.class);
        query.setParameter("name",name);
        return (Artist) query.getSingleResult();
    }

    public Iterable<Artist> findByDataDeNascimento(LocalDateTime inicio,
                                                    LocalDateTime fim)
    {
        var jpql = "SELECT ar FROM Artist ar WHERE DataNascimento BETWEEN = :inicio AND " +
                ":fim";
        var query = entityManager.createQuery(jpql, Artist.class);
        query.setParameter("inicio",inicio);
        query.setParameter("fim",fim);
        return query.getResultList();
    }

    public void InserirArtist(Artist artist)
    {
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(artist);
            entityManager.getTransaction().commit();
        }
        catch (Exception e)
        {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void AtualizarArtist(Artist artist)
    {
        try{
            entityManager.getTransaction().begin();
            entityManager.merge(artist);
            entityManager.getTransaction().commit();
        }
        catch (Exception e)
        {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void InserirOuAtualizarArtist(Artist artist)
    {
        try{
            entityManager.getTransaction().begin();

            var artist_existente = findById(artist.getId());
            if(artist_existente != null)
                entityManager.merge(artist);
            else
                entityManager.persist(artist);

            entityManager.getTransaction().commit();
        }
        catch (Exception e)
        {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void RemoverArtist(Artist artist)
    {
        try{
            entityManager.getTransaction().begin();
            //entityManager.remove(artist);
            artist.setEstaExcluido(true);
            entityManager.merge(artist);
            entityManager.getTransaction().commit();
        }
        catch (Exception e)
        {
            entityManager.getTransaction().rollback();
        }
    }

    public void RemoverArtistById(int id)
    {
        try{
            entityManager.getTransaction().begin();
            //entityManager.remove(artist);
            var artist = findById(id);
            artist.setEstaExcluido(true);
            entityManager.merge(artist);
            entityManager.getTransaction().commit();
        }
        catch (Exception e)
        {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
}
