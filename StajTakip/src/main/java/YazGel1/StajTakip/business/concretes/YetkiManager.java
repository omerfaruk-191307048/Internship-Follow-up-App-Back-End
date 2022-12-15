package YazGel1.StajTakip.business.concretes;

import YazGel1.StajTakip.business.abstracts.YetkiService;
import YazGel1.StajTakip.core.utilites.pdfHelper.YetkiListPdfHelper;
import YazGel1.StajTakip.core.utilites.results.DataResult;
import YazGel1.StajTakip.core.utilites.results.ErrorResult;
import YazGel1.StajTakip.core.utilites.results.Result;
import YazGel1.StajTakip.core.utilites.results.SuccessDataResult;
import YazGel1.StajTakip.dataAccess.abstracts.YetkiRepository;
import YazGel1.StajTakip.entities.concretes.Yetki;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class YetkiManager implements YetkiService {
    @Autowired
    private YetkiRepository yetkiRepository;

    @Override
    public DataResult addYetki(Yetki yetki) {
        yetkiRepository.save(yetki);
        return new SuccessDataResult("Yetki başarıyla eklendi.");
    }

    @Override
    public DataResult<List<Yetki>> getAllYetki() {
        return new SuccessDataResult<Iterable<Yetki>>(yetkiRepository.findAll(),"Yetki başarıyla listelendi.");
    }

    @Override
    public DataResult updateYetki(Yetki yetki) {
        Yetki yetkiDB = yetkiRepository.findById(yetki.getYetkiId()).get();
        if (Objects.nonNull(yetki.getYetkiAdi()) && !"".equalsIgnoreCase(yetki.getYetkiAdi())){
            yetkiDB.setYetkiAdi(yetki.getYetkiAdi());
        }
        yetkiRepository.save(yetkiDB);
        return new SuccessDataResult<Yetki>("Yetki başarıyla güncellendi.");
    }

    @Override
    public DataResult<Yetki> deleteYetkiById(Integer yetkiId) {
        yetkiRepository.deleteById(yetkiId);
        return new SuccessDataResult<Yetki>("Yetki başarıyla silindi.");
    }

    @Override
    public Result exportToPdfYetki(HttpServletResponse response) {
        try{
            String fileName = "durum-list";

            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            response.setHeader("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName+".pdf");

            YetkiListPdfHelper yetkiListPdfHelper = new YetkiListPdfHelper(yetkiRepository.findAll());
            yetkiListPdfHelper.export(response);
            return new  SuccessDataResult(getAllYetki().toString());
        }
        catch (Exception exception){
            return new ErrorResult("Bilinmeyen bir hata oluştu.");
        }
    }
}
