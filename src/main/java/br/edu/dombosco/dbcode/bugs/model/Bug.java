package br.edu.dombosco.dbcode.bugs.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "BUG")
public class Bug {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "TITULO", nullable = false)
    private String titulo;
    @Column(name = "STATUS", nullable = false)
    private String status;
    @Column(name = "DESCRICAO", nullable = false)
    private String descricao;
    @Column(name = "REPRODUCAO", nullable = false)
    private String reproducao;
    @Column(name = "FILE")
    private String file;
    @Column(name = "CLASSIFICACAO", nullable = false)
    private String classificacao;
    @Column(name = "PRIORIDADE", nullable = false)
    private String prioridade;
    @Column(name = "DATA_CADASTRO", nullable = false)
    private LocalDate data_cadastro;

    public boolean containsNullFields(){
        return this.titulo.isEmpty() || this.descricao.isEmpty() || this.reproducao.isEmpty();
    }
}