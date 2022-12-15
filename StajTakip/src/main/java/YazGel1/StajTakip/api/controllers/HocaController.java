package YazGel1.StajTakip.api.controllers;

import YazGel1.StajTakip.business.abstracts.HocaService;
import YazGel1.StajTakip.core.utilites.results.DataResult;
import YazGel1.StajTakip.core.utilites.results.ErrorDataResult;
import YazGel1.StajTakip.entities.concretes.Hoca;
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
@RequestMapping(name = "/hoca/HocaController")
public class HocaController {
    @Autowired
    private HocaService hocaService;

    @PostMapping(value = "/addHoca")
    public ResponseEntity<?> addHoca(@RequestBody Hoca hoca){
        return ResponseEntity.ok(hocaService.addHoca(hoca));
    }

    @GetMapping("/hoca/getAllHoca")
    public DataResult<List<Hoca>> getAllHoca(){
        return hocaService.getAllHoca();
    }

    @PutMapping("/hoca/updateHoca")
    public DataResult updateHoca(@RequestBody Hoca hoca){
        return hocaService.updateHoca(hoca);
    }

    @DeleteMapping("/hoca/deleteHocaById")
    public DataResult<Hoca> deleteHocaById(@RequestParam Integer hocaId){
        return hocaService.deleteHocaById(hocaId);
    }

    @GetMapping("/hoca/getAllHocaSorted")
    public DataResult<List<Hoca>> getAllHocaSorted(){
        return hocaService.getAllHocaSorted();
    }

    @GetMapping("/hoca/getHocaByHocaAdContains")
    public DataResult<List<Hoca>> getHocaByHocaAdContains(String hocaAd){
        return hocaService.getHocaByHocaAdContains(hocaAd);
    }

    @GetMapping("/hoca/getHocaByHocaId")
    public DataResult<Hoca> getHocaByHocaId(Integer hocaId){
        return hocaService.getHocaByHocaId(hocaId);
    }

    @GetMapping("/hoca/exportToPdfHoca")
    public ResponseEntity<?> exportToPdfHoca(HttpServletResponse response) throws IOException{
        return ResponseEntity.ok(hocaService.exportToPdfHoca(response));
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
