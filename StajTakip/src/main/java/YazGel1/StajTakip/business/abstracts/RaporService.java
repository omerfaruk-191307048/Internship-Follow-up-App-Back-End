package YazGel1.StajTakip.business.abstracts;

import YazGel1.StajTakip.core.utilites.results.DataResult;
import YazGel1.StajTakip.core.utilites.results.Result;
import YazGel1.StajTakip.entities.concretes.Rapor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface RaporService {
    @Autowired
    DataResult addRapor(Rapor rapor);
    @Autowired
    DataResult<List<Rapor>> getAllRapor();
    @Autowired
    DataResult updateRapor(Rapor rapor);
    @Autowired
    DataResult<Rapor> deleteRaporById(Integer raporId);
    @Autowired
    DataResult<Rapor> getRaporByRaporId(Integer raporId);
    @Autowired
    Result exportToPdfRapor(HttpServletResponse response);
}
