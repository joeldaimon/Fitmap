package com.fitmap.Fitmap.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Ejercicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private int dificultad;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

}
