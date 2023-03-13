package org.example.valueObjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Endereco {
    private String Cep;
    private String Logradouro;
    @Column(length = 20)
    private String Numero;
    @Column(length = 20)
    private String Complemento;
    private String Bairro;
    private String Cidade;
    @Column(length = 2) //SP,RJ,MG
    private String UF;
}
