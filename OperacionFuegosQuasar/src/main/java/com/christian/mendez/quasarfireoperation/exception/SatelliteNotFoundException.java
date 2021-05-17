package com.christian.mendez.quasarfireoperation.exception;

import io.swagger.annotations.ApiModel;
import lombok.*;

@Builder
@NoArgsConstructor
@ApiModel(value = "SatelliteNotFoundException")
public class SatelliteNotFoundException extends RuntimeException{

}
