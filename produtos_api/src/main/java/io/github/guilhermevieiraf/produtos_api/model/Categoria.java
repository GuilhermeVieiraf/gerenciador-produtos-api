package io.github.guilhermevieiraf.produtos_api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "categoria", schema = "public")
@Data
public class Categoria {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID) // -> diz que o id vai ser gerado automaticamente
    private UUID id;
    @Column(name = "nome", length = 100, nullable = false)
    private String nome;
}
