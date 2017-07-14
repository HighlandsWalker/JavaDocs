package ro.teamnet.zth.appl.dao;

import ro.teamnet.zth.api.em.ColumnInfo;
import ro.teamnet.zth.api.em.EntityManagerImpl;
import ro.teamnet.zth.api.em.EntityUtils;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Location;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Gabriel.Tabus on 7/14/2017.
 */
public class DepartmentDao {
    private EntityManagerImpl managerClass = new EntityManagerImpl();

    private Department elem1;
    private Department elem2;

    private HashMap<String, Object> params1;
    private HashMap<String, Object> params2;

    private List<ColumnInfo> columns;

    private void setEntries(){
        elem1 = new Department();
        elem2 = new Department();

        elem1.setId(new Long(271));
        elem1.setDepartmentName("Test1");
        elem1.setLocation(new Long(3200));


        elem2.setId(new Long(272));
        elem2.setDepartmentName("Test2");
        elem2.setLocation(new Long(1700));
    }

    private void setParams(){
        params1 = new HashMap<String, Object>();
        params2 = new HashMap<String, Object>();
    }

    private void setColumns(){
        columns = EntityUtils.getColumns(Department.class);
        for(ColumnInfo each : columns){
            try {
                Field field = Department.class.getDeclaredField(each.getColumnName());
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

        List<Department> entriesList1 = (List<Department>) managerClass.findByParams(Department.class, params1);
        List<Department> entriesList2 = (List<Department>) managerClass.findByParams(Department.class, params2);

        try {
            managerClass.delete(elem1);
            managerClass.delete(elem2);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

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

        Department entry1 = managerClass.findById(elem1.getClass(), elem1.getId());
        Department entry2 = managerClass.findById(elem2.getClass(), elem2.getId());

        if(entry1 == null && entry2 == null)
            return true;
        return false;
    }

    public Boolean methodUpdateEntries(){

        elem1.setLocation(new Long(2000));
        elem1.setDepartmentName("Modified1");
        elem2.setLocation(new Long(2100));
        elem2.setDepartmentName("Modified2");

        Department entry1 = null;
        Department entry2 = null;

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
