package com.marathon.controller;

import com.marathon.domain.dto.PerformanceDto;
import com.marathon.exception.NotValidCodeException;
import com.marathon.exception.PerformanceNotFoundException;
import com.marathon.service.PostalCodeService;
import com.marathon.validator.PostalCodeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/postal")
public class PostalController {

    @Autowired
    private PostalCodeService postalCodeService;

    @GetMapping(value = "{postalCode}")
    public ResponseEntity<String> getCity(@PathVariable String postalCode) throws NotValidCodeException {
        String city = postalCodeService.getCity(postalCode);
        return ResponseEntity.ok(city);
    }
}
