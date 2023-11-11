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
@Table(name = "REQUIREMENT")
public class Requisito {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NAME", nullable = false, unique = true)
    private String nome;
    @Column(name = "QUALIFICATION", nullable = false)
    private String qualificacao;
    @Column(name = "DESCRIPTION", nullable = false)
    private String descricao;
    @Column(name = "FILE_ESPECIFICATION", nullable = false)
    private String file_especificacao;
    @Column(name = "FILE_LAYOUT", nullable = false)
    private String file_desenho;
}

