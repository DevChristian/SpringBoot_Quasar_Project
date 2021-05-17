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
@ApiModel(value = "PositionDto")
public class PositionDto {

    @ApiModelProperty(value = "Posicion X donde se ubica la nave",
            dataType = "Double",
            required = true)
    private Double x;

    @ApiModelProperty(value = "Posicion Y donde se ubica la nave",
            dataType = "Double",
            required = true)
    private Double y;
}
