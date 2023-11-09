package br.edu.dombosco.dbcode.accessManagment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Profile {
    ANALYST("Analista"),
    TEST("Testador"),
    DEV("Desenvolvedor"),
    ADMIN("Administrador");

    private String profileName;

    @Override
    public String toString() {
        return this.profileName;
    }
}
