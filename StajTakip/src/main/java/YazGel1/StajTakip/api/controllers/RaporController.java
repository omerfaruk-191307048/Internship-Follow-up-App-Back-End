package YazGel1.StajTakip.api.controllers;

import YazGel1.StajTakip.business.abstracts.RaporService;
import YazGel1.StajTakip.core.utilites.results.DataResult;
import YazGel1.StajTakip.core.utilites.results.ErrorDataResult;
import YazGel1.StajTakip.entities.concretes.Rapor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(name = "/api/RaporController")
public class RaporController {
    @Autowired
    private RaporService raporService;

    @PostMapping(value = "/addRapor")
    public ResponseEntity<?> addRapor(@RequestBody Rapor rapor){
        return ResponseEntity.ok(raporService.addRapor(rapor));
    }

    @GetMapping("/rapor/getAllRapor")
    public DataResult<List<Rapor>> getAllRapor(){
        return raporService.getAllRapor();
    }

    @PutMapping("/rapor/updateRapor")
    public DataResult updateRapor(@RequestBody Rapor rapor){
        return raporService.updateRapor(rapor);
    }

    @DeleteMapping("/rapor/deleteRaporById")
    public DataResult<Rapor> deleteRaporById(@RequestParam Integer raporId){
        return raporService.deleteRaporById(raporId);
    }

    @GetMapping("/rapor/getRaporByRaporId")
    public DataResult<Rapor> getRaporByRaporId(Integer raporId){
        return raporService.getRaporByRaporId(raporId);
    }

    @GetMapping("/rapor/exportToPdfRapor")
    public ResponseEntity<?> exportToPdfRapor(HttpServletResponse response) throws IOException{
        return ResponseEntity.ok(raporService.exportToPdfRapor(response));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) //spring validation'dan gelen butun dogrulama hatalarini listeler.
    @ResponseStatus(HttpStatus.BAD_REQUEST) //Bu metot default olarak bad request(500 hatasi) donsun.
    public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions){ //Exception olusursa bu metodu cagir demek. O hatalari exceptions'a parametre olarak gec.
        Map<String, String> validationErrors = new HashMap<String, String>(); //Iki alani verecegimiz icin map yapisini kullandik. Anahtar(alan), hata.
        for(FieldError fieldError : exceptions.getBindingResult().getFieldErrors()){
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage()); //HashMap'i put olarak ekleriz.
        }
        ErrorDataResult<Object> errors = new ErrorDataResult<Object>(validationErrors, "Doğrulama hataları.");
        return errors;
    }
}
