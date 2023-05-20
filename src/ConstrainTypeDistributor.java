import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * singleton
 * ky osht map in map sepse ka shum hard-nenconstraints
 */
public class ConstrainTypeDistributor {

    private static Map<ConstrainType, Map> constrainTypeMap;

    private ConstrainTypeDistributor(){
        constrainTypeMap = new LinkedHashMap<>();

        Map<String, Integer> hard_constrain = new HashMap<>();
        hard_constrain.put("penalty for vehicles crossing paths", -100000);
        hard_constrain.put("penalty for red light longer than frames requested", -50000);
        hard_constrain.put("penalty for green light less than frames requested", -50000);
        constrainTypeMap.put(ConstrainType.HARD_CONSTRAIN_PENALTY, hard_constrain);

        Map<String, Integer> cool_constrain = new HashMap<>();
        cool_constrain.put("penalty for breaking cool constrain", -5000);
        constrainTypeMap.put(ConstrainType.COOL_CONSTRAIN_PENALTY, cool_constrain);

        Map<String, Integer> soft_constrain = new HashMap<>();
        soft_constrain.put("penalty for breaking soft constrain", -500);
        soft_constrain.put("penalty for green light less then frames preferred", -500);
        soft_constrain.put("penalty for red light longer then frames preferred", -500);
        constrainTypeMap.put(ConstrainType.SOFT_CONSTRAIN_PENALTY, soft_constrain);
    }
    public static Map<String, Integer> getConstrain(ConstrainType constrainType){
        if(constrainTypeMap == null){
            new ConstrainTypeDistributor();
        }
        return constrainTypeMap.get(constrainType);
    }
}
