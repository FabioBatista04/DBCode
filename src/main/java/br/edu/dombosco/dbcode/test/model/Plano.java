package br.edu.dombosco.dbcode.test.model;

import br.edu.dombosco.dbcode.requisitos.model.Projeto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PLANO")
public class Plano {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NAME")
    private String nome;
    @Column(name = "INCLUDE_DATE")
    private String data_inclusao;
    @Column(name = "RESPONSABILITE")
    private String responsavel_inclusao;

    @ManyToOne
    @JoinColumn(name = "PROJETO_ID", nullable = false)
    private Projeto projeto;

    @OneToMany(mappedBy = "plano", cascade = CascadeType.ALL)
    private Set<Cenario> cenarios;


    public String containsNullFields(){
        ArrayList<String> fields = new ArrayList<>();
        if (nome == null || nome.isEmpty()){
            fields.add("nome");
        }
        if (data_inclusao == null || data_inclusao.isEmpty()){
            fields.add("data inclusao");
        }
        if (responsavel_inclusao == null || responsavel_inclusao.isEmpty()){
            fields.add("responsavel inclusao");
        }
        return formatFields(fields);

    }

    private String formatFields(ArrayList<String> fields) {
        if (fields.isEmpty()){
            return "";
        }
        StringBuilder sb = new StringBuilder("\n");
        fields.forEach(field -> sb.append(field).append("\n"));
        return sb.toString();
    }
}
