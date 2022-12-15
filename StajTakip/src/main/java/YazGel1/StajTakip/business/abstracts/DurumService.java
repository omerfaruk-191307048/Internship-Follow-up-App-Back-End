package YazGel1.StajTakip.business.abstracts;

import YazGel1.StajTakip.core.utilites.results.DataResult;
import YazGel1.StajTakip.core.utilites.results.Result;
import YazGel1.StajTakip.entities.concretes.Durum;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface DurumService {
    @Autowired
    DataResult addDurum(Durum durum);
    @Autowired
    DataResult<List<Durum>> getAllDurum();
    @Autowired
    DataResult updateDurum(Durum durum);
    @Autowired
    DataResult<Durum> deleteDurumById(Integer durumId);
    @Autowired
    Result exportToPdfDurum(HttpServletResponse response);
}
