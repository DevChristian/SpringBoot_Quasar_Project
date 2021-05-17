package com.christian.mendez.quasarfireoperation.service;

import com.christian.mendez.quasarfireoperation.dto.*;
import com.christian.mendez.quasarfireoperation.exception.CircleNotIntersectException;
import com.christian.mendez.quasarfireoperation.exception.LocationNotFoundException;
import com.christian.mendez.quasarfireoperation.exception.SatelliteNotFoundException;
import com.christian.mendez.quasarfireoperation.util.ConstantsEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.christian.mendez.quasarfireoperation.util.Utilitary.modifyDouble;

@Service
@Slf4j
public class QuasarFireOperationServiceImpl implements QuasarFireOperationService{

    @Value("${satellites.nameSatellite1}")
    private String nameSatellite1;

    @Value("${satellites.nameSatellite2}")
    private String nameSatellite2;

    @Value("${satellites.nameSatellite3}")
    private String nameSatellite3;

    @Autowired
    private Environment env;

    @Override
    public TopSecretResponseDto obtainPositionAndMessage(TopSecretRequestDto topSecretRequestDto) {

        log.info("[obtainPositionAndMessage] [START]");

        SatelliteDto firstSatellite = obtainSatellite(topSecretRequestDto.getSatellites(),nameSatellite1);

        SatelliteDto secondSatellite = obtainSatellite(topSecretRequestDto.getSatellites(),nameSatellite2);

        SatelliteDto thirdSatellite = obtainSatellite(topSecretRequestDto.getSatellites(),nameSatellite3);

        PositionDto positionDto = getLocation(firstSatellite,secondSatellite,thirdSatellite);

        String messageFinal = getMessage(firstSatellite,secondSatellite,thirdSatellite);

        log.info("[obtainPositionAndMessage] [FIN]");
        return TopSecretResponseDto.builder().position(positionDto).message(messageFinal).build();

    }

    @Override
    public TopSecretSplitResponseDto obtainPositionAndMessageSplit(SatelliteDto topSecretSplitRequestDto) {
        log.info("[obtainPositionAndMessageSplit] [START]");

        //Obtain position and message
        return TopSecretSplitResponseDto.builder().position(PositionDto.builder().x(Double.NaN).y(Double.NaN).build())
                .message(String.join(" ",topSecretSplitRequestDto.getMessage()).strip()).build();
    }

    @Override
    public TopSecretSplitResponseDto obtainPositionAndMessageSplit(String satellite_name, Double satellite_distance, List<String> satellite_message) {
        log.info("[obtainPositionAndMessageSplit] [START]");

        //Obtain position and message
        return TopSecretSplitResponseDto.builder().position(PositionDto.builder().x(Double.NaN).y(Double.NaN).build())
                .message(String.join(" ", satellite_message).strip()).build();
    }

    private String getMessage(SatelliteDto firstSatellite, SatelliteDto secondSatellite, SatelliteDto thirdSatellite){
        log.info("[getMessage] obtain message of all satellite");
        List<String> messageFinal = new ArrayList<>();

        List<Integer> sizes = Arrays.asList(firstSatellite.getMessage().size(),secondSatellite.getMessage().size(),thirdSatellite.getMessage().size());

        int max = sizes
                .stream()
                .mapToInt(s -> s)
                .max().orElseThrow(NoSuchElementException::new);

        for(int i = 0;i < max; i++) {

            String messageSattelite1 = validateSizeMessage(firstSatellite, i);
            String messageSattelite2 = validateSizeMessage(secondSatellite, i);
            String messageSattelite3 = validateSizeMessage(thirdSatellite, i);

            if (i == 0)
                messageFinal.add(insertValueString(messageSattelite1, messageSattelite2, messageSattelite3));
            else if (!messageFinal.get(i - 1).equalsIgnoreCase(insertValueString(messageSattelite1, messageSattelite2, messageSattelite3)))
                messageFinal.add(insertValueString(messageSattelite1, messageSattelite2, messageSattelite3));
            else
                messageFinal.add("");
        }

        return messageFinal.stream().map(Object::toString).collect(Collectors.joining("")).strip();
    }

