package model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name="documento")
@Getter
@Setter
@EqualsAndHashCode
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    Long id;
    char tipoDocumento;
    String descricao;
    LocalDate dataInclusao;
    LocalDate dataAtualizacao;

}
