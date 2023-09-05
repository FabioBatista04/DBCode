package br.edu.dombosco.domsoft.accessManagment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Profile {
    ACCESS("Acesso",10,80),
    BUG("Bug",90,60),
    REQUIREMENT("Requerimento",150,130),
    TEST("Teste",280,70),
    MANAGEMENT("Gerenciamento",350,150);

    private String profileName;
    private int padding;
    private int width;
}
