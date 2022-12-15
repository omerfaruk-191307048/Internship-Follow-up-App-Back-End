package YazGel1.StajTakip.dataAccess.abstracts;

import YazGel1.StajTakip.entities.concretes.Durum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DurumRepository extends JpaRepository<Durum, Integer> {
}
