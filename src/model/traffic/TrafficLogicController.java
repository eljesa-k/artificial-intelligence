package model.traffic;

import model.IntersectionType;
import model.traffic.constraint.constraintMatrix.Matrix;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TrafficLogicController {
    private TrafficLights[] trafficLights;
    private int sequenceLength;

    public TrafficLogicController() {
        Properties prop = new Properties();
        String fileName = "app.config";
        try (FileInputStream fis = new FileInputStream(fileName)) {
            prop.load(fis);

            IntersectionType constraints[][] = new Matrix().getMatrix("matrix.csv");
            trafficLights = new TrafficLights[16];
            int time_frame = Integer.parseInt(prop.get("time_frame").toString());
            sequenceLength = Integer.parseInt(prop.get("complete_interval").toString()) / time_frame;

            int min_time_green_vehicle  = Integer.parseInt(prop.get("min_time_green_vehicle").toString());
            int max_time_red_vehicle  = Integer.parseInt(prop.get("max_time_red_vehicle").toString());
            int preferred_time_green_vehicle  = Integer.parseInt(prop.get("preferred_time_green_vehicle").toString());
            int preferred_time_red_vehicle  = Integer.parseInt(prop.get("preferred_time_red_vehicle").toString());

            int min_time_green_pedestrian  = Integer.parseInt(prop.get("min_time_green_pedestrian").toString());
            int max_time_red_pedestrian  = Integer.parseInt(prop.get("max_time_red_pedestrian").toString());
            int preferred_time_green_pedestrian  = Integer.parseInt(prop.get("preferred_time_green_pedestrian").toString());
            int preferred_time_red_pedestrian  = Integer.parseInt(prop.get("preferred_time_red_pedestrian").toString());


            for (int i = 0; i < 12; i++) {
                trafficLights[i] = new VehicleTrafficLight(constraints[i], i, false,time_frame,
                                                            min_time_green_vehicle, max_time_red_vehicle, preferred_time_green_vehicle, preferred_time_red_vehicle);
            }
            for (int i = 12; i < 16; i++) {
                trafficLights[i] = new PedestrianTrafficLight(constraints[i], i, false,time_frame,
                        min_time_green_pedestrian, max_time_red_pedestrian, preferred_time_green_pedestrian, preferred_time_red_pedestrian);
            }
        } catch (FileNotFoundException ex) {System.out.println(ex); }
          catch (IOException ex) {}

    }

    public TrafficLights[] getTrafficLights() {
        return trafficLights;
    }

    public int getSequenceLength() {
        return sequenceLength;
    }
}
