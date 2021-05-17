package com.christian.mendez.quasarfireoperation.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@ApiModel(value = "TopSecretSplitResponseDto")
public class TopSecretSplitResponseDto {

    @ApiModelProperty(value = "Posicion de la nave",
            dataType = "PositionDto",
            required = true)
    private PositionDto position;

    @ApiModelProperty(value = "Mensaje de la nave descifrada",
            dataType = "String",
            required = true)
    private String message;
}
