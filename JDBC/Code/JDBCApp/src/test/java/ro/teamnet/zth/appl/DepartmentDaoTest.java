package ro.teamnet.zth.appl;

import org.junit.Test;
import ro.teamnet.zth.appl.dao.DepartmentDao;
import ro.teamnet.zth.appl.domain.Department;

import static junit.framework.TestCase.*;

/**
 * Created by Gabriel.Tabus on 7/14/2017.
 */
public class DepartmentDaoTest {

    @Test
    public void testMethodInsertEntries(){
        DepartmentDao testClass = new DepartmentDao();

        assertEquals(new Boolean(true), testClass.methodInsertEntries());

        testClass.methodDeleteEntries();
    }

    @Test
    public void testMethodDeleteEntries(){
        DepartmentDao testClass = new DepartmentDao();

        testClass.methodInsertEntries();

        assertEquals(new Boolean(true), testClass.methodDeleteEntries());
    }

    @Test
    public void testMethodUpdateEntries(){
        DepartmentDao testClass = new DepartmentDao();

        testClass.methodInsertEntries();

        assertEquals(new Boolean(true), testClass.methodUpdateEntries());

        testClass.methodDeleteEntries();
    }
}
