package com.christian.mendez.quasarfireoperation.util;

public enum ConstantsEnum {

    SATELLITE_NOT_FOUND("Satellite not found"), LOCATION_NOT_FOUND("Location not found"),
    CIRCLE_NOT_INTERSECT("Satellites not intersect, location of ship not found"), EPSILON("0.000001");

    private String value;

    ConstantsEnum(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
