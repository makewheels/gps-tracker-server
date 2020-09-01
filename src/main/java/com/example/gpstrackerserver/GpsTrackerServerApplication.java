package com.example.gpstrackerserver;

import com.example.gpstrackerserver.socket.SocketHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GpsTrackerServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GpsTrackerServerApplication.class, args);

        SocketHandler.init();
    }

}
