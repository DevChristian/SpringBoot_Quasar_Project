package com.christian.mendez.quasarfireoperation.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Christian Mendez
 * <p>
 *     <b>Este proyecto fue realizado por christian</b>
 * </p>
 **/

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "JwtResponseDto")
public class JwtResponseDto implements Serializable {

    @ApiModelProperty(value = "Token necesario para enviar el mensaje desde la nave",
            dataType = "String",
            required = true)
    private String jwtToken;
}
