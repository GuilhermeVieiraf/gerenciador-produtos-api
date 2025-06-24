package io.github.guilhermevieiraf.produtos_api.controller;

import io.github.guilhermevieiraf.produtos_api.dto.CategoriaDTO;
import io.github.guilhermevieiraf.produtos_api.model.Categoria;
import io.github.guilhermevieiraf.produtos_api.service.CategoriaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("categoria")
@AllArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<CategoriaDTO> salvar(@RequestBody @Valid CategoriaDTO categoriaDTO) {
        Categoria categoriaSalva = categoriaService.salvar(categoriaDTO);
        System.out.println("Categoria salva: " + categoriaSalva);

        CategoriaDTO categoriaRetornoDTO = new CategoriaDTO(categoriaSalva.getId(), categoriaSalva.getNome());
        return new ResponseEntity<>(categoriaRetornoDTO, HttpStatus.CREATED);
    }

    // Deletar por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable UUID id) {
        try {
            categoriaService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> atualizar(@PathVariable UUID id, @RequestBody @Valid CategoriaDTO categoriaDTO) {
        if (categoriaDTO.getId() == null || !categoriaDTO.getId().equals(id)) {
            categoriaDTO.setId(id);
        }
        Categoria categoriaAtualizada = categoriaService.atualizar(categoriaDTO);
        CategoriaDTO categoriaRetornoDTO = new CategoriaDTO(categoriaAtualizada.getId(), categoriaAtualizada.getNome());
        return ResponseEntity.ok(categoriaRetornoDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> buscarPorId(@PathVariable UUID id) {
        return categoriaService.buscarPorId(id)
                .map(categoria -> new CategoriaDTO(categoria.getId(), categoria.getNome()))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}

