package com.christian.mendez.quasarfireoperation.controller;

import com.christian.mendez.quasarfireoperation.dto.JwtResponseDto;
import com.christian.mendez.quasarfireoperation.service.JwtGenerateService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Christian Mendez
 * <p>
 *     <b>Este proyecto fue realizado por christian</b>
 * </p>
 **/
@Slf4j
@RestController
public class AuthController {

    @Autowired
    private JwtGenerateService jwtGenerateService;

    @ApiOperation(
            value = "Obtiene mensaje y posicion de la nave"
    )
    @PostMapping(
            value = "/authenticate",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<JwtResponseDto> createAuthenticationToken(@RequestParam Map<String, String> body) {

        return ResponseEntity.ok(jwtGenerateService.getJWTToken(body));

    }
}
