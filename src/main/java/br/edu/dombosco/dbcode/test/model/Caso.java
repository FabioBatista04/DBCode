package br.edu.dombosco.dbcode.test.model;

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

import java.util.Set;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CASO")
public class Caso {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NAME")
    private String nome;
    @Column(name = "REQUIREMENT")
    private String preRequisitos;
    @Column(name = "DESCRIPTION")
    private String descricao;
    @Column(name = "COMPLEXITY")
    private String complexidade;


    @OneToMany(mappedBy = "caso")
    private Set<CasoDetalhado> casosDetalhados;

    @ManyToOne
    @JoinColumn(name = "CENARIO_ID", nullable = false)
    private Cenario cenario;

    public String containsNullFields() {
        StringBuilder sb = new StringBuilder();
        if (nome == null || nome.isEmpty()) {
            sb.append("\nnome");
        }
        if (preRequisitos == null || preRequisitos.isEmpty()) {
            sb.append("\npreRequisitos");
        }
        if (descricao == null || descricao.isEmpty()) {
            sb.append("\ndescricao");
        }
        if (complexidade == null || complexidade.isEmpty()) {
            sb.append("\ncomplexidade");
        }
        return sb.toString();
    }
}
