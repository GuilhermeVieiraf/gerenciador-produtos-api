package io.github.guilhermevieiraf.produtos_api.repository;

import io.github.guilhermevieiraf.produtos_api.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
}
