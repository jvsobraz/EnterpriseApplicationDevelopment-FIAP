package org.example.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Estoques")
public class Estoque {

    @Id
    private int IdProduto;
    @Id
    private int IdArmazem;


    @ManyToOne
    @JoinColumn(name = "IdProduto")
    private Produto Produto;


    @ManyToOne
    @JoinColumn(name = "IdArmazem")
    private Armazem Armazem;
}
