package YazGel1.StajTakip.api.controllers;

import YazGel1.StajTakip.business.abstracts.DurumService;
import YazGel1.StajTakip.core.utilites.results.DataResult;
import YazGel1.StajTakip.core.utilites.results.ErrorDataResult;
import YazGel1.StajTakip.core.utilites.results.Result;
import YazGel1.StajTakip.entities.concretes.Durum;
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
@RequestMapping(name = "/api/DurumController")
public class DurumController {
    @Autowired
    private DurumService durumService;

    @PostMapping(value = "/addDurum")
    public ResponseEntity<?> addDurum(@RequestBody Durum durum){
        return ResponseEntity.ok(durumService.addDurum(durum));
    }

    @GetMapping("/durum/getAllDurum")
    public DataResult<List<Durum>> getAllDurum(){
        return durumService.getAllDurum();
    }

    @PutMapping("/durum/updateDurum")
    public DataResult updateDurum(@RequestBody Durum durum){
        return durumService.updateDurum(durum);
    }

    @DeleteMapping("/durum/deleteDurumById")
    public DataResult<Durum> deleteDurumById(@RequestParam Integer durumId){
        return durumService.deleteDurumById(durumId);
    }

    @GetMapping("/durum/PDF")
    public ResponseEntity<?> exportToPdfDurum(HttpServletResponse response) throws IOException{
        return ResponseEntity.ok(durumService.exportToPdfDurum(response));
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
