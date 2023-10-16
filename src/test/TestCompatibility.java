package test;

import model.IntersectionType;
import model.traffic.TrafficLight;
import model.traffic.VehicleTrafficLight;
import model.traffic.constraint.TrafficCoefficient;
import model.traffic.constraint.constraintMatrix.Matrix;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestCompatibility {
    public static void main(String[] args) {
        Properties prop = new Properties();
        String fileName = "app.config";
        try (FileInputStream fis = new FileInputStream(fileName)) {
            prop.load(fis);

            IntersectionType constraints[][] = new Matrix().getMatrix("udhekryqi-fshmn.csv");
            double[] trafficCoefficients = new TrafficCoefficient().getValuesFromFile("random_numbers.csv");
//            trafficLights = new TrafficLight[16];
            int allowedTimeToRun = Integer.parseInt(prop.get("allowed_time_to_run").toString());
            int time_frame = Integer.parseInt(prop.get("time_frame").toString());
            // TODO: nese e perdorim /time_frame kontrolloje kohen minimale
            int sequenceLength = Integer.parseInt(prop.get("complete_interval").toString()) / time_frame;

            int min_time_green_vehicle  = Integer.parseInt(prop.get("min_time_green_vehicle").toString());
            int max_time_red_vehicle  = Integer.parseInt(prop.get("max_time_red_vehicle").toString());
            int preferred_time_green_vehicle  = Integer.parseInt(prop.get("preferred_time_green_vehicle").toString());
            int preferred_time_red_vehicle  = Integer.parseInt(prop.get("preferred_time_red_vehicle").toString());

            int min_time_green_pedestrian  = Integer.parseInt(prop.get("min_time_green_pedestrian").toString());
            int max_time_red_pedestrian  = Integer.parseInt(prop.get("max_time_red_pedestrian").toString());
            int preferred_time_green_pedestrian  = Integer.parseInt(prop.get("preferred_time_green_pedestrian").toString());
            int preferred_time_red_pedestrian  = Integer.parseInt(prop.get("preferred_time_red_pedestrian").toString());

            int hard = Integer.parseInt(prop.get("hard_constraint_penallty").toString());
            int soft = Integer.parseInt(prop.get("soft_constraint_penallty").toString());
            int cool = Integer.parseInt(prop.get("cool_constraint_penallty").toString());

//            for (int i = 0; i < 12; i++) {
//                trafficLights[i] = new VehicleTrav fficLight(constraints[i], i, false,time_frame,0.1,
//                        min_time_green_vehicle, max_time_red_vehicle, preferred_time_green_vehicle, preferred_time_red_vehicle, trafficCoefficients[i]);
//            }
//            for (int i = 12; i < 16; i++) {
//                trafficLights[i] = new PedestrianTrafficLight(constraints[i], i, false,time_frame,
//                        min_time_green_pedestrian, max_time_red_pedestrian, preferred_time_green_pedestrian, preferred_time_red_pedestrian);
//            }


            boolean[][] seq = {{false, false, true, false, true, false, false, true, false, true, true, true, false, false, false, false}};

            double sum = 0;
            for(int index = 0; index < 12; index++){
                TrafficLight light = new VehicleTrafficLight(constraints[index], index, time_frame, 0.1, min_time_green_vehicle, max_time_red_vehicle, preferred_time_green_vehicle, preferred_time_red_vehicle, trafficCoefficients[index]);
                double score = light.getScore(seq);
                sum += score;
                System.out.println(score);
            }
            System.out.println("\nTotal: " + sum);
        }
        catch (FileNotFoundException ex) {
            System.out.println(ex); }
        catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
