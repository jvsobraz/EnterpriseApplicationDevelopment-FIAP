package org.example.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.example.models.Album;

import java.util.List;

public class AlbumRepository {


    private EntityManager entityManager;

    public AlbumRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Album> findAll() {
        String jpql = "SELECT ab FROM Album ab";
        Query query = entityManager.createQuery(jpql, Album.class);
        return query.getResultList();
    }

    public List<Album> findByName(String name) {
        String jpql = "SELECT ab FROM Album ab WHERE ab.name LIKE '%:name%'";
        Query query = entityManager.createQuery(jpql, Album.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    public List<Album> findByNameNative(String name) {
        Query query = entityManager.createNativeQuery(
                "SELECT * FROM Albums WHERE name = :name (nolock)", Album.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    public Album findById(Long id) {
        Album album = entityManager.find(Album.class, id);
        if (Album == null) {
            //throw new EntityNotFoundException("Album não encontrado");
            return null;
        }
        return Album;
    }

    public void save(Album album) {
        entityManager.getTransaction().begin();

        try {
            entityManager.persist(album);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void delete(Album album) {
        entityManager.getTransaction().begin();

        try {
            entityManager.remove(album);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void deleteById(Long id) {
        entityManager.getTransaction().begin();

        try {
            Album album = entityManager.find(Album.class, id);
            if (album != null) {
                entityManager.remove(album);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void RemoverAlbums(int[] ids)
    {
        entityManager.getTransaction().begin();

        try {
            for (int id:ids) {
                Album album = entityManager.find(Album.class, id);
                if (album != null) {
                    album.setExcluido(true);
                    //entityManager.remove(album);
                    entityManager.merge(album);
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
