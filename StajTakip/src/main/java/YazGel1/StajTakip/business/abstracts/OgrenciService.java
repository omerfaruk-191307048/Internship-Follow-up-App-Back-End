package YazGel1.StajTakip.business.abstracts;

import YazGel1.StajTakip.core.utilites.results.DataResult;
import YazGel1.StajTakip.core.utilites.results.Result;
import YazGel1.StajTakip.entities.concretes.Ogrenci;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface OgrenciService {
    @Autowired
    DataResult addOgrenci(Ogrenci ogrenci);
    @Autowired
    DataResult<List<Ogrenci>> getAllOgrenci();
    @Autowired
    DataResult updateOgrenci(Ogrenci ogrenci);
    @Autowired
    DataResult<Ogrenci> deleteOgrenciByNo(Integer ogrNo);
    @Autowired
    public DataResult<List<Ogrenci>> getAllOgrenciSorted();
    @Autowired
    DataResult<List<Ogrenci>> getOgrenciByOgrAdContains(String ogrAd);
    @Autowired
    DataResult<Ogrenci> getOgrenciByOgrNo(Integer ogrNo);
    @Autowired
    Result exportToPdfOgrenci(HttpServletResponse response);
}
