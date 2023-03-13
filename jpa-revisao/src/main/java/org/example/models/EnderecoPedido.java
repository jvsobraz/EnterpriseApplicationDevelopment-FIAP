package org.example.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.example.valueObjects.Endereco;

@Entity
public class EnderecoPedido {
    @Id
    private int Id;
    @Embedded
    private Endereco Endereco;
}
