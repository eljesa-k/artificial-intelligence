package model.traffic;

import model.IntersectionType;
import model.traffic.constraint.TrafficCoefficient;
import model.traffic.constraint.constraintMatrix.Matrix;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TrafficLogicController {
    private static TrafficLight[] trafficLights;
    private static int sequenceLength;
    private static int hard;
    private static int soft;
    private static int cool;
    private static int allowedTimeToRun;

    /**
     * Reads all the config and constraints and creates traffic light instances
     * Saves all the config data as static variables
     * Initialized using Singleton Design Pattern
     */
    private TrafficLogicController() {
        // Source for reading config file
        // https://stackoverflow.com/questions/16273174/how-to-read-a-configuration-file-in-java

        Properties prop = new Properties();
        String fileName = "app.config";
        try (FileInputStream fis = new FileInputStream(fileName)) {
            prop.load(fis);

            IntersectionType constraints[][] = new Matrix().getMatrix("udhekryqi-fshmn.csv");
            double[] trafficCoefficients = new TrafficCoefficient().getValuesFromFile("random_numbers.csv");
            trafficLights = new TrafficLight[16];
            allowedTimeToRun = Integer.parseInt(prop.get("allowed_time_to_run").toString());
            int time_frame = Integer.parseInt(prop.get("time_frame").toString());
            // TODO: nese e perdorim /time_frame kontrolloje kohen minimale
            sequenceLength = Integer.parseInt(prop.get("complete_interval").toString()) / time_frame;

            int min_time_green_vehicle  = Integer.parseInt(prop.get("min_time_green_vehicle").toString());
            int max_time_red_vehicle  = Integer.parseInt(prop.get("max_time_red_vehicle").toString());
            int preferred_time_green_vehicle  = Integer.parseInt(prop.get("preferred_time_green_vehicle").toString());
            int preferred_time_red_vehicle  = Integer.parseInt(prop.get("preferred_time_red_vehicle").toString());

            int min_time_green_pedestrian  = Integer.parseInt(prop.get("min_time_green_pedestrian").toString());
            int max_time_red_pedestrian  = Integer.parseInt(prop.get("max_time_red_pedestrian").toString());
            int preferred_time_green_pedestrian  = Integer.parseInt(prop.get("preferred_time_green_pedestrian").toString());
            int preferred_time_red_pedestrian  = Integer.parseInt(prop.get("preferred_time_red_pedestrian").toString());

            hard = Integer.parseInt(prop.get("hard_constraint_penallty").toString());
            soft = Integer.parseInt(prop.get("soft_constraint_penallty").toString());
            cool = Integer.parseInt(prop.get("cool_constraint_penallty").toString());

            for (int i = 0; i < 12; i++) {
                trafficLights[i] = new VehicleTrafficLight(constraints[i], i,time_frame,0.1,
                                                            min_time_green_vehicle, max_time_red_vehicle, preferred_time_green_vehicle, preferred_time_red_vehicle, trafficCoefficients[i]);
            }
            for (int i = 12; i < 16; i++) {
                trafficLights[i] = new PedestrianTrafficLight(constraints[i], i,time_frame,
                        min_time_green_pedestrian, max_time_red_pedestrian, preferred_time_green_pedestrian, preferred_time_red_pedestrian);
            }
        }
        catch (FileNotFoundException ex) {
            System.out.println(ex); }
        catch (IOException ex) {
              System.out.println(ex);
        }

    }

    public static TrafficLight[] getTrafficLights() {
        if(trafficLights == null)
            new TrafficLogicController();
        return trafficLights;
    }

    public static int getSequenceLength() {
        if(trafficLights == null)
            new TrafficLogicController();
        return sequenceLength;
    }

    public static int getHard() {
        if(trafficLights == null)
            new TrafficLogicController();
        return hard;
    }

    public static int getSoft() {
        if(trafficLights == null)
            new TrafficLogicController();
        return soft;
    }

    public static int getCool() {
        if(trafficLights == null)
            new TrafficLogicController();
        return cool;
    }

    public static int getAllowedTimeToRun() {
        if(trafficLights == null)
            new TrafficLogicController();
        return allowedTimeToRun;
    }
}
