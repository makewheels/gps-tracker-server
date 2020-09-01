package com.example.gpstrackerserver.bean;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * $GNGGA,013341.998,4635.37934,N,12507.84738,E,6,07,4.5,176.5,M,0.0,M,,*75
 * $GNGLL,4635.37934,N,12507.84738,E,013341.998,A,E*40
 * $GNGSA,A,3,28,193,199,,,,,,,,,,8.9,4.5,7.6,1*30
 * $GNGSA,A,3,01,19,29,,,,,,,,,,8.9,4.5,7.6,4*37
 * $GNGSA,A,3,67,,,,,,,,,,,,8.9,4.5,7.6,2*32
 * $GPGSV,1,1,04,28,42,193,41,193,32,152,38,195,,,39,199,36,177,33,0*66
 * $BDGSV,2,1,08,01,33,154,38,02,,,32,04,,,33,07,,,34,0*42
 * $BDGSV,2,2,08,10,,,33,19,47,127,40,29,35,098,33,59,,,33,0*76
 * $GLGSV,1,1,03,78,,,35,70,,,34,67,40,126,39,0*49
 * $GNRMC,013341.998,A,4635.37934,N,12507.84738,E,0.03,0.00,240820,,,E,V*02
 * $GNVTG,0.00,T,,M,0.03,N,0.06,K,E*22
 * $GNZDA,013341.998,24,08,2020,00,00*4A
 * $GPTXT,01,01,01,ANTENNA OK*35
 */

/**
 * 上传到服务器的数据
 */
@Data
@Entity
public class UploadData implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date createTime;

    @Column(length = 2000)
    private String data_ATGM336H;   //GPS数据

    @Column(length = 1000)
    private String data_DHT11;      //温湿度数据

    private String sessionId;
    private String imei;            //SIM800 IMEI
    private String clientIp;        //ip
    private String codeId;          //代码中的硬编码id


}
