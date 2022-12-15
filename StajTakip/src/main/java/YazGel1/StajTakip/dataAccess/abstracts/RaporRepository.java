package YazGel1.StajTakip.dataAccess.abstracts;

import YazGel1.StajTakip.entities.concretes.Rapor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RaporRepository extends JpaRepository<Rapor, Integer> {
}
