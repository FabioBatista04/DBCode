package br.edu.dombosco.dbcode.requisitos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequisitosModel {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String nome;
    @Column(nullable = false)
    private String qualificacao;
    @Column(nullable = false)
    private String descricao;
    @Column(nullable = false)
    private String file_especificacao; // não sei como anexar um arquivo
    @Column(nullable = false)
    private String file_desenho; // não sei como anexar um arquivo


    public boolean containsNullFields(){
        return this.nome == null || this.qualificacao == null  || this.descricao == null || this.file_especificacao == null || this.file_desenho == null;

    }
}

