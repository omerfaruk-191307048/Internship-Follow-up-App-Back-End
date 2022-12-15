package YazGel1.StajTakip.business.concretes;

import YazGel1.StajTakip.business.abstracts.StjTurService;
import YazGel1.StajTakip.core.utilites.pdfHelper.StjTurListPdfHelper;
import YazGel1.StajTakip.core.utilites.results.DataResult;
import YazGel1.StajTakip.core.utilites.results.ErrorResult;
import YazGel1.StajTakip.core.utilites.results.Result;
import YazGel1.StajTakip.core.utilites.results.SuccessDataResult;
import YazGel1.StajTakip.dataAccess.abstracts.StjTurRepository;
import YazGel1.StajTakip.entities.concretes.StjTur;
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
public class StjTurManager implements StjTurService {
    @Autowired
    private StjTurRepository stjTurRepository;

    @Override
    public DataResult addStjTur(StjTur stjTur) {
        stjTurRepository.save(stjTur);
        return new SuccessDataResult("Staj türü başarıyla eklendi.");
    }

    @Override
    public DataResult<List<StjTur>> getAllStjTur() {
        return new SuccessDataResult<Iterable<StjTur>>(stjTurRepository.findAll(),"Staj türü başarıyla listelendi.");
    }

    @Override
    public DataResult updateStjTur(StjTur stjTur) {
        StjTur stjTurDB = stjTurRepository.findById(stjTur.getStjTurId()).get();
        if (Objects.nonNull(stjTur.getStjTurAd()) && !"".equalsIgnoreCase(stjTur.getStjTurAd())){
            stjTurDB.setStjTurAd(stjTur.getStjTurAd());
        }
        stjTurRepository.save(stjTurDB);
        return new SuccessDataResult<StjTur>("Staj türü başarıyla güncellendi.");
    }

    @Override
    public DataResult<StjTur> deleteStjTurById(Integer stjTurId) {
        stjTurRepository.deleteById(stjTurId);
        return new SuccessDataResult<StjTur>("Staj türü başarıyla silindi.");
    }

    @Override
    public Result exportToPdfStjTur(HttpServletResponse response) {
        try{
            String fileName = "stajturu-list";

            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            response.setHeader("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName+".pdf");

            StjTurListPdfHelper stjTurListPdfHelper = new StjTurListPdfHelper(stjTurRepository.findAll());
            stjTurListPdfHelper.export(response);
            return new SuccessDataResult(getAllStjTur().toString());
        }
        catch (Exception exception){
            return new ErrorResult("Bilinmeyen bir hata oluştu.");
        }

    }
}
