package com.example.gpstrackerserver.util;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * 坐标转换工具
 *
 * @Author makewheels
 * @Time 2021.03.21 22:56:36
 */
public class CoordinateConverter {
    /**
     * nmea原始数据转为gps经纬度
     *
     * @param nmea
     * @return
     */
    public static String[] nmeaToGps(String nmea) {
        String[] array = nmea.split(",");
        String NS = array[1];
        String EW = array[3];
        String nmeaLatitude = array[0];
        String nmeaLongitude = array[2];

        BigDecimal latitude = new BigDecimal(nmeaLatitude.substring(2));
        latitude = latitude.divide(new BigDecimal(60), 16, BigDecimal.ROUND_HALF_UP);
        latitude = latitude.add(new BigDecimal(nmeaLatitude.substring(0, 2)));

        BigDecimal longitude = new BigDecimal(nmeaLongitude.substring(2));
        longitude = longitude.divide(new BigDecimal(60), 16, BigDecimal.ROUND_HALF_UP);
        longitude = longitude.add(new BigDecimal(nmeaLongitude.substring(0, 3)));

        String latitudeString;
        if (NS.equals("S"))
            latitudeString = "-" + latitude.toString();
        else
            latitudeString = latitude.toString();
        String longitudeString;
        if (EW.equals("W"))
            longitudeString = "-" + longitude.toString();
        else
            longitudeString = longitude.toString();

        String[] result = new String[2];
        result[0] = latitudeString;
        result[1] = longitudeString;
        return result;
    }

    public static void main(String[] args) {
        String[] s = nmeaToGps("4635.37934,N,12507.84738,E");
        System.out.println(Arrays.toString(s));
    }
}
