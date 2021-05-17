package com.christian.mendez.quasarfireoperation.exception;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@ApiModel(value = "LocationNotFoundException")
public class LocationNotFoundException extends RuntimeException{

}
