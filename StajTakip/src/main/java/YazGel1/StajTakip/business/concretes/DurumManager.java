package YazGel1.StajTakip.business.concretes;

import YazGel1.StajTakip.business.abstracts.DurumService;
import YazGel1.StajTakip.core.utilites.pdfHelper.DurumListPdfHelper;
import YazGel1.StajTakip.core.utilites.results.DataResult;
import YazGel1.StajTakip.core.utilites.results.ErrorResult;
import YazGel1.StajTakip.core.utilites.results.Result;
import YazGel1.StajTakip.core.utilites.results.SuccessDataResult;
import YazGel1.StajTakip.dataAccess.abstracts.DurumRepository;
import YazGel1.StajTakip.entities.concretes.Durum;
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
public class DurumManager implements DurumService {
    @Autowired
    private DurumRepository durumRepository;

    @Override
    public DataResult addDurum(Durum durum) {
        durumRepository.save(durum);
        return new SuccessDataResult("Durum başarıyla eklendi.");
    }

    @Override
    public DataResult<List<Durum>> getAllDurum() {
        return new SuccessDataResult<Iterable<Durum>>(durumRepository.findAll(),"Durum başarıyla listelendi.");
    }

    @Override
    public DataResult updateDurum(Durum durum) {
        Durum durumDB=durumRepository.findById(durum.getDurumId()).get();
        if(Objects.nonNull(durum.getDurumAdi()) && !"".equalsIgnoreCase(durum.getDurumAdi())){
            durumDB.setDurumAdi(durum.getDurumAdi());
        }
        durumRepository.save(durumDB);
        return new SuccessDataResult<Durum>("Durum başarıyla güncellendi.");
    }

    @Override
    public DataResult<Durum> deleteDurumById(Integer durumId) {
        durumRepository.deleteById(durumId);
        return new SuccessDataResult<Durum>("Durum başarıyla silindi.");
    }

    @Override
    public Result exportToPdfDurum(HttpServletResponse response) {
        try{
            String fileName = "durum-list";

            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            response.setHeader("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName+".pdf");

            DurumListPdfHelper durumListPdfHelper = new DurumListPdfHelper(durumRepository.findAll());
            durumListPdfHelper.export(response);
            return new SuccessDataResult(getAllDurum().toString());
        }
        catch (Exception exception){
            return new ErrorResult("Bilinmeyen bir hata oluştu.");
        }
    }
}
