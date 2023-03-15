package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoModel {
    private int id;
    private String Nome;
    private Double Preco;
    private String Sku;
    private boolean Excluido;

    private List<ProdutoDetalhesModel> Detalhes;
}
