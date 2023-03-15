package org.example.controller;

import org.example.DAO.Produto;
import org.example.repository.ProdutoRepository;
import org.example.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public Iterable<Produto> listarProdutos() {
        //return produtoRepository.findAll();
        return produtoService.findAll();
    }

    @GetMapping("/{id}")
    public Produto buscarProduto(@PathVariable Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }

    @GetMapping("/sku/{sku}")
    public Produto buscarProdutoPorSku(@PathVariable String sku) {
        return produtoRepository.findBySku(sku)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }

    @GetMapping("/nome/{nome}")
    public Iterable<Produto> buscarProdutosPorNome(@PathVariable String nome) {
        return produtoRepository.findByNome(nome);
    }

    @PostMapping
    public Produto adicionarProduto(@RequestBody Produto produto) {
        return produtoRepository.save(produto);
    }

    @PutMapping("/{id}")
    public Produto atualizarProduto(@PathVariable Long id, @RequestBody Produto produtoAtualizado) {
        Produto produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

        produtoExistente.setNome(produtoAtualizado.getNome());
        produtoExistente.setPreco(produtoAtualizado.getPreco());
        produtoExistente.setDetalhes(produtoAtualizado.getDetalhes());

        return produtoRepository.save(produtoExistente);
    }

    @DeleteMapping("/{id}")
    public void removerProduto(@PathVariable Long id) {
        produtoRepository.deleteById(id);
    }
}