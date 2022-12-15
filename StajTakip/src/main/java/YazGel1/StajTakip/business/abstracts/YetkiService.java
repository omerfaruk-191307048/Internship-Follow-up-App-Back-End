package YazGel1.StajTakip.business.abstracts;

import YazGel1.StajTakip.core.utilites.results.DataResult;
import YazGel1.StajTakip.core.utilites.results.Result;
import YazGel1.StajTakip.entities.concretes.Yetki;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface YetkiService {
    @Autowired
    DataResult addYetki(Yetki yetki);
    @Autowired
    DataResult<List<Yetki>> getAllYetki();
    @Autowired
    DataResult updateYetki(Yetki yetki);
    @Autowired
    DataResult<Yetki> deleteYetkiById(Integer yetkiId);
    @Autowired
    Result exportToPdfYetki(HttpServletResponse response);
}
