package org.example.valueObjects;

import jakarta.persistence.*;

import javax.validation.constraints.Size;

public class CreditCard {
    private String Numero;
    @Column(length = 3)
    private String CodSeguraca;

    //TODO: fazer anotaçao de validar min/max
    @Size(min = 2, max = 2)
    private int MesVencimento;

    //TODO: fazer anotaçao de validar min/max
    @Size(min = 2, max = 4)
    private int AnoVencimento;
}
