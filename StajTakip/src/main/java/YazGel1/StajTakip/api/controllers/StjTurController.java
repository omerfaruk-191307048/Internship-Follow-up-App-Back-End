package YazGel1.StajTakip.api.controllers;

import YazGel1.StajTakip.business.abstracts.StjTurService;
import YazGel1.StajTakip.core.utilites.results.DataResult;
import YazGel1.StajTakip.core.utilites.results.ErrorDataResult;
import YazGel1.StajTakip.entities.concretes.StjTur;
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
@RequestMapping(name = "/api/StjTurController")
public class StjTurController {
    @Autowired
    private StjTurService stjTurService;

    @PostMapping(value = "/addStjTur")
    public ResponseEntity<?> addStjTur(@RequestBody StjTur stjTur){
        return ResponseEntity.ok(stjTurService.addStjTur(stjTur));
    }

    @GetMapping("/stjTur/getAllStjTur")
    public DataResult<List<StjTur>> getAllStjTur(){
        return stjTurService.getAllStjTur();
    }

    @PutMapping("/stjTur/updateStjTur")
    public DataResult updateStjTur(@RequestBody StjTur stjTur){
        return stjTurService.updateStjTur(stjTur);
    }

    @DeleteMapping("/stjTur/deleteStjTurById")
    public DataResult<StjTur> deleteStjTurById(@RequestParam Integer stjTurId){
        return stjTurService.deleteStjTurById(stjTurId);
    }

    @GetMapping("/stjTur/exportToPdfStjTur")
    public ResponseEntity<?> exportToPdfStjTur(HttpServletResponse response) throws IOException{
        return ResponseEntity.ok(stjTurService.exportToPdfStjTur(response));
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
