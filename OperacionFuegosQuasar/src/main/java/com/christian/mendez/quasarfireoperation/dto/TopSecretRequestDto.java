package com.christian.mendez.quasarfireoperation.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
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
@ApiModel(value = "TopSecretRequestDto")
public class TopSecretRequestDto {

    @NotEmpty(message = "Name is mandatory")
    @ApiModelProperty(value = "Listado de satelites",
            dataType = "List<SatelliteDto>",
            required = true)
    private List<SatelliteDto> satellites;
}
