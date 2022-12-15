package YazGel1.StajTakip.business.concretes;

import YazGel1.StajTakip.business.abstracts.OgrenciService;
import YazGel1.StajTakip.core.utilites.pdfHelper.OgrenciListPdfHelper;
import YazGel1.StajTakip.core.utilites.results.*;
import YazGel1.StajTakip.dataAccess.abstracts.OgrenciRepository;
import YazGel1.StajTakip.entities.concretes.Ogrenci;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class OgrenciManager implements OgrenciService {
    @Autowired
    private OgrenciRepository ogrenciRepository;

    @Override
    public DataResult addOgrenci(Ogrenci ogrenci) {
        ogrenciRepository.save(ogrenci);
        return new SuccessDataResult("Öğrenci başarıyla eklendi.");
    }

    @Override
    public DataResult<List<Ogrenci>> getAllOgrenci() {
        return new SuccessDataResult<Iterable<Ogrenci>>(ogrenciRepository.findAll(),"Öğrenci başarıyla listelendi.");
    }

    @Override
    public DataResult<Ogrenci> updateOgrenci(Ogrenci ogrenci) {
        Ogrenci ogrenciDB=ogrenciRepository.findById(ogrenci.getOgrNo()).get();
        if(Objects.nonNull(ogrenci.getOgrAd()) && !"".equalsIgnoreCase(ogrenci.getOgrAd())){
            ogrenciDB.setOgrAd(ogrenci.getOgrAd());
        }
        if(Objects.nonNull(ogrenci.getOgrSoyad()) && !"".equalsIgnoreCase(ogrenci.getOgrSoyad())){
            ogrenciDB.setOgrSoyad(ogrenci.getOgrSoyad());
        }
        if(Objects.nonNull(ogrenci.getOgrFakulte()) && !"".equalsIgnoreCase(ogrenci.getOgrFakulte())){
            ogrenciDB.setOgrFakulte(ogrenci.getOgrFakulte());
        }
        if(Objects.nonNull(ogrenci.getOgrOkul()) && !"".equalsIgnoreCase(ogrenci.getOgrOkul())){
            ogrenciDB.setOgrOkul(ogrenci.getOgrOkul());
        }
        if(Objects.nonNull(ogrenci.getOgrBolum()) && !"".equalsIgnoreCase(ogrenci.getOgrBolum())){
            ogrenciDB.setOgrBolum(ogrenci.getOgrBolum());
        }
        if(Objects.nonNull(ogrenci.getOgrSinif()) && !"".equalsIgnoreCase(ogrenci.getOgrSinif())){
            ogrenciDB.setOgrSinif(ogrenci.getOgrSinif());
        }
        if(Objects.nonNull(ogrenci.getOgrEmail()) && !"".equalsIgnoreCase(ogrenci.getOgrEmail())){
            ogrenciDB.setOgrEmail(ogrenci.getOgrEmail());
        }
        if(Objects.nonNull(ogrenci.getOgrSifre()) && !"".equalsIgnoreCase(ogrenci.getOgrSifre())){
            ogrenciDB.setOgrSifre(ogrenci.getOgrSifre());
        }
        if(Objects.nonNull(ogrenci.getOgrTel()) && !"".equalsIgnoreCase(ogrenci.getOgrTel())){
            ogrenciDB.setOgrTel(ogrenci.getOgrTel());
        }
        ogrenciRepository.save(ogrenciDB);
        return new SuccessDataResult<Ogrenci>("Öğrenci başarıyla güncellendi.");
    }

    @Override
    public DataResult<Ogrenci> deleteOgrenciByNo(Integer ogrNo) {
        ogrenciRepository.deleteById(ogrNo);
        return new SuccessDataResult<Ogrenci>("Öğrenci başarıyla silindi.");
    }

    @Override
    public DataResult<List<Ogrenci>> getAllOgrenciSorted() {
        Sort sort = Sort.by(Sort.Direction.ASC,"ogrAd");
        return new SuccessDataResult<List<Ogrenci>>(ogrenciRepository.findAll(sort),"Başarılı");
    }

    @Override
    public DataResult<List<Ogrenci>> getOgrenciByOgrAdContains(String ogrAd) {
        List<Ogrenci> ogrList=ogrenciRepository.getOgrenciByOgrAdContains(ogrAd);
        if (Objects.nonNull(ogrList) && ogrList.size() > 0){
            return new SuccessDataResult<List<Ogrenci>>(ogrList,"Öğrenci başarıyla bulundu.");
        } else {
            return new ErrorDataResult<List<Ogrenci>>(ogrList, "Öğrenci bulunamadı.");
        }
    }

    @Override
    public DataResult<Ogrenci> getOgrenciByOgrNo(Integer ogrNo) {
        Optional<Ogrenci> ogrList = ogrenciRepository.findById(ogrNo);
        if (ogrList.isPresent()) {
            return new SuccessDataResult<Optional<Ogrenci>>(ogrList,"Öğrenci başarıyla bulundu.");
        }
        return new ErrorDataResult<List<Ogrenci>>("Öğrenci bulunamadı.");
    }

    @Override
    public Result exportToPdfOgrenci(HttpServletResponse response) {
        try {
            String fileName = "ogrenci-list";

            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            response.setHeader("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName+".pdf");

            OgrenciListPdfHelper ogrenciListPdfHelper = new OgrenciListPdfHelper(ogrenciRepository.findAll());
            ogrenciListPdfHelper.export(response);
            return new SuccessDataResult(getAllOgrenci().toString());
        }
        catch (Exception exception){
            return new ErrorResult("Bilinmeyen bir hata oluştu.");
        }
    }
}
