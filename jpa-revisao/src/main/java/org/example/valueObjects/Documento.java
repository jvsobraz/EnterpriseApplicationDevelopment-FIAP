package org.example.valueObjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public abstract class Documento {
    public String Numero;

    public boolean ValidarNumero(String Numero)
    {
        //TODO:Implementar regra de validar CPF
        if(Numero.trim().length() > 0)
            return true;
        else
            return false;
    }
}
