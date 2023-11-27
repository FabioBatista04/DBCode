package br.edu.dombosco.dbcode.test.model;


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

import java.util.Collection;
import java.util.Set;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CENARIO")
public class Cenario {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NAME")
    private String nome;
    @Column(name = "TIME")
    private String tempo;
    @Column(name = "STATUS")
    private String status;

    @ManyToOne
    @JoinColumn(name = "PLANO_ID", nullable = false)
    private Plano plano;

    @OneToMany(mappedBy = "cenario", cascade = CascadeType.ALL)
    private Set<Caso> casos;


    public String containsNullFields() {
        StringBuilder sb = new StringBuilder();
        if (nome == null || nome.isEmpty()) {
            sb.append("\nnome");
        }
        if (tempo == null || tempo.isEmpty()) {
            sb.append("\ntempo");
        }
        if (status == null || status.isEmpty()) {
            sb.append("\nstatus");
        }
        return sb.toString();
    }
}
//Teste:
//
//        Um teste pertence a um projeto e pode ter vários cenários.
//        Relacionamento: Teste para Casos (1:N).
