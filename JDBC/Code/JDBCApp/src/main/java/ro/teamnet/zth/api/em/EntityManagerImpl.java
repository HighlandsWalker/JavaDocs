package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.database.DBManager;
import ro.teamnet.zth.appl.domain.Department;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Gabriel.Tabus on 7/13/2017.
 */
public class EntityManagerImpl implements EntityManager {

    @Override
    public <T> T findById(Class<T> entityClass, Long id) {
        if(entityClass == null) return null;
        if(id == null) return null;
        QueryBuilder builder = new QueryBuilder();
        Connection conn = DBManager.getConnection();

        // Get Builder Info

        String tableName = EntityUtils.getTableName(entityClass);
        List<ColumnInfo> columns = EntityUtils.getColumns(entityClass);

        Condition cond = new Condition();

        for (ColumnInfo each : columns) {
            if (each.isId()) {
                cond.setColumnName(each.getDbColumnName());
                cond.setValue(id);
            }
        }

        // Set Builder Info

        builder.setTableName(tableName);
        builder.setQueryType(QueryType.SELECT);
        builder.addQueryColumns(columns);
        builder.addCondition(cond);
        String query = builder.createQuery();

        // Statement Call

        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                T elem = entityClass.newInstance();
                for (ColumnInfo each : columns) {
                    Field field = elem.getClass().getDeclaredField(each.getColumnName());
                    field.setAccessible(true);
                    field.set(elem, EntityUtils.castFromSqlType(
                                                        resultSet.getObject(each.getDbColumnName()),
                                                        each.getColumnType()));
                }
                conn.close();
                return elem;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public Long getNextIdVal(String tableName, String columnIdName) {
        if(tableName == null || columnIdName == null) return null;
        Connection conn = DBManager.getConnection();

        Long toReturn = new Long(0);

        // Statement Init

        Statement stmt = null;

        // Query Construction

        String query = "select max(" + columnIdName + ") from " + tableName;

        // Query execution

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                toReturn = (Long)EntityUtils.castFromSqlType(rs.getObject(1), Long.class);
            }
            if (stmt != null) { stmt.close(); }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return toReturn + 1;
    }

    @Override
    public Object insert(Object entity) {
        if(entity == null) return null;

        QueryBuilder builder = new QueryBuilder();
        Connection conn = DBManager.getConnection();

        String tableName = EntityUtils.getTableName(entity.getClass());
        List<ColumnInfo> columns = EntityUtils.getColumns(entity.getClass());

        Long id = new Long(0);

        for(ColumnInfo each : columns){
            if(each.isId()){
                Long nextIdVal = getNextIdVal(tableName, each.getColumnName());
                each.setValue(nextIdVal);
                id = nextIdVal;
            } else {
                Field field = null;
                try {
                    field = entity.getClass().getDeclaredField(each.getColumnName());
                    field.setAccessible(true);
                    each.setValue(field.get(entity));
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        // Set Builder Info

        builder.setTableName(tableName);
        builder.setQueryType(QueryType.INSERT);
        builder.addQueryColumns(columns);

        String query = builder.createQuery();

        // Statement Call

        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);
            return findById(entity.getClass(), id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    @Override
    public <T> List<T> findAll(Class<T> entityClass) {
        if(entityClass == null) return null;
        QueryBuilder builder = new QueryBuilder();
        Connection conn = DBManager.getConnection();

        // Get Builder Info

        String tableName = EntityUtils.getTableName(entityClass);
        List<ColumnInfo> columns = EntityUtils.getColumns(entityClass);

        // Set Builder

        builder.setTableName(tableName);
        builder.setQueryType(QueryType.SELECT);
        builder.addQueryColumns(columns);

        String query = builder.createQuery();

        // Execution of Query

        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);
            ArrayList<T> toReturnList = new ArrayList<T>();
            while(resultSet.next()){
                T elem = entityClass.newInstance();
                for(ColumnInfo each : columns){
                    Field field = elem.getClass().getDeclaredField(each.getColumnName());
                    field.setAccessible(true);
                    field.set(elem,
                            EntityUtils.castFromSqlType(
                                    resultSet.getObject(each.getDbColumnName()),
                                    each.getColumnType()));
                }
                toReturnList.add(elem);
            }
            return toReturnList;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
