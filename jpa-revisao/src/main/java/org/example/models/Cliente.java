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
@Table(name = "Clientes")
public class Cliente {
    @Id
    private int Id;
    @Embedded
    private Cpf Cpf;
    private String Email;
    @OneToMany(mappedBy = "Cliente")
    private List<Pedido> Pedidos;
}
