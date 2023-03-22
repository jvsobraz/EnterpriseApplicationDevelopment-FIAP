package org.example.valueObjects;

public class Cnpj extends Documento{
    @Override
    public boolean ValidarNumero(String Numero)
    {
        //TODO:Implementar regra de validar CNPJ
        if(Numero.trim().length() == 14)
            return true;
        else
            return false;
    }
}
