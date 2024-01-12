package model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Entity
@Table(name="beneficiario")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Beneficiario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    Long id;
    String nome;
    Integer telefone;
    LocalDate dataNascimento;
    LocalDate dataInclusao;
    LocalDate DataAtualizacao;
}
