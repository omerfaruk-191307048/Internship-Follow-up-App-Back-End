package YazGel1.StajTakip.business.concretes;

import YazGel1.StajTakip.business.abstracts.NottService;
import YazGel1.StajTakip.core.utilites.pdfHelper.NottListPdfHelper;
import YazGel1.StajTakip.core.utilites.results.DataResult;
import YazGel1.StajTakip.core.utilites.results.ErrorResult;
import YazGel1.StajTakip.core.utilites.results.Result;
import YazGel1.StajTakip.core.utilites.results.SuccessDataResult;
import YazGel1.StajTakip.dataAccess.abstracts.NottRepository;
import YazGel1.StajTakip.entities.concretes.Nott;
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
public class NottManager implements NottService {
    @Autowired
    private NottRepository nottRepository;

    @Override
    public DataResult addNott(Nott nott) {
        nottRepository.save(nott);
        return new SuccessDataResult("Not başarıyla eklendi.");
    }

    @Override
    public DataResult<List<Nott>> getAllNott() {
        return new SuccessDataResult<Iterable<Nott>>(nottRepository.findAll(),"Not başarıyla listelendi.");
    }

    @Override
    public DataResult updateNott(Nott nott) {
        Nott nottDB = nottRepository.findById(nott.getNottId()).get();
        if (Objects.nonNull(nott.getNottAdi()) && !"".equalsIgnoreCase(nott.getNottAdi())){
            nottDB.setNottAdi(nott.getNottAdi());
        }
        nottRepository.save(nottDB);
        return new SuccessDataResult<Nott>("Not başarıyla güncellendi.");
    }

    @Override
    public DataResult<Nott> deleteNottById(Integer nottId) {
        nottRepository.deleteById(nottId);
        return new SuccessDataResult<Nott>("Not başarıyla silindi.");
    }

    @Override
    public Result exportToPdfNott(HttpServletResponse response) {
        try{
            String fileName = "not-list";

            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            response.setHeader("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName+".pdf");

            NottListPdfHelper nottListPdfHelper = new NottListPdfHelper(nottRepository.findAll());
            nottListPdfHelper.export(response);
            return new SuccessDataResult(getAllNott().toString());
        }
        catch (Exception exception){
            return new ErrorResult("Bilinmeyen bir hata oluştu.");
        }
    }
}
