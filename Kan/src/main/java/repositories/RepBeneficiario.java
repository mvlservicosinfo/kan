package repositories;

import model.Beneficiario;
import model.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepBeneficiario extends JpaRepository<Beneficiario,Long> {
    @Query("Select s FROM Beneficiario s WHERE s.id=Documento.id")
    List<Documento> findByIDDoc(Long id);
}
