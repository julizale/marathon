package com.marathon.controller;

import com.marathon.service.PostalCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.ValidationException;

@RestController
@RequestMapping("/v1/postal")
public class PostalController {

    @Autowired
    private PostalCodeService postalCodeService;

    @GetMapping(value = "{postalCode}")
    public ResponseEntity<String> getCity(@PathVariable String postalCode) throws ValidationException {
        String city = postalCodeService.getCity(postalCode);
        return ResponseEntity.ok(city);
    }
}
