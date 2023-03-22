package org.example.models;

import jakarta.persistence.*;
import org.example.valueObjects.Endereco;

@Entity
public class EnderecoPedido {
    @Id
    private int Id;
    @Embedded
    private Endereco Endereco;


    @OneToOne
    private Pedido Pedido;
}
