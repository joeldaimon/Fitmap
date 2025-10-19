package com.fitmap.Fitmap.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
public class Rutina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "rutina_ejercicios",
            joinColumns = @JoinColumn(name = "rutina_id"),
            inverseJoinColumns = @JoinColumn(name = "ejercicio_id")
    )

    private Set<Ejercicio> ejercicios = new HashSet<>();

}
