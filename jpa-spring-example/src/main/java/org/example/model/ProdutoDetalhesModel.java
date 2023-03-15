package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDetalhesModel {
    private int id;
    private String Caracteristica;
    private String Valor;
    private ProdutoModel Produto;
}
