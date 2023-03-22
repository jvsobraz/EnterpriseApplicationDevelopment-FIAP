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
@Table(name = "Armazens")
public class Armazem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String Descricao;
    @Embedded
    private Endereco Endereco;

    @OneToMany(mappedBy = "Armazem")
    private List<Estoque> Estoques;
}
