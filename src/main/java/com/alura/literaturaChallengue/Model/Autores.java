package com.alura.literaturaChallengue.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Autores")
public class Autores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String autor;
    private Integer fechaDeNacimento;
    private Integer fechaDeFallecimiento;

    @OneToMany(mappedBy = "autores", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros = new ArrayList<>();  // Inicializar la lista

    @Override
    public String toString() {
        String librosTitulos = libros.stream()
                .map(Libro::getTitulo)
                .collect(Collectors.joining(", "));
        return "\n------- AUTORES -------" +
                "\nAutor : " + autor +
                "\nFecha de Nacimiento : " + fechaDeNacimento +
                "\nFecha de Fallecimiento : " + fechaDeFallecimiento +
                "\nLibros : " + librosTitulos +
                "\n-----------------------\n";
    }

    public void addLibro(Libro libro) {
        libros.add(libro);
        libro.setAutores(this);
    }
}