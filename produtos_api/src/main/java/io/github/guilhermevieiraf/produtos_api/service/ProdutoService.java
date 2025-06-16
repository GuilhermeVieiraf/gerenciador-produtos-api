package io.github.guilhermevieiraf.produtos_api.service;

import io.github.guilhermevieiraf.produtos_api.model.Categoria;
import io.github.guilhermevieiraf.produtos_api.model.Produto;
import io.github.guilhermevieiraf.produtos_api.repository.CategoriaRepository;
import io.github.guilhermevieiraf.produtos_api.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public Produto salvar(Produto produto) {

        if (produto.getNome() == null || produto.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do produto é obrigatório.");
        }

        if (produto.getPreco() == null || produto.getPreco().doubleValue() <= 0) {
            throw new IllegalArgumentException("O preço deve ser maior que zero.");
        }

        if (produto.getQuantidadeEstoque() == null || produto.getQuantidadeEstoque() < 0) {
            throw new IllegalArgumentException("A quantidade em estoque não pode ser negativa.");
        }

        if (produto.getCategoria() == null || produto.getCategoria().getId() == null) {
            throw new IllegalArgumentException("Categoria inválida.");
        }

        Categoria categoria = categoriaRepository
                .findById(produto.getCategoria().getId())
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada."));

        produto.setCategoria(categoria);
        return produtoRepository.save(produto);
    }

    public void atualizar(Produto produto){
        if (produto.getId() == null) {
            throw new IllegalArgumentException("Para autalizar, é necessário que o produto já esteja salvo na base.");
        }
        produtoRepository.save(produto);
    }

}
