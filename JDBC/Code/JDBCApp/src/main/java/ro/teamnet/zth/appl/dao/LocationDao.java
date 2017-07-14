package ro.teamnet.zth.appl.dao;

import ro.teamnet.zth.api.em.ColumnInfo;
import ro.teamnet.zth.api.em.EntityManager;
import ro.teamnet.zth.api.em.EntityManagerImpl;
import ro.teamnet.zth.api.em.EntityUtils;
import ro.teamnet.zth.appl.domain.Location;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Gabriel.Tabus on 7/14/2017.
 */
public class LocationDao {
    private EntityManager managerClass = new EntityManagerImpl();

    private Location elem1;
    private Location elem2;

    private HashMap<String, Object> params1;
    private HashMap<String, Object> params2;

    private List<ColumnInfo> columns;

    private void setEntries(){
        elem1 = new Location();
        elem2 = new Location();

        elem1.setId(new Long(3201));
        elem1.setStreetAddress("Test1");
        elem1.setPostalCode("Test1");
        elem1.setCity("Test1");
        elem1.setStateProvince("Test1");

        elem2.setId(new Long(3202));
        elem2.setStreetAddress("Test2");
        elem2.setPostalCode("Test2");
        elem2.setCity("Test2");
        elem2.setStateProvince("Test2");
    }

    private void setParams(){
        params1 = new HashMap<String, Object>();
        params2 = new HashMap<String, Object>();
    }

    private void setColumns(){
        columns = EntityUtils.getColumns(Location.class);
        for(ColumnInfo each : columns){
            try {
                Field field = Location.class.getDeclaredField(each.getColumnName());
                field.setAccessible(true);
                params1.put(each.getDbColumnName(), field.get(elem1));
                params2.put(each.getDbColumnName(), field.get(elem2));
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public Boolean methodInsertEntries(){

        setEntries();
        setParams();
        setColumns();

        managerClass.insert(elem1);
        managerClass.insert(elem2);

        List<Location> entriesList1 = (List<Location>) managerClass.findByParams(elem1.getClass(), params1);
        List<Location> entriesList2 = (List<Location>) managerClass.findByParams(elem1.getClass(), params2);

        if(entriesList1.size() == 1 && entriesList2.size() == 1){
            return true;
        } else {
            return false;
        }
    }

    public Boolean methodDeleteEntries(){

        try {
            managerClass.delete(elem1);
            managerClass.delete(elem2);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Location entry1 = managerClass.findById(elem1.getClass(), elem1.getId());
        Location entry2 = managerClass.findById(elem2.getClass(), elem2.getId());

        if(entry1 == null && entry2 == null)
            return true;
        return false;
    }

    public Boolean methodUpdateEntries(){

        elem1.setId(new Long(3201));
        elem1.setStreetAddress("Modified1");
        elem1.setPostalCode("Modified1");
        elem1.setCity("Modified1");
        elem1.setStateProvince("Modified1");

        elem2.setId(new Long(3202));
        elem2.setStreetAddress("Modified2");
        elem2.setPostalCode("Modified2");
        elem2.setCity("Modified2");
        elem2.setStateProvince("Modified2");

        Location entry1 = null;
        Location entry2 = null;

        try {
            entry1 = managerClass.update(elem1);
            entry2 = managerClass.update(elem2);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        if(entry1 != null && entry2 != null)
            return true;

        return false;
    }
}
