package com.petlar.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do pet é obrigatório.")
    private String nome;

    @NotBlank(message = "O tipo do pet é obrigatório.")
    private String tipo;

    @Past(message = "A data de nascimento deve estar no passado.")
    private LocalDate dataNascimento;

    @DecimalMin(value = "0.0", inclusive = false, message = "O valor deve ser maior que zero.")
    private BigDecimal valorAdocao;

    @Pattern(
            regexp = "^\\(\\d{2}\\)\\s\\d{4,5}-\\d{4}$",
            message = "O telefone deve estar no formato (XX) XXXX-XXXX ou (XX) XXXXX-XXXX."
    )
    private String telefoneDono;
}
