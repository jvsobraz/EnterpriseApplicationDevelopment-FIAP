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
@Table(name = "Pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    @Column(unique = true,
            updatable = false) //8745AB31
    private String Codigo;

    @ManyToOne
    @JoinColumn(name = "IdCliente")
    private Cliente Cliente;

    @OneToOne
    @JoinColumn(name = "id_endereco")
    private EnderecoPedido EnderecoPedido;
}
