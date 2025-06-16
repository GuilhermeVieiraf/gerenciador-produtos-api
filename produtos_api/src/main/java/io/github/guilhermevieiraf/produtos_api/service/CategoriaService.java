package io.github.guilhermevieiraf.produtos_api.service;

import io.github.guilhermevieiraf.produtos_api.model.Categoria;
import io.github.guilhermevieiraf.produtos_api.repository.CategoriaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public Categoria salvar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public void atualizar(Categoria categoria) {
        if (categoria.getId() == null) {
            throw new IllegalArgumentException("Para autalizar, é necessário que a categoria já esteja salvo na base.");
        }
        categoriaRepository.save(categoria);
    }

    public void deletar(Categoria categoria) {
        categoriaRepository.delete(categoria);
    }

    public Optional<Categoria> buscarPorId(UUID id) {
        return categoriaRepository.findById(id);
    }

    public List<Categoria> buscarTodos() {
        return categoriaRepository.findAll();
    }

}
