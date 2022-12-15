package YazGel1.StajTakip.dataAccess.abstracts;

import YazGel1.StajTakip.entities.concretes.Yetki;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YetkiRepository extends JpaRepository<Yetki, Integer> {
}
