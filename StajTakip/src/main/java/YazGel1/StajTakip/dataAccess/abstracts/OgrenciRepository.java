package YazGel1.StajTakip.dataAccess.abstracts;

import YazGel1.StajTakip.entities.concretes.Ogrenci;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OgrenciRepository extends JpaRepository<Ogrenci, Integer> {
    List<Ogrenci> getOgrenciByOgrAdContains(String ogrAd);
}
