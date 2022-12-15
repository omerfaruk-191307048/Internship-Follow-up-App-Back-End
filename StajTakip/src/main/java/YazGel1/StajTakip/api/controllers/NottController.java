package YazGel1.StajTakip.api.controllers;

import YazGel1.StajTakip.business.abstracts.NottService;
import YazGel1.StajTakip.core.utilites.results.DataResult;
import YazGel1.StajTakip.core.utilites.results.ErrorDataResult;
import YazGel1.StajTakip.entities.concretes.Nott;
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
@RequestMapping(name = "/api/NottController")
public class NottController {
    @Autowired
    private NottService nottService;

    @PostMapping(value = "/addNott")
    public ResponseEntity<?> addNott(@RequestBody Nott nott){
        return ResponseEntity.ok(nottService.addNott(nott));
    }

    @GetMapping("/nott/getAllNott")
    public DataResult<List<Nott>> getAllNott(){
        return nottService.getAllNott();
    }

    @PutMapping("/nott/updateNott")
    public DataResult updateNott(@RequestBody Nott nott){
        return nottService.updateNott(nott);
    }

    @DeleteMapping("/nott/deleteNottById")
    public DataResult<Nott> deleteNottById(Integer nottId){
        return nottService.deleteNottById(nottId);
    }

    @GetMapping("/nott/exportToPdfNott")
    public ResponseEntity<?> exportToPdfNott(HttpServletResponse response) throws IOException{
        return ResponseEntity.ok(nottService.exportToPdfNott(response));
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
