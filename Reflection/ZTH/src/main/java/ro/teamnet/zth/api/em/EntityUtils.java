package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.annotation.Column;
import ro.teamnet.zth.api.annotation.Id;
import ro.teamnet.zth.api.annotation.Table;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by Gabriel.Tabus on 7/12/2017.
 */
public class EntityUtils {

    public EntityUtils() {
        throw new UnsupportedOperationException();
    }

    public static String getTableName(Class entity) {
        if (entity == null) return null;
        return ((Table) entity.getAnnotation(Table.class)).name();
    }

    public static ArrayList<ColumnInfo> getColumns(Class entity) {
        if (entity == null) return null;
        ArrayList<ColumnInfo> toReturn = new ArrayList<ColumnInfo>();

        Field[] fields = entity.getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            if (fields[i].isAnnotationPresent(Column.class)) {
                ColumnInfo newCol = new ColumnInfo();
                newCol.setColumnName(fields[i].getName());
                newCol.setColumnType(fields[i].getType());
                newCol.setDbColumnName(((Column) fields[i].getAnnotation(Column.class)).name());
                newCol.setId(false);
                toReturn.add(newCol);
            }
            if (fields[i].isAnnotationPresent(Id.class)) {
                ColumnInfo newCol = new ColumnInfo();
                newCol.setColumnName(fields[i].getName());
                newCol.setColumnType(fields[i].getType());
                newCol.setDbColumnName(((Id) fields[i].getAnnotation(Id.class)).name());
                newCol.setId(true);
                toReturn.add(newCol);
            }
        }
        return toReturn;
    }

    public static Object castFromSqlType(Object value, Class wantedType) {
        if (value == null) return value;
        if (value instanceof BigDecimal) {
            if (wantedType.getSimpleName().equals("Integer")) {
                return ((BigDecimal) value).intValue();
            }
            if (wantedType.getSimpleName().equals("Long")) {
                return ((BigDecimal) value).longValue();
            }
            if (wantedType.getSimpleName().equals("Float")) {
                return ((BigDecimal) value).floatValue();
            }
            if (wantedType.getSimpleName().equals("Double")) {
                return ((BigDecimal) value).doubleValue();
            }
            return (BigDecimal) value;
        } else {
            return value;
        }
    }

    public static ArrayList<Field> getFieldsByAnnotattions(Class clazz, Class annotation) {
        ArrayList<Field> toReturn = new ArrayList<Field>();

        Field[] fields = clazz.getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            if (fields[i].isAnnotationPresent(annotation)) {
                toReturn.add(fields[i]);
            }
        }

        return toReturn;
    }

    public static Object getSqlValue(Object object) {

        if (object.getClass().isAnnotationPresent(Table.class)) {
            Field[] fields = object.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                if (fields[i].isAnnotationPresent(Id.class)) {
                    fields[i].setAccessible(true);
                    return fields[i];
                }
            }
            return null;
        } else {
            return object;
        }
    }
}
