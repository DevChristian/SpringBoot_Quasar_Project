package com.christian.mendez.quasarfireoperation.controller;


import com.christian.mendez.quasarfireoperation.dto.SatelliteDto;
import com.christian.mendez.quasarfireoperation.dto.TopSecretRequestDto;
import com.christian.mendez.quasarfireoperation.dto.TopSecretResponseDto;
import com.christian.mendez.quasarfireoperation.dto.TopSecretSplitResponseDto;
import com.christian.mendez.quasarfireoperation.service.QuasarFireOperationService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author Christian Mendez
 * <p>
 *     <b>Este proyecto fue realizado por christian</b>
 * </p>
 **/
@Slf4j
@RestController
public class QuasarFireOperationController {

    @Autowired
    private QuasarFireOperationService quasarFireOperationService;

    @ApiOperation(
            value = "Obtiene mensaje y posicion de la nave"
    )
    @PostMapping(
            value = "/topsecret/",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<TopSecretResponseDto> obtainPositionAndMessageShip(@Valid @RequestBody TopSecretRequestDto topSecretRequestDto){

        return ResponseEntity.ok(quasarFireOperationService.obtainPositionAndMessage(topSecretRequestDto));
    }

    @ApiOperation(
            value = "Obtiene mensaje y posicion de la nave split"
    )
    @PostMapping(
            value = "/topsecret_split/",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<TopSecretSplitResponseDto> obtainPositionAndMessageShip(@Valid @RequestBody SatelliteDto satelliteDto){

        return ResponseEntity.ok(quasarFireOperationService.obtainPositionAndMessageSplit(satelliteDto));
    }

    @ApiOperation(
            value = "Obtiene mensaje y posicion de la nave split"
    )
    @GetMapping(
            value = "/topsecret_split/{satellite_name}/{satellite_distance}/{satellite_message}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<TopSecretSplitResponseDto> obtainPositionAndMessageShipGet(@PathVariable String satellite_name,
            @PathVariable Double satellite_distance, @PathVariable List<String> satellite_message){

        return ResponseEntity.ok(quasarFireOperationService.obtainPositionAndMessageSplit(satellite_name,satellite_distance,satellite_message));
    }
}
