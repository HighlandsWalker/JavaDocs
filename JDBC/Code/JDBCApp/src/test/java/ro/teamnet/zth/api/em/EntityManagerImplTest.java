package ro.teamnet.zth.api.em;

import org.junit.Test;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Location;

import java.lang.reflect.Field;
import java.util.*;

import static junit.framework.TestCase.*;


/**
 * Created by Gabriel.Tabus on 7/13/2017.
 */
public class EntityManagerImplTest {

    @Test
    public void testFindById() {
        EntityManagerImpl testClass = new EntityManagerImpl();

        // Inserare intrare noua in tabela

        Department elem1 = new Department();
        elem1.setId(new Long(271));
        elem1.setDepartmentName("Test");
        elem1.setLocation(new Long(3200));

        testClass.insert(elem1);

        // Inserare intrare noua in tabela

        Location elem2 = new Location();
        elem2.setId(new Long(3201));
        elem2.setStreetAddress("1233 test address");
        elem2.setPostalCode("12302");
        elem2.setCity("Test");
        elem2.setStateProvince("TestProvince");

        testClass.insert(elem2);

        // Testare findById

        Department entry1 = testClass.findById(Department.class, elem1.getId());
        Location entry2 =  testClass.findById(Location.class, elem2.getId());

        assertEquals(elem1.getId(),             entry1.getId());
        assertEquals(elem1.getDepartmentName(), entry1.getDepartmentName());
        assertEquals(elem1.getLocation(),       entry1.getLocation());

        assertEquals(elem2.getId(),             entry2.getId());
        assertEquals(elem2.getStreetAddress(),  entry2.getStreetAddress());
        assertEquals(elem2.getPostalCode(),     entry2.getPostalCode());
        assertEquals(elem2.getCity(),           entry2.getCity());
        assertEquals(elem2.getStateProvince(),  entry2.getStateProvince());

        try {
            testClass.delete(elem1);
            testClass.delete(elem2);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindByIdNull() {
        EntityManagerImpl testClass = new EntityManagerImpl();

        Department entry1 = testClass.findById(Department.class, null);
        Department entry2 = testClass.findById(null, new Long(20));

        assertEquals(null, entry1);
        assertEquals(null, entry2);
    }

    @Test
    public void testGetNextIdVal() {
        EntityManagerImpl testClass = new EntityManagerImpl();

        Long entry1 = testClass.getNextIdVal("departments", "department_id");
        Long entry2 = testClass.getNextIdVal("locations", "location_id");

        assertEquals(new Long(271), entry1);
        assertEquals(new Long(3201), entry2);
    }

    @Test
    public void testGetNextIdValNull() {
        EntityManagerImpl testClass = new EntityManagerImpl();

        Long entry1 = testClass.getNextIdVal(null, "department_id");
        Long entry2 = testClass.getNextIdVal("locations", null);

        assertEquals(null, entry1);
        assertEquals(null, entry2);
    }

    @Test
    public void testInsert(){
        EntityManagerImpl testClass = new EntityManagerImpl();

        // Creare intrari de inserat in tabele

        Department elem1 = new Department();
        elem1.setDepartmentName("Test");
        elem1.setLocation(new Long(3200));
        elem1.setId(new Long(271));

        Location elem2 = new Location();
        elem2.setId(new Long(3201));
        elem2.setCity("Test");
        elem2.setPostalCode("12302");
        elem2.setStateProvince("TestProvince");
        elem2.setStreetAddress("1233 test address");

        // Testare Insert

        Department entry1 = (Department)testClass.insert(elem1);
        Location entry2 = (Location)testClass.insert(elem2);

        assertEquals(elem1.getDepartmentName(), entry1.getDepartmentName());
        assertEquals((Long)elem1.getId(), (Long)entry1.getId());
        assertEquals((Long)elem2.getId(), (Long)entry2.getId());

        // Stergere intrari inserate

        try {
            testClass.delete(entry1);
            testClass.delete(entry2);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindAll() {
        int expectedResult1 = 27;
        int expectedResult2 = 23;

        EntityManagerImpl testClass = new EntityManagerImpl();

        List<Department> resultedArray1 = testClass.findAll(Department.class);
        List<Location> resultedArray2 = testClass.findAll(Location.class);

        assertEquals(expectedResult1, resultedArray1.size());
        assertEquals(expectedResult2, resultedArray2.size());
    }

    @Test
    public void testFindAllNull() {

        EntityManagerImpl testClass = new EntityManagerImpl();

        List<Department> resultedArray = testClass.findAll(null);

        assertEquals(null, resultedArray);
    }

    @Test
    public void testUpdate(){
        EntityManagerImpl testClass = new EntityManagerImpl();

        // Creare intrare noua, inserare si modificare intrare pentru testare Update

        Department elem1 = new Department();
        elem1.setId(new Long(271));
        elem1.setDepartmentName("Test");
        elem1.setLocation(new Long(3200));

        testClass.insert(elem1);

        elem1.setDepartmentName(new String("Modify"));
        elem1.setLocation(new Long(2800));

        // Creare intrare noua, inserare si modificare intrare pentru testare Update

        Location elem2 = new Location();
        elem2.setId(new Long(3201));
        elem2.setStreetAddress("1233 test address");
        elem2.setPostalCode("12302");
        elem2.setCity("Test");
        elem2.setStateProvince("TestProvince");

        testClass.insert(elem2);

        elem2.setStreetAddress("Modified");
        elem2.setPostalCode("Modified");
        elem2.setCity("Modified");
        elem2.setStateProvince("Modified");

        // Testare update

        Department entry1 = null;
        Location entry2 = null;

        try {
            entry1 = (Department)testClass.update(elem1);
            entry2 = (Location)testClass.update(elem2);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        assertEquals(elem1.getDepartmentName(), entry1.getDepartmentName());
        assertEquals((Long)elem1.getId(),       (Long)entry1.getId());

        assertEquals(elem2.getStateProvince(),  entry2.getStateProvince());
        assertEquals((Long)elem2.getId(),       (Long)entry2.getId());

        // Stergere intrari tabela

        try {
            testClass.delete(entry1);
            testClass.delete(entry2);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdateNull(){
        EntityManagerImpl testClass = new EntityManagerImpl();

        try {
            assertEquals(null, testClass.update(null));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete(){
        EntityManagerImpl testClass = new EntityManagerImpl();

        // Creare intrari tabela si inseare a acestora

        Department elem1 = new Department();
        elem1.setId(new Long(271));
        elem1.setLocation(new Long(2800));
        elem1.setDepartmentName(new String("Modify"));

        Location elem2 = new Location();
        elem2.setId(new Long(3201));
        elem2.setStreetAddress("Modified");
        elem2.setStateProvince("Modified");
        elem2.setPostalCode("Modified");
        elem2.setCity("Modified");

        Department elem3 = new Department();
        elem3.setId(new Long(300));
        elem3.setDepartmentName("Modified3");
        elem3.setLocation(new Long(1700));

        testClass.insert(elem1);
        testClass.insert(elem2);

        // Testare Delete

        try {
            testClass.delete(elem1);
            testClass.delete(elem2);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Department isThere1 = testClass.findById(Department.class,elem1.getId());
        Location isThere2 = testClass.findById(Location.class,elem2.getId());

        assertEquals(null, isThere1);
        assertEquals(null, isThere2);
    }

    @Test
    public void testFindByParams(){
        EntityManagerImpl testClass = new EntityManagerImpl();
        List<ColumnInfo> columns;

        // Inserare intrare noua in tabela DEPARTMENTS

        HashMap<String,Object> paramsMap1 = new HashMap<String, Object>();
        columns = EntityUtils.getColumns(Department.class);
        Department elem1 = new Department();

        elem1.setId(new Long(271));
        elem1.setDepartmentName("Test");
        elem1.setLocation(new Long(3200));

        for(ColumnInfo each : columns){
            try {
                Field field = elem1.getClass().getDeclaredField(each.getColumnName());
                field.setAccessible(true);
                paramsMap1.put(each.getDbColumnName(), field.get(elem1));
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        testClass.insert(elem1);

        // Inserare intrare noua in tabela LOCATIONS

        HashMap<String,Object> paramsMap2 = new HashMap<String, Object>();
        columns.clear();
        columns = EntityUtils.getColumns(Location.class);
        Location elem2 = new Location();

        elem2.setId(new Long(3201));
        elem2.setStreetAddress("1233 test address");
        elem2.setPostalCode("12302");
        elem2.setCity("Test");
        elem2.setStateProvince("TestProvince");

        for(ColumnInfo each : columns){
            try {
                Field field = elem2.getClass().getDeclaredField(each.getColumnName());
                field.setAccessible(true);
                paramsMap2.put(each.getDbColumnName(), field.get(elem2));
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        testClass.insert(elem2);

        // Testare findByParams

        List<Department> entries1 = (List<Department>) testClass.findByParams(elem1.getClass(), paramsMap1);
        List<Location> entries2 = (List<Location>) testClass.findByParams(elem2.getClass(), paramsMap2);

        assertEquals(elem1.getId(),             entries1.get(0).getId());
        assertEquals(elem1.getDepartmentName(), entries1.get(0).getDepartmentName());
        assertEquals(elem1.getLocation(),       entries1.get(0).getLocation());

        assertEquals(elem2.getId(),             entries2.get(0).getId());
        assertEquals(elem2.getStreetAddress(),  entries2.get(0).getStreetAddress());
        assertEquals(elem2.getPostalCode(),     entries2.get(0).getPostalCode());
        assertEquals(elem2.getCity(),           entries2.get(0).getCity());
        assertEquals(elem2.getStateProvince(),  entries2.get(0).getStateProvince());

        try {
            testClass.delete(entries1.get(0));
            testClass.delete(entries2.get(0));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
