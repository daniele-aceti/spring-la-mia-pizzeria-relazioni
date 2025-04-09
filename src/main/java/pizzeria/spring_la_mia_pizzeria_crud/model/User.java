package pizzeria.spring_la_mia_pizzeria_crud.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(length = 50, nullable = false)
    private String nome;

    @NotBlank
    @Column(nullable = false)
    private String cognome;

    @NotBlank
    @Column(nullable = false)
    private String dataDiNascita;

    @NotBlank
    @Column(nullable = false)
    private String password;
}
