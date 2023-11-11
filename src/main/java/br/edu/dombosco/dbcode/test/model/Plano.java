package br.edu.dombosco.dbcode.test.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PLANO")
public class Plano {
    @Id
    private Long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "data_inclusao")
    private Date data_inclusao;
    @Column(name = "responsavel_inclusao")
    private String responsavel_inclusao;
}
