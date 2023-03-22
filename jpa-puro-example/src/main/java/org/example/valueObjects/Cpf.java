package org.example.valueObjects;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Cpf extends Documento{
    public String Numero;
    private LocalDateTime DataExpedicao;
    private String OrgaoExpedidor;

    @Override
    public boolean ValidarNumero(String Numero)
    {
        //TODO:Implementar regra de validar CPF
        if(Numero.trim().length() == 11) // "176.548.926-22"
            return true;
        else
            return false;
    }
}
