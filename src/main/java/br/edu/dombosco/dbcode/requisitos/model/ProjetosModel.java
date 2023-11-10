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
public class ProjetosModel {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @Column(nullable = false)
    private String descricao;
    @Column(nullable = false)
    private String parcipantes;
    @Column(nullable = false)
    private String datainicio;
    @Column(nullable = false)
    private String datafim;
    @Column(nullable = false)
    private String estimativas;
    @Column(nullable = false)


    public boolean containsNullFields(){
        return this.titulo == null || this.descricao == null || this.parcipantes == null || this.datainicio == null || this.datafim == null || this.estimativas == null;

    }
}
