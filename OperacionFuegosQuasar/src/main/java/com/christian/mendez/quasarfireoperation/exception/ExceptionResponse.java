package com.christian.mendez.quasarfireoperation.exception;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ExceptionResponse")
public class ExceptionResponse {

    @ApiModelProperty(value = "Mensaje de la excepcion",
            dataType = "String",
            required = true)
    private String mensaje;
}
