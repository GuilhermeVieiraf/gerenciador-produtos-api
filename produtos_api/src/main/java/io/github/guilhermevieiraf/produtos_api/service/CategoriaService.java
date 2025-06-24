package io.github.guilhermevieiraf.produtos_api.service;

import io.github.guilhermevieiraf.produtos_api.dto.CategoriaDTO;
import io.github.guilhermevieiraf.produtos_api.model.Categoria;
import io.github.guilhermevieiraf.produtos_api.repository.CategoriaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public Categoria salvar(CategoriaDTO categoriaDTO) {
        Categoria categoria = new Categoria();
        categoria.setNome(categoriaDTO.getNome());
        return categoriaRepository.save(categoria);
    }

    public Categoria atualizar(CategoriaDTO categoriaDTO) {
        if (categoriaDTO.getId() == null) {
            throw new IllegalArgumentException("Para atualizar, é necessário que o ID da categoria seja fornecido.");
        }
        Optional<Categoria> categoriaExistente = categoriaRepository.findById(categoriaDTO.getId());
        if (categoriaExistente.isEmpty()) {
            throw new IllegalArgumentException("Categoria com ID " + categoriaDTO.getId() + " não encontrada para atualização.");
        }
        Categoria categoria = categoriaExistente.get();
        categoria.setNome(categoriaDTO.getNome());
        return categoriaRepository.save(categoria);
    }

    @Transactional
    public void deletar(UUID id) {
        if (!categoriaRepository.existsById(id)) {
            throw new IllegalArgumentException("Categoria com ID " + id + " não encontrada para deleção.");
        }
        categoriaRepository.deleteById(id);
    }

    public Optional<Categoria> buscarPorId(UUID id) {
        return categoriaRepository.findById(id);
    }
}
