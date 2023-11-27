package br.edu.dombosco.dbcode.requisitos.model;

import br.edu.dombosco.dbcode.bugs.model.Bug;
import br.edu.dombosco.dbcode.test.model.Plano;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Set;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PROJETO")
public class Projeto {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "TITULO")
    private String titulo;
    @Column(name = "DESCRICAO")
    private String descricao;
    @Column(name = "PARTICIPANTES")
    private String parcipantes;
    @Column(name = "DATA_INICIO")
    private String datainicio;
    @Column(name = "DATA_FIM")
    private String datafim;
    @Column(name = "ESTIMATIVAS")
    private String estimativas;

    @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL)
    private Set<Requisito> requisitos;

    @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL)
    private Set<Bug> bugs;

    @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL)
    private Set<Plano> planos;

    public String containsNullFields(){
        StringBuilder fields = new StringBuilder();
        if (titulo == null || titulo.isEmpty()){
            fields.append("\ntitulo");
        }
        if (descricao == null || descricao.isEmpty()){
            fields.append("\ndescricao");
        }
        if (parcipantes == null || parcipantes.isEmpty()){
            fields.append("\nparcipantes");
        }
        if (datainicio == null || datainicio.isEmpty()){
            fields.append("\ndata de inicio");
        }
        if (datafim == null || datafim.isEmpty()){
            fields.append("\ndata fim");
        }
        if (estimativas == null || estimativas.isEmpty()){
            fields.append("\nestimativas");
        }
        return fields.toString();

    }
}
