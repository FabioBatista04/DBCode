package br.edu.dombosco.dbcode.test.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CASO_DETALHADO")
public class CasoDetalhado {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "STEP")
    private String passo;
    @Column(name = "ACTION")
    private String acao;
    @Column(name = "EXPECTED_RESULT")
    private String resultadoEsperado;
    @Column(name = "PASS")
    private String passou;

    @ManyToOne
    @JoinColumn(name = "CASO_ID", nullable = false)
    private Caso caso;

    public String containsNullFields() {
        StringBuilder sb = new StringBuilder();
        if (passo == null || passo.isEmpty()) {
            sb.append("\npasso");
        }
        if (acao == null || acao.isEmpty()) {
            sb.append("\nacao");
        }
        if (resultadoEsperado == null || resultadoEsperado.isEmpty()) {
            sb.append("\nresultadoEsperado");
        }
        if (passou == null || passou.isEmpty()) {
            sb.append("\npassou");
        }
        return sb.toString();
    }
}
