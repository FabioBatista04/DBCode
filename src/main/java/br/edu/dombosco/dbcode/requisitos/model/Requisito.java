package br.edu.dombosco.dbcode.requisitos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "REQUIREMENT")
public class Requisito {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NAME", nullable = false, unique = true)
    private String nome;
    @Column(name = "QUALIFICACAO", nullable = false)
    private String qualificacao;
    @Column(name = "DESCRICAO", nullable = false)
    private String descricao;
    @Column(name = "ARQUIVO_ESPECIFICACAO", nullable = false)
    private String file_especificacao;
    @Column(name = "ARQUIVO_DESENHO", nullable = false)
    private String file_desenho;

    @ManyToOne
    @JoinColumn(name = "PROJETO_ID", nullable = false)
    private Projeto projeto;

    public String containsNullFields() {
        StringBuilder fields = new StringBuilder();
        if (this.nome == null || this.nome.isEmpty()) {
            fields.append("\nnome");
        }
        if (this.qualificacao == null || this.qualificacao.isEmpty()) {
            fields.append("\nqualificação");
        }
        if (this.descricao == null || this.descricao.isEmpty()) {
            fields.append("\ndescrição");
        }
        if (this.file_especificacao == null || this.file_especificacao.isEmpty()) {
            fields.append("\nArquivo especificação");
        }
        if (this.file_desenho == null || this.file_desenho.isEmpty()) {
            fields.append("\nArquivo desenho");
        }
        return fields.toString();


    }
}

