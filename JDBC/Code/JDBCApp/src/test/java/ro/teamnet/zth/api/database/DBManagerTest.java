package ro.teamnet.zth.api.database;

import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static junit.framework.TestCase.*;
import static ro.teamnet.zth.api.database.DBManager.*;

/**
 * Created by Gabriel.Tabus on 7/13/2017.
 */
public class DBManagerTest {

    @Test
    public void testGetConnection(){
            Connection conn = DBManager.getConnection();
            assertNotNull(conn);
    }

    @Test
    public void testCheckConnection(){
            String result = DBManager.checkConnection(DBManager.getConnection());

            assertEquals("1", result);
    }
}
