package ro.teamnet.zth.api.em;

import org.junit.Test;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Location;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;


/**
 * Created by Gabriel.Tabus on 7/13/2017.
 */
public class EntityManagerImplTest {

    @Test
    public void testFindById() {
        Long id = new Long(20);
        Long location_id = new Long(1800);

        EntityManagerImpl testClass = new EntityManagerImpl();

        Department entry = testClass.findById(Department.class, id);

        assertEquals(id, entry.getId());
        assertEquals("Marketing", entry.getDepartmentName());
        assertEquals(location_id, entry.getLocation());
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

        Department elem1 = new Department();

        elem1.setDepartmentName("Test");
        elem1.setLocation(new Long(3200));

        Location elem2 = new Location();

        elem2.setCity("Test");
        elem2.setPostalCode("12302");
        elem2.setStateProvince("TestProvince");
        elem2.setStreetAddress("1233 test address");

        //Department entry1 = (Department)testClass.insert(elem1);
        //Location entry2 = (Location)testClass.insert(elem2);

        //assertEquals(elem1.getDepartmentName(), entry1.getDepartmentName());
        //assertEquals(elem2.getId(), entry2.getId());
    }

    @Test
    public void testFindAll() {
        int expectedResult1 = 28;
        int expectedResult2 = 24;

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
}
