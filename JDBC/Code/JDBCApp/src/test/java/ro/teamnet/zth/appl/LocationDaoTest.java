package ro.teamnet.zth.appl;

import org.junit.Test;
import ro.teamnet.zth.appl.dao.DepartmentDao;
import ro.teamnet.zth.appl.dao.LocationDao;
import ro.teamnet.zth.appl.domain.Department;

import static junit.framework.TestCase.*;

/**
 * Created by Gabriel.Tabus on 7/14/2017.
 */
public class LocationDaoTest {

    @Test
    public void testMethodInsertEntries(){
        LocationDao testClass = new LocationDao();

        assertEquals(new Boolean(true), testClass.methodInsertEntries());

        testClass.methodDeleteEntries();
    }

    @Test
    public void testMethodDeleteEntries(){
        LocationDao testClass = new LocationDao();

        testClass.methodInsertEntries();

        assertEquals(new Boolean(true), testClass.methodDeleteEntries());
    }

    @Test
    public void testMethodUpdateEntries(){
        LocationDao testClass = new LocationDao();

        testClass.methodInsertEntries();

        assertEquals(new Boolean(true), testClass.methodUpdateEntries());

        testClass.methodDeleteEntries();
    }
}
