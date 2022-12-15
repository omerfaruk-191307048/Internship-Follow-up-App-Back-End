package YazGel1.StajTakip.business.abstracts;

import YazGel1.StajTakip.core.utilites.results.DataResult;
import YazGel1.StajTakip.core.utilites.results.Result;
import YazGel1.StajTakip.entities.concretes.Hoca;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface HocaService {
    @Autowired
    DataResult addHoca(Hoca hoca);
    @Autowired
    DataResult<List<Hoca>> getAllHoca();
    @Autowired
    DataResult updateHoca(Hoca hoca);
    @Autowired
    DataResult<Hoca> deleteHocaById(Integer hocaId);
    @Autowired
    public DataResult<List<Hoca>> getAllHocaSorted();
    @Autowired
    DataResult<List<Hoca>> getHocaByHocaAdContains(String hocaAd);
    @Autowired
    DataResult<Hoca> getHocaByHocaId(Integer hocaId);
    @Autowired
    Result exportToPdfHoca(HttpServletResponse response);
}
