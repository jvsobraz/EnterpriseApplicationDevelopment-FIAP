package org.example.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.example.valueObjects.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Produtos")
@NamedQuery(name = "Produto.findBySku", query = "SELECT p FROM Produto p WHERE p.Sku =:Sku")
@NamedQuery(name = "Produto.findById", query = "SELECT p FROM Produto p WHERE p.id =:id")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String Nome;
    private double Preco;
    //@Column(unique = true)
    private String Sku;
    private boolean Excluido;
//    @Embedded
//    private Dimensoes Dimensoes;

    @OneToMany(mappedBy = "Produto", fetch = FetchType.LAZY)
    private List<Estoque> Estoques;

}
