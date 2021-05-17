package com.christian.mendez.quasarfireoperation.controller

import com.christian.mendez.quasarfireoperation.dto.SatelliteDto
import com.christian.mendez.quasarfireoperation.dto.TopSecretRequestDto
import com.christian.mendez.quasarfireoperation.dto.TopSecretResponseDto
import com.christian.mendez.quasarfireoperation.dto.TopSecretSplitResponseDto
import com.christian.mendez.quasarfireoperation.service.QuasarFireOperationService

import spock.lang.Specification
import spock.lang.Unroll

class QuasarFireOperationControllerSpec extends Specification {

    QuasarFireOperationController controller

    QuasarFireOperationService quasarFireOperationService = Mock()

    def setup() {
        controller = new QuasarFireOperationController()
        controller.quasarFireOperationService = quasarFireOperationService
    }

    @Unroll
    def "Obtener posicion y mensaje de la nave enviada a traves de los satelites"() {
        given: "el request de los satelite"
        quasarFireOperationService.obtainPositionAndMessage(_ as TopSecretRequestDto) >> TopSecretResponseDto.builder().build()

        when: "se obtienen listado de satellites con distancia y message"
        def retorno = controller.obtainPositionAndMessageShip(TopSecretRequestDto.builder().build())

        then: "se obtiene posicion y mensaje de la nave"
        retorno
    }

    @Unroll
    def "Obtener posicion y mensaje de la nave enviada a traves de un satelite"() {
        given: "el request del satelite "
        quasarFireOperationService.obtainPositionAndMessageSplit(_ as SatelliteDto) >> TopSecretSplitResponseDto.builder().build()

        when: "se obtienen satelite con distancia y message"
        def retorno = controller.obtainPositionAndMessageShip(SatelliteDto.builder().build())

        then: "se obtiene posicion y mensaje de la nave"
        retorno
    }

    @Unroll
    def "Obtener posicion y mensaje de la nave enviada a traves de un satelite get method"() {
        given: "el request del satelite "
        quasarFireOperationService.obtainPositionAndMessageSplit(_ as String, _ as Double, _ as List<String>) >> TopSecretSplitResponseDto.builder().build()

        when: "se obtienen satelite con distancia y message"
        def retorno = controller.obtainPositionAndMessageShipGet(new String("Satelite_name"),
                 new Double(1D), Arrays.asList("mensaje"))

        then: "se obtiene posicion y mensaje de la nave"
        retorno
    }
}