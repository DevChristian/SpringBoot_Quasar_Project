package com.christian.mendez.quasarfireoperation.service

import com.christian.mendez.quasarfireoperation.dto.SatelliteDto
import com.christian.mendez.quasarfireoperation.dto.TopSecretRequestDto
import com.christian.mendez.quasarfireoperation.exception.CircleNotIntersectException
import com.christian.mendez.quasarfireoperation.exception.LocationNotFoundException
import com.christian.mendez.quasarfireoperation.exception.SatelliteNotFoundException

import org.springframework.core.env.Environment
import spock.lang.Specification
import spock.lang.Unroll

class QuasarFireOperationServiceSpec extends Specification{

    QuasarFireOperationServiceImpl service

    String nameSatellite1= "kenobi"
    String nameSatellite2= "skywalker"
    String nameSatellite3= "sato"
    Environment env= Mock()


    def setup() {
        service = new QuasarFireOperationServiceImpl()
        service.nameSatellite1=nameSatellite1
        service.nameSatellite2=nameSatellite2
        service.nameSatellite3=nameSatellite3
        service.env=env
    }

    @Unroll
    def "Obtener posicion y mensaje de la nave enviada a traves de los satelites"() {
        given:"Listado de satelites"
        List<SatelliteDto> satelliteDtoList = Arrays.asList(SatelliteDto.builder().name("kenobi").distance(485.6D).message(Arrays.asList("este","","")).build(),
                SatelliteDto.builder().name("skywalker").distance(266.0D).message(Arrays.asList("","mensaje","")).build(),
                SatelliteDto.builder().name("sato").distance(600.5D).message(Arrays.asList("","","secreto")).build())
        env.getRequiredProperty("satellites.kenobiPosition") >> "-500,-200"
        env.getRequiredProperty("satellites.skywalkerPosition") >> "100,-100"
        env.getRequiredProperty("satellites.satoPosition") >> "500,100"

        when: "se obtienen listado de satellites con distancia y message"
        def retorno = service.obtainPositionAndMessage(TopSecretRequestDto.builder().satellites(satelliteDtoList).build())

        then: "se obtiene posicion y mensaje de la nave"
        retorno
    }

    @Unroll
    def "Obtener posicion y mensaje de la nave enviada a traves de los satelites SatelliteNotFoundException"() {
        given:"Listado de satelites"
        List<SatelliteDto> satelliteDtoList = Arrays.asList(SatelliteDto.builder().name("kenobi").distance(485.6D).message(Arrays.asList("este","","")).build(),
                SatelliteDto.builder().name("skywalker").distance(266.0D).message(Arrays.asList("","mensaje","")).build(),
                SatelliteDto.builder().name("christian").distance(600.5D).message(Arrays.asList("","","secreto")).build())
        env.getRequiredProperty("satellites.kenobiPosition") >> "-500,-200"
        env.getRequiredProperty("satellites.skywalkerPosition") >> "100,-100"
        env.getRequiredProperty("satellites.satoPosition") >> "500,100"

        when: "se obtienen listado de satellites con distancia y message"
        service.obtainPositionAndMessage(TopSecretRequestDto.builder().satellites(satelliteDtoList).build())

        then: "se obtiene SatelliteNotFoundException"
        thrown(SatelliteNotFoundException)
    }

    @Unroll
    def "Obtener posicion y mensaje de la nave enviada a traves de los satelites LocationNotFoundException"() {
        given:"Listado de satelites"
        List<SatelliteDto> satelliteDtoList = Arrays.asList(SatelliteDto.builder().name("kenobi").distance(485.6D).message(Arrays.asList("este","","")).build(),
                SatelliteDto.builder().name("skywalker").distance(266.0D).message(Arrays.asList("","mensaje","")).build(),
                SatelliteDto.builder().name("sato").distance(500.5D).message(Arrays.asList("","","secreto")).build())
        env.getRequiredProperty("satellites.kenobiPosition") >> "-500,-200"
        env.getRequiredProperty("satellites.skywalkerPosition") >> "100,-100"
        env.getRequiredProperty("satellites.satoPosition") >> "500,100"

        when: "se obtienen listado de satellites con distancia y message"
        service.obtainPositionAndMessage(TopSecretRequestDto.builder().satellites(satelliteDtoList).build())

        then: "se obtiene LocationNotFoundException"
        thrown(LocationNotFoundException)
    }

    @Unroll
    def "Obtener posicion y mensaje de la nave enviada a traves de los satelites CircleNotIntersectException"() {
        given:"Listado de satelites"
        List<SatelliteDto> satelliteDtoList = Arrays.asList(SatelliteDto.builder().name("kenobi").distance(485.6D).message(Arrays.asList("este","","")).build(),
                SatelliteDto.builder().name("skywalker").distance(100.0D).message(Arrays.asList("","mensaje","")).build(),
                SatelliteDto.builder().name("sato").distance(500.5D).message(Arrays.asList("","","secreto")).build())
        env.getRequiredProperty("satellites.kenobiPosition") >> "-500,-200"
        env.getRequiredProperty("satellites.skywalkerPosition") >> "100,-100"
        env.getRequiredProperty("satellites.satoPosition") >> "500,100"

        when: "se obtienen listado de satellites con distancia y message"
        service.obtainPositionAndMessage(TopSecretRequestDto.builder().satellites(satelliteDtoList).build())

        then: "se obtiene CircleNotIntersectException"
        thrown(CircleNotIntersectException)
    }

    @Unroll
    def "Obtener posicion y mensaje de la nave enviada a de un satelite get"() {
        given:

        when: "se obtienen satelite con su distancia y mensaje"
        def retorno = service.obtainPositionAndMessageSplit("satellina_name",1D,Arrays.asList(""))

        then: "se obtiene posicion y mensaje de la nave"
        retorno
    }

    @Unroll
    def "Obtener posicion y mensaje de la nave enviada a de un satelite"() {
        given:

        when: "se obtienen satelite con su distancia y mensaje"
        def retorno = service.obtainPositionAndMessageSplit(SatelliteDto.builder().name("satellite_name").distance(1D).message(Arrays.asList("")).build())

        then: "se obtiene posicion y mensaje de la nave"
        retorno
    }
}
