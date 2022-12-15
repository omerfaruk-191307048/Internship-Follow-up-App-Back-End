package YazGel1.StajTakip.api.controllers;

import YazGel1.StajTakip.business.abstracts.OgrenciService;
import YazGel1.StajTakip.core.utilites.results.DataResult;
import YazGel1.StajTakip.core.utilites.results.ErrorDataResult;
import YazGel1.StajTakip.entities.concretes.Ogrenci;
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
@RequestMapping(name = "/api/OgrenciController")
public class OgrenciController {
    @Autowired
    private OgrenciService ogrenciService;

    @PostMapping(value = "/addOgrenci")
    public ResponseEntity<?> addOgrenci(@RequestBody Ogrenci ogrenci){
        return ResponseEntity.ok(ogrenciService.addOgrenci(ogrenci));
    }

    @GetMapping("/ogrenci/getAllOgrenci")
    public DataResult<List<Ogrenci>> getAllOgrenci(){
        return ogrenciService.getAllOgrenci();
    }

    @PutMapping("/ogrenci/updateOgrenci")
    public DataResult<Ogrenci> updateOgrenci(@RequestBody Ogrenci ogrenci){
        return ogrenciService.updateOgrenci(ogrenci);
    }

    @DeleteMapping("/ogrenci/deleteOgrenciByNo")
    public DataResult<Ogrenci> deleteOgrenciByNo(@RequestParam Integer ogrNo){
        return ogrenciService.deleteOgrenciByNo(ogrNo);
    }

    @GetMapping("/ogrenci/getAllOgrenciSorted")
    public DataResult<List<Ogrenci>> getAllOgrenciSorted(){
        return ogrenciService.getAllOgrenciSorted();
    }

    @GetMapping("/ogrenci/getOgrenciByOgrAdContains")
    public DataResult<List<Ogrenci>> getOgrenciByOgrAdContains(String ogrAd){
        return ogrenciService.getOgrenciByOgrAdContains(ogrAd);
    }

    @GetMapping("/ogrenci/getOgrenciByOgrNo")
    public DataResult<Ogrenci> getOgrenciByOgrNo(Integer ogrNo){
        return ogrenciService.getOgrenciByOgrNo(ogrNo);
    }

    @GetMapping("/ogrenci/exportToPdfOgrenci")
    public ResponseEntity<?> exportToPdfOgrenci(HttpServletResponse response) throws IOException{
        return ResponseEntity.ok(ogrenciService.exportToPdfOgrenci(response));
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
