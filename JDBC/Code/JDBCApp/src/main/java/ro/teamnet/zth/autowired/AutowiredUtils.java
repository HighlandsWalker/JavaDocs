package ro.teamnet.zth.autowired;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mihaela.Scripcaru on 7/7/2017.
 */
public class AutowiredUtils {

    public static Map<String, Object> mapIntancesByQualifier = new HashMap<>();
    public static Map<Class, Object> mapIntancesByType = new HashMap<>();

    public static void updateMapIntancesByQualifier(Object object){
        Class clazz = object.getClass();
        MyQualifier qualifierAnnotation = (MyQualifier) clazz.getAnnotation(MyQualifier.class);
        String qualifier = qualifierAnnotation.value();

        mapIntancesByQualifier.put(qualifier, object);
    }

    public static void updateMapIntancesByType(Object object){
        Class clazz = object.getClass();
        mapIntancesByType.put(clazz, object);
    }

    public static void handleFieldAutowiring(Object foo) throws IllegalAccessException {
        Class clazz = foo.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            final MyAutowired autowiredAnnotation = field.getAnnotation(MyAutowired.class);
            if (autowiredAnnotation != null) {
                String autowiredAnnotationValue = autowiredAnnotation.value();

                if(autowiredAnnotationValue != null && !autowiredAnnotationValue.equals("")){
                    String qualifierValue = autowiredAnnotationValue;
                    Object autowirableInstance = getInstanceByQualifier(qualifierValue);
                    field.setAccessible(true);
                    field.set(foo, autowirableInstance);
                }else{
                    Object autowirableInstance = getInstanceByType(field.getType());
                    field.setAccessible(true);
                    field.set(foo, autowirableInstance);
                }
            }
        }
    }

    public static Object getInstanceByType(Class fieldType){
        return mapIntancesByType.get(fieldType);
    }

    public static Object getInstanceByQualifier(String qualifierValue){
        return mapIntancesByQualifier.get(qualifierValue);
    }
}
