package com.herokuapp.convert.gregtohebrew.rest;

import com.herokuapp.convert.gregtohebrew.model.HebrewDate;
import com.herokuapp.convert.gregtohebrew.service.ConvertService;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor(onConstructor = @_(@Autowired))
public class GregToHebrewController {
    private ConvertService convertService;

    @PostMapping(value = "{gy}/{gm}/{gd}")
    public ResponseEntity<HebrewDate> convertGregToHebrew(
            @PathVariable int gy, @PathVariable int gm,
            @PathVariable int gd) throws UnirestException {
        return ResponseEntity.ok(
                new HebrewDate(convertService.gregToHebrew(gy, gm, gd)));
    }
}
