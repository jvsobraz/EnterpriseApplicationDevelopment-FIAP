package org.example.DAO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "produtos")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String Nome;
    private Double Preco;
    private String Sku;
    private boolean Excluido;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true
    ,fetch = FetchType.LAZY)
    private List<ProdutoDetalhe> Detalhes = new ArrayList<>();

    public Produto(String Nome, Double Preco, String Sku)
    {
        this.Nome = Nome;
        this.Preco = Preco;
        this.Sku = Sku;
    }

    public Produto(String Nome, Double Preco, List<ProdutoDetalhe> Detalhes)
    {
        this.Nome = Nome;
        this.Preco = Preco;
        this.Detalhes = Detalhes;
    }
}
