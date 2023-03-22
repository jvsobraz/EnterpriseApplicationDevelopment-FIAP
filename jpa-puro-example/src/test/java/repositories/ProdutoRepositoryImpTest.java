package repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.models.*;
import org.example.repositories.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProdutoRepositoryImpTest {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private ProdutoRepository produtoRepository;

    @BeforeAll
    public void init() {
        entityManagerFactory =
                Persistence.
                        createEntityManagerFactory(
                                "Ecommerce-PU");
        entityManager = entityManagerFactory.
                createEntityManager();

        produtoRepository = new ProdutoRepository(entityManager);
    }

    @Test
    public void testSave() {
        Produto produto = new Produto();
        produto.setNome("Smartphone Samsung Galaxy S20");
        produto.setPreco(2599.90);

        produtoRepository.save(produto);

        assertNotNull(produto.getId());
    }

    @Test
    public void testFindById() {
        Produto produto = new Produto();
        produto.setNome("Smartphone Samsung Galaxy S20");
        produto.setPreco(2599.90);

        produtoRepository.save(produto);

        Produto produtoEncontrado = produtoRepository.
                findById((long) produto.getId());

        assertNotNull(produtoEncontrado);
        assertEquals(produto, produtoEncontrado);
    }

    @Test
    public void testFindAll() {
        Produto produto1 = new Produto();
        produto1.setNome("Smartphone Samsung Galaxy S20");
        produto1.setPreco(2599.90);
        Produto produto2 = new Produto();
        produto2.setNome("Iphone 14 Pro Max");
        produto2.setPreco(25999.90);

        produtoRepository.save(produto1);
        produtoRepository.save(produto2);

        List<Produto> produtosEncontrados = produtoRepository.findAll();

        assertNotNull(produtosEncontrados);
        assertEquals(2, produtosEncontrados.size());
        assertEquals(produto1, produtosEncontrados.get(0));
        assertEquals(produto2, produtosEncontrados.get(1));
    }

    @Test
    public void testDelete() {
        Produto produto = new Produto();
        produto.setNome("Smartphone Samsung Galaxy S20");
        produto.setPreco(2599.90);

        produtoRepository.save(produto);

        produtoRepository.delete(produto);

        assertNull(produtoRepository.findById((long) produto.getId()));
    }

    @AfterAll
    public void finish()
    {
        entityManager.close();
        entityManagerFactory.close();
    }
}