package com.fitmap.Fitmap.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class RegistroProgreso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "ejercicio_id")
    private Ejercicio ejercicio;

    private int repeticiones;
    private double peso;
    private LocalDate fecha;

}

