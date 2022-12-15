package YazGel1.StajTakip.dataAccess.abstracts;

import YazGel1.StajTakip.entities.concretes.Nott;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NottRepository extends JpaRepository<Nott, Integer> {
}
