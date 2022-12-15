package YazGel1.StajTakip.dataAccess.abstracts;

import YazGel1.StajTakip.core.utilites.results.DataResult;
import YazGel1.StajTakip.entities.concretes.Hoca;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HocaRepository extends JpaRepository<Hoca, Integer> {
    List<Hoca> getHocaByHocaAdContains(String hocaAd);
}
