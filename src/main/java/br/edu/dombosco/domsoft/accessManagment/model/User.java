package br.edu.dombosco.domsoft.accessManagment.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "EMAIL",nullable = false, unique = true)
    private String email;
    @Column(name = "USERNAME",nullable = false, unique = true)
    private String username;
    @Column(name = "PASSWORD", nullable = false)
    private String password;
    @Column(name = "PROFILE", nullable = false)
    private Profile profile;



    public boolean containsNullFields(){
        return this.email == null || this.username == null || this.password == null || this.profile == null;

    }
}
