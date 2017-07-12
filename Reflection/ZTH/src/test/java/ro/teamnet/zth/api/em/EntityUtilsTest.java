package ro.teamnet.zth.api.em;

import org.junit.Test;
import ro.teamnet.zth.api.annotation.Column;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Location;

import javax.swing.text.html.parser.Entity;
import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Gabriel.Tabus on 7/12/2017.
 */
public class EntityUtilsTest {

    // GetTableNameMethod TESTS
    @Test
    public void testGetTableNameMethod() {
        String tableName = EntityUtils.getTableName(Department.class);
        assertEquals("Table name should be departments!", "departments", tableName);
    }

    @Test
    public void testGetTableNameMethodNull(){
        String tableName = EntityUtils.getTableName(null);
        assertEquals(null,tableName);
    }

    // GetColumns TESTS
    @Test
    public void testGetColumns(){
        ArrayList<ColumnInfo> expectedResult = new ArrayList<ColumnInfo>();
        ArrayList<ColumnInfo> methodResult = EntityUtils.getColumns(Department.class);

        ColumnInfo item = new ColumnInfo();
        item.setColumnName("id");
        item.setColumnType(Long.class);
        item.setDbColumnName("department_id");
        item.setId(true);

        expectedResult.add(item);

        item = new ColumnInfo();
        item.setColumnName("departmentName");
        item.setColumnType(String.class);
        item.setDbColumnName("department_name");
        item.setId(false);

        expectedResult.add(item);

        item = new ColumnInfo();
        item.setColumnName("location");
        item.setColumnType(Location.class);
        item.setDbColumnName("location_id");
        item.setId(false);

        expectedResult.add(item);

        assertEquals(expectedResult.size(), methodResult.size());
    }

    @Test
    public void testGetColumnsNull(){
        ArrayList<ColumnInfo> methodResult = EntityUtils.getColumns(null);
        assertEquals(null, methodResult);
    }

    @Test
    public void testCastFromSqlType(){
        BigDecimal decimal = new BigDecimal("2.41243252");


        Object result1 = EntityUtils.castFromSqlType(decimal, Integer.class);
        Object result2 = EntityUtils.castFromSqlType(decimal, Long.class);
        Object result3 = EntityUtils.castFromSqlType(decimal, Float.class);
        Object result4 = EntityUtils.castFromSqlType(decimal, Double.class);
        Object result5 = EntityUtils.castFromSqlType(null,Double.class);
        Object result6 = EntityUtils.castFromSqlType(decimal, Class.class);

        assertEquals((int) 2.41243252, result1);
        assertEquals((long) 2.41243252, result2);
        assertEquals((float) 2.41243252, result3);
        assertEquals((double) 2.41243252, result4);
        assertEquals(null,result5);
        assertEquals(decimal,result6);
    }
}
