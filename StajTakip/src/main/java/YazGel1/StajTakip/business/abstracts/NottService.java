package YazGel1.StajTakip.business.abstracts;

import YazGel1.StajTakip.core.utilites.results.DataResult;
import YazGel1.StajTakip.core.utilites.results.Result;
import YazGel1.StajTakip.entities.concretes.Nott;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface NottService {
    @Autowired
    DataResult addNott(Nott nott);
    @Autowired
    DataResult<List<Nott>> getAllNott();
    @Autowired
    DataResult updateNott(Nott nott);
    @Autowired
    DataResult<Nott> deleteNottById(Integer nottId);
    @Autowired
    Result exportToPdfNott(HttpServletResponse response);
}
