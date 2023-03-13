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
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String Nome;
    private double Preco;
    //@Column(unique = true)
    //private String Sku;
    //private boolean Excluido;
    //@Embedded
    //private Dimensoes Dimensoes;

    @OneToMany(mappedBy = "Produto")
    private List<Estoque> Estoques;

}
