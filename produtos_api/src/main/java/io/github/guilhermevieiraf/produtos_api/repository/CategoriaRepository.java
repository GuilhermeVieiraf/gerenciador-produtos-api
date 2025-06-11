package io.github.guilhermevieiraf.produtos_api.repository;

import io.github.guilhermevieiraf.produtos_api.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoriaRepository extends JpaRepository<Categoria, UUID> {
}
