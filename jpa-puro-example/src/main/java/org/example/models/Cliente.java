package org.example.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.example.valueObjects.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Clientes")
@NamedQuery(name = "Cliente.findById",query = "SELECT c FROM Cliente c where id = :id")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @Embedded
    private Cpf Cpf;
    private String Email;
    private String Nome;
    private LocalDateTime DataDeNascimento;

    @Column(columnDefinition = "boolean default false")
    private boolean EstaExcluido;
    @OneToMany(mappedBy = "Cliente")
    private List<Pedido> Pedidos;
}
