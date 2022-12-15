package YazGel1.StajTakip.business.concretes;

import YazGel1.StajTakip.business.abstracts.HocaService;
import YazGel1.StajTakip.core.utilites.pdfHelper.HocaListPdfHelper;
import YazGel1.StajTakip.core.utilites.results.*;
import YazGel1.StajTakip.dataAccess.abstracts.HocaRepository;
import YazGel1.StajTakip.entities.concretes.Hoca;
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
public class HocaManager implements HocaService {
    @Autowired
    private HocaRepository hocaRepository;

    @Override
    public DataResult addHoca(Hoca hoca) {
        hocaRepository.save(hoca);
        return new SuccessDataResult("Hoca başarıyla eklendi.");
    }

    @Override
    public DataResult<List<Hoca>> getAllHoca() {
        return new SuccessDataResult<Iterable<Hoca>>(hocaRepository.findAll(),"Hoca başarıyla listelendi.");
    }

    @Override
    public DataResult<Hoca> updateHoca(Hoca hoca) {
        Optional<Hoca> hocaUpdateDBOpt = hocaRepository.findById(hoca.getHocaId());
        Hoca hocaUpdateDB;
        if (hocaUpdateDBOpt.isEmpty()){
            System.out.println("Hoca bulunamadı.");
            return new ErrorDataResult("Hata");
        }else {
            hocaUpdateDB = hocaUpdateDBOpt.get();
        }
        if (Objects.nonNull(hoca.getHocaAd()) && !"".equalsIgnoreCase(hoca.getHocaAd())){
            hocaUpdateDB.setHocaAd(hoca.getHocaAd());
        }
        if (Objects.nonNull(hoca.getHocaSoyad()) && !"".equalsIgnoreCase(hoca.getHocaSoyad())){
            hocaUpdateDB.setHocaSoyad(hoca.getHocaSoyad());
        }
        if (Objects.nonNull(hoca.getHocaFakulte()) && !"".equalsIgnoreCase(hoca.getHocaFakulte())){
            hocaUpdateDB.setHocaFakulte(hoca.getHocaFakulte());
        }
        if (Objects.nonNull(hoca.getHocaBolum()) && !"".equalsIgnoreCase(hoca.getHocaBolum())){
            hocaUpdateDB.setHocaBolum(hoca.getHocaBolum());
        }
        if (Objects.nonNull(hoca.getHocaEmail()) && !"".equalsIgnoreCase(hoca.getHocaEmail())){
            hocaUpdateDB.setHocaEmail(hoca.getHocaEmail());
        }
        if (Objects.nonNull(hoca.getHocaSifre()) && !"".equalsIgnoreCase(hoca.getHocaSifre())){
            hocaUpdateDB.setHocaSifre(hoca.getHocaSifre());
        }
        if (Objects.nonNull(hoca.getHocaTel()) && !"".equalsIgnoreCase(hoca.getHocaTel())){
            hocaUpdateDB.setHocaTel(hoca.getHocaTel());
        }
        if (Objects.nonNull(hoca.getYetki().getYetkiId()) && !"".equalsIgnoreCase(hoca.getYetki().getYetkiId().toString())){
            hocaUpdateDB.setYetki(hoca.getYetki());
        }
        hocaRepository.save(hocaUpdateDB);
        return new SuccessDataResult<Hoca>("Hoca başarıyla güncellendi.");
    }

    @Override
    public DataResult<Hoca> deleteHocaById(Integer hocaId) {
        hocaRepository.deleteById(hocaId);
        return new SuccessDataResult<Hoca>("Grup başarıyla silindi.");
    }

    @Override
    public DataResult<List<Hoca>> getAllHocaSorted() {
        Sort sort = Sort.by(Sort.Direction.ASC,"hocaAd");
        return new SuccessDataResult<List<Hoca>>(hocaRepository.findAll(sort),"Başarılı");
    }

    @Override
    public DataResult<List<Hoca>> getHocaByHocaAdContains(String hocaAd) {
        List<Hoca> hocaList = hocaRepository.getHocaByHocaAdContains(hocaAd);
        if (Objects.nonNull(hocaList) && hocaList.size() > 0){
            return new SuccessDataResult<List<Hoca>>(hocaList,"Hoca başarıyla bulundu.");
        } else {
            return new ErrorDataResult<List<Hoca>>(hocaList,"Hoca bulunamadı.");
        }
    }

    @Override
    public DataResult<Hoca> getHocaByHocaId(Integer hocaId) {
        Optional<Hoca> hocaList = hocaRepository.findById(hocaId);
        if (hocaList.isPresent()) {
            return new SuccessDataResult<Optional<Hoca>>(hocaList,"Hoca başarıyla bulundu.");
        }
        return new ErrorDataResult<List<Hoca>>("Hoca bulunamadı.");
    }

    @Override
    public Result exportToPdfHoca(HttpServletResponse response) {
        try{
            String fileName = "hoca-list";

            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            response.setHeader("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName+".pdf");

            HocaListPdfHelper hocaListPdfHelper = new HocaListPdfHelper(hocaRepository.findAll());
            hocaListPdfHelper.export(response);
            return new SuccessDataResult(getAllHoca().toString());
        }
        catch (Exception exception){
            return new ErrorResult("Bilinmeyen bir hata oluştu.");
        }
    }
}
