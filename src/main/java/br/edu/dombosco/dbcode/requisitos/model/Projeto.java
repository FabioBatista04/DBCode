package br.edu.dombosco.dbcode.requisitos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Projeto {
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

    public ArrayList<String> containsNullFields(){
        ArrayList<String> fields = new ArrayList<>();
        if (titulo == null || titulo.isEmpty()){
            fields.add("titulo");
        }
        if (descricao == null || descricao.isEmpty()){
            fields.add("descricao");
        }
        if (parcipantes == null || parcipantes.isEmpty()){
            fields.add("parcipantes");
        }
        if (datainicio == null || datainicio.isEmpty()){
            fields.add("datainicio");
        }
        if (datafim == null || datafim.isEmpty()){
            fields.add("datafim");
        }
        if (estimativas == null || estimativas.isEmpty()){
            fields.add("estimativas");
        }
        return fields;

    }
}
