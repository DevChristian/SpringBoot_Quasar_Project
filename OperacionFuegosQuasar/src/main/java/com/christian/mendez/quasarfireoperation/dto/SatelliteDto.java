package com.christian.mendez.quasarfireoperation.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

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
@ApiModel(value = "SatelliteRequestDto")
public class SatelliteDto {

    @NotBlank(message = "Name is mandatory")
    @ApiModelProperty(value = "Nombre del satelite",
            dataType = "String",
            required = true)
    private String name;

    @NotNull(message = "Distance is mandatory")
    @ApiModelProperty(value = "Distancia del satelite con respecto a la nave",
            dataType = "Double",
            required = true)
    private Double distance;

    @NotEmpty(message = "Message is mandatory")
    @ApiModelProperty(value = "Mensaje obtenido de la nave",
            dataType = "List<String>",
            required = true)
    private List<String> message;

    @ApiModelProperty(value = "Posicion X donde se ubica el satelite",
            dataType = "Double",
            required = true)
    private Double x;

    @ApiModelProperty(value = "Posicion Y donde se ubica el satelite",
            dataType = "Double",
            required = true)
    private Double y;

}
