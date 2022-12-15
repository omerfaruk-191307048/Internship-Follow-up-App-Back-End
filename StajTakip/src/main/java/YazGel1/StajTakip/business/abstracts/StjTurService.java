package YazGel1.StajTakip.business.abstracts;

import YazGel1.StajTakip.core.utilites.results.DataResult;
import YazGel1.StajTakip.core.utilites.results.Result;
import YazGel1.StajTakip.entities.concretes.StjTur;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface StjTurService {
    @Autowired
    DataResult addStjTur(StjTur stjTur);
    @Autowired
    DataResult<List<StjTur>> getAllStjTur();
    @Autowired
    DataResult updateStjTur(StjTur stjTur);
    @Autowired
    DataResult<StjTur> deleteStjTurById(Integer stjTurId);
    @Autowired
    Result exportToPdfStjTur(HttpServletResponse response);
}
