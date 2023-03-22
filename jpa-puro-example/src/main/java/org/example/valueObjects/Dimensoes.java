package org.example.valueObjects;

import jakarta.persistence.Embeddable;
import org.hibernate.annotations.Formula;

@Embeddable
public class Dimensoes {
    private double Largura;
    private double Comprimento;
    private double Altura;
    @Formula("Largura * Altura * Comprimento")
    private double Volume;
}
