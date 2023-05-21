package model.traffic.constraint;

import java.util.HashMap;
import java.util.Map;

/**
 * singleton
 * ky e ka veq ni map se i ka trujtene veq ka ni value per key
 */
public class ConstrainConstantDistributor {

    private static Map<ConstrainType, Integer> constrainTypeMap;

    private ConstrainConstantDistributor(){
        constrainTypeMap = new HashMap<>();

        constrainTypeMap.put(ConstrainType.MIN_TIME_GREEN, 2);
        constrainTypeMap.put(ConstrainType.PREF_MIN_TIME_GREEN, 3);
        constrainTypeMap.put(ConstrainType.MAX_TIME_RED, 4);
        constrainTypeMap.put(ConstrainType.PREF_MAX_TIME_RED, 1);

    }
    public static int getConstrain(ConstrainType constrainType){
        if(constrainTypeMap == null){
            new ConstrainConstantDistributor();
        }
        return constrainTypeMap.get(constrainType);
    }
}