    private PositionDto getLocation(SatelliteDto firstSatellite, SatelliteDto secondSatellite, SatelliteDto thirdSatellite){

            log.info("[getLocation] obtain location of ship");

            PositionDto positionDto= PositionDto.builder().build();
            double distancePoints, distanceX, distanceY, distanceCenters, distancePoints2, rx, ry;
            double point2_x, point2_y;

            /* distanceX and distanceY are the vertical and horizontal distances between
             * the circle centers.
             */
            distanceX = secondSatellite.getX() - firstSatellite.getX();
            distanceY = secondSatellite.getY() - firstSatellite.getY();

            /* Determine the straight-line distance between the centers. */
            distanceCenters = Math.sqrt((distanceY*distanceY) + (distanceX*distanceX));

            /* Check for solvability. */
            if (distanceCenters > (firstSatellite.getDistance() + secondSatellite.getDistance()))
            {
                /* no solution. circles do not intersect. */
                throw new CircleNotIntersectException();
            }
            if (distanceCenters < Math.abs(firstSatellite.getDistance() - secondSatellite.getDistance()))
            {
                /* no solution. one circle is contained in the other */
                throw new CircleNotIntersectException();
            }

            /* 'point 2' is the point where the line through the circle
             * intersection points crosses the line between the circle
             * centers.
             */

            /* Determine the distance from point 0 to point 2. */
            distancePoints = ((firstSatellite.getDistance()*firstSatellite.getDistance()) - (secondSatellite.getDistance()*secondSatellite.getDistance()) + (distanceCenters*distanceCenters)) / (2.0 * distanceCenters) ;

            /* Determine the coordinates of point 2. */
            point2_x = firstSatellite.getX() + (distanceX * distancePoints/distanceCenters);
            point2_y = firstSatellite.getY() + (distanceY * distancePoints/distanceCenters);

            /* Determine the distance from point 2 to either of the
             * intersection points.
             */
            distancePoints2 = Math.sqrt((firstSatellite.getDistance()*firstSatellite.getDistance()) - (distancePoints*distancePoints));

            /* Now determine the offsets of the intersection points from
             * point 2.
             */
            rx = -distanceY * (distancePoints2/distanceCenters);
            ry = distanceX * (distancePoints2/distanceCenters);

            /* Determine the absolute intersection points. */
            double intersectionPoint1_x = point2_x + rx;
            double intersectionPoint2_x = point2_x - rx;
            double intersectionPoint1_y = point2_y + ry;
            double intersectionPoint2_y = point2_y - ry;

            log.info("Intesections of sattelite1 and satellite2:(" + (String.format("%.1f",intersectionPoint1_x) + "," + (String.format("%.1f",intersectionPoint1_y) + ")" + " and (" + (String.format("%.1f",intersectionPoint2_x) + "," + (String.format("%.1f",intersectionPoint2_y) + ")")))));

            /* Lets determine if circle 3 intersects at either of the above intersection points. */
            distanceX = intersectionPoint1_x - thirdSatellite.getX();
            distanceY = intersectionPoint1_y - thirdSatellite.getY();

            double d1 = Math.sqrt((distanceY*distanceY) + (distanceX*distanceX));

            distanceX = intersectionPoint2_x - thirdSatellite.getX();
            distanceY = intersectionPoint2_y - thirdSatellite.getY();
            double d2 = Math.sqrt((distanceY*distanceY) + (distanceX*distanceX));

            if(Math.abs(modifyDouble(d1) - thirdSatellite.getDistance()) < Double.parseDouble(ConstantsEnum.EPSILON.toString())) {
                positionDto.setX(modifyDouble(intersectionPoint1_x));
                positionDto.setY(modifyDouble(intersectionPoint1_y));
                log.info("Intersection of all satellites :(" + modifyDouble(intersectionPoint1_x) + "," + modifyDouble(intersectionPoint1_y) + ")");
            }
            else if(Math.abs(modifyDouble(d2) - thirdSatellite.getDistance()) < Double.parseDouble(ConstantsEnum.EPSILON.toString())) {
                positionDto.setX(modifyDouble(intersectionPoint2_x));
                positionDto.setY(modifyDouble(intersectionPoint2_y));
                log.info("Intersection of all satellites :(" + modifyDouble(intersectionPoint2_x) + "," + modifyDouble(intersectionPoint2_y) + ")");
            }
            else {
                throw new LocationNotFoundException();
            }

            return positionDto;
    }

    private SatelliteDto obtainSatellite(List<SatelliteDto> satelliteDtoList, String nombreSatelite){

        log.info("[obtainSatellite] obtain satellite [{}]", nombreSatelite);

        return satelliteDtoList.stream().filter(
                satelliteDto -> satelliteDto.getName().equalsIgnoreCase(nombreSatelite)
                ).peek(satellite -> {satellite.setX(Double.parseDouble(env.getRequiredProperty("satellites." + nombreSatelite + "Position").split(",")[0]));
                                                    satellite.setY(Double.parseDouble(env.getRequiredProperty("satellites." + nombreSatelite + "Position").split(",")[1]));
                                                        satellite.setDistance(modifyDouble(satellite.getDistance()));})
                .findFirst()
                .orElseThrow(SatelliteNotFoundException::new);

    }

    private String insertValueString(String messageSattelite1, String messageSattelite2, String messageSattelite3){
        return messageSattelite1.trim().length() > 0 ? messageSattelite1 + " " :
                messageSattelite2.trim().length() > 0 ? messageSattelite2 + " " :
                        messageSattelite3.trim().length() > 0 ? messageSattelite3 + " " : "";
    }

    private String validateSizeMessage(SatelliteDto satelliteDto, int position){
        return satelliteDto.getMessage().size() > position ? satelliteDto.getMessage().get(position) : "" ;
    }

}
