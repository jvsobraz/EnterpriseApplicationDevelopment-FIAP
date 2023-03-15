package org.example.DAO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="produto_detalhes")
public class ProdutoDetalhe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String Caracteristica;
    private String Valor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_produto",referencedColumnName = "id")
    @JsonIgnore
    private Produto produto;

    public ProdutoDetalhe(String Caracteristica, String Valor, Produto produto){
        this.Caracteristica = Caracteristica;
        this.Valor = Valor;
        this.produto = produto;
    }
}
