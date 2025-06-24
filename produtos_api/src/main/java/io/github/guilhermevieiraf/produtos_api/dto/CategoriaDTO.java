package io.github.guilhermevieiraf.produtos_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDTO {

    private UUID id;

    @NotBlank(message = "O nome da categoria não pode estar em branco.")
    @Size(min = 3, max = 100, message = "O nome da categoria deve ter entre {min} e {max} caracteres.")
    private String nome;
}
