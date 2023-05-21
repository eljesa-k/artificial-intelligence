package test;

import jdk.jfr.Period;

import java.util.Date;
import java.util.GregorianCalendar;

public class TimeChanges {
    public static void main(String[] args) {
        Date start_time = new Date();
        try{
            Thread.sleep(5000);

        }catch (InterruptedException e){

        }

        Date end_time = new Date();
        int diff = (int) (start_time.getTime() - end_time.getTime()) / 1000;
        System.out.println(diff);
    }
}
