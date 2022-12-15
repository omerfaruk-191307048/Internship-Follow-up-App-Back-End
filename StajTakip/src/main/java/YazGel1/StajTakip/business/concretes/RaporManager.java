package YazGel1.StajTakip.business.concretes;

import YazGel1.StajTakip.business.abstracts.RaporService;
import YazGel1.StajTakip.core.utilites.pdfHelper.RaporListPdfHelper;
import YazGel1.StajTakip.core.utilites.results.*;
import YazGel1.StajTakip.dataAccess.abstracts.RaporRepository;
import YazGel1.StajTakip.entities.concretes.Hoca;
import YazGel1.StajTakip.entities.concretes.Rapor;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class RaporManager implements RaporService {
    @Autowired
    private RaporRepository raporRepository;

    @Override
    public DataResult addRapor(Rapor rapor) {
        raporRepository.save(rapor);
        return new SuccessDataResult("Rapor başarıyla eklendi.");
    }

    @Override
    public DataResult<List<Rapor>> getAllRapor() {
        return new SuccessDataResult<Iterable<Rapor>>(raporRepository.findAll(),"Rapor başarıyla listelendi.");
    }

    @Override
    public DataResult updateRapor(Rapor rapor) {
        Optional<Rapor> raporUpdateDBOpt = raporRepository.findById(rapor.getRaporId());
        Rapor raporUpdateDB;
        if (raporUpdateDBOpt.isEmpty()){
            System.out.println("Rapor bulunamadı.");
            return new ErrorDataResult("Hata");
        }else {
            raporUpdateDB = raporUpdateDBOpt.get();
        }
        if (Objects.nonNull(rapor.getOgrenci().getOgrNo()) && !"".equalsIgnoreCase(rapor.getOgrenci().getOgrNo().toString())){
            raporUpdateDB.setOgrenci(rapor.getOgrenci());
        }
        if (Objects.nonNull(rapor.getHoca().getHocaId()) && !"".equalsIgnoreCase(rapor.getHoca().getHocaId().toString())){
            raporUpdateDB.setHoca(rapor.getHoca());
        }
        if (Objects.nonNull(rapor.getDurum().getDurumId()) && !"".equalsIgnoreCase(rapor.getDurum().getDurumId().toString())){
            raporUpdateDB.setDurum(rapor.getDurum());
        }
        if (Objects.nonNull(rapor.getNott().getNottId()) && !"".equalsIgnoreCase(rapor.getNott().getNottId().toString())){
            raporUpdateDB.setNott(rapor.getNott());
        }
        if (Objects.nonNull(rapor.getStjTur().getStjTurId()) && !"".equalsIgnoreCase(rapor.getStjTur().getStjTurId().toString())){
            raporUpdateDB.setStjTur(rapor.getStjTur());
        }
        if (Objects.nonNull(rapor.getStjBas()) && !"".equalsIgnoreCase(rapor.getStjBas())){
            raporUpdateDB.setStjBas(rapor.getStjBas());
        }
        if (Objects.nonNull(rapor.getStjBit()) && !"".equalsIgnoreCase(rapor.getStjBit())){
            raporUpdateDB.setStjBit(rapor.getStjBit());
        }
        if (Objects.nonNull(rapor.getStjGun()) && !"".equalsIgnoreCase(rapor.getStjGun())){
            raporUpdateDB.setStjGun(rapor.getStjGun());
        }
        if (Objects.nonNull(rapor.getStjFirma()) && !"".equalsIgnoreCase(rapor.getStjFirma())){
            raporUpdateDB.setStjFirma(rapor.getStjFirma());
        }
        if (Objects.nonNull(rapor.getStjDonem()) && !"".equalsIgnoreCase(rapor.getStjDonem())){
            raporUpdateDB.setStjDonem(rapor.getStjDonem());
        }
        if (Objects.nonNull(rapor.getRaporPath()) && !"".equalsIgnoreCase(rapor.getRaporPath())){
            raporUpdateDB.setRaporPath(rapor.getRaporPath());
        }
        raporRepository.save(raporUpdateDB);
        return new SuccessDataResult<Rapor>("Rapor başarıyla güncellendi.");
    }

    @Override
    public DataResult<Rapor> deleteRaporById(Integer raporId) {
        raporRepository.deleteById(raporId);
        return new SuccessDataResult<Rapor>("Grup başarıyla silindi.");
    }

    @Override
    public DataResult<Rapor> getRaporByRaporId(Integer raporId) {
        Optional<Rapor> raporList = raporRepository.findById(raporId);
        if (raporList.isPresent()){
            return new SuccessDataResult<Optional<Rapor>>(raporList,"Rapor başarıyla bulundu.");
        }
        return new ErrorDataResult<List<Rapor>>("Rapor bulunamadı.");
    }

    @Override
    public Result exportToPdfRapor(HttpServletResponse response) {
        try{
            String fileName = "rapor-list";

            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            response.setHeader("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName+".pdf");

            RaporListPdfHelper raporListPdfHelper = new RaporListPdfHelper(raporRepository.findAll());
            raporListPdfHelper.export(response);
            return new SuccessDataResult(getAllRapor().toString());
        }
        catch (Exception exception){
            return new ErrorResult("Bilinmeyen bir hata oluştu.");
        }
    }
}
