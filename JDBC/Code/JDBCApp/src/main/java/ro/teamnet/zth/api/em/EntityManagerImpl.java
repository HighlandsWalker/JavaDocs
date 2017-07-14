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
import java.util.Map;

/**
 * Created by Gabriel.Tabus on 7/13/2017.
 */
public class EntityManagerImpl implements EntityManager {

    @Override
    public <T> T findById(Class<T> entityClass, Long id) {
        if (entityClass == null) return null;
        if (id == null) return null;
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
            if (!resultSet.next()) {
                return null;
            } else {
                T elem = entityClass.newInstance();
                for (ColumnInfo each : columns) {
                    Field field = elem.getClass().getDeclaredField(each.getColumnName());
                    field.setAccessible(true);
                    field.set(elem, EntityUtils.castFromSqlType(
                            resultSet.getObject(each.getDbColumnName()),
                            each.getColumnType()));
                }
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
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public Long getNextIdVal(String tableName, String columnIdName) {
        if (tableName == null || columnIdName == null) return null;
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
                toReturn = (Long) EntityUtils.castFromSqlType(rs.getObject(1), Long.class);
            }
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
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return toReturn + 1;
    }

    @Override
    public Object insert(Object entity) {
        if (entity == null) return null;

        QueryBuilder builder = new QueryBuilder();
        Connection conn = DBManager.getConnection();

        String tableName = EntityUtils.getTableName(entity.getClass());
        List<ColumnInfo> columns = EntityUtils.getColumns(entity.getClass());

        Long id = new Long(0);

        for (ColumnInfo each : columns) {
            if (each.isId()) {
                Long nextIdVal = getNextIdVal(tableName, each.getDbColumnName());
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
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    @Override
    public <T> List<T> findAll(Class<T> entityClass) {
        if (entityClass == null) return null;
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
            while (resultSet.next()) {
                T elem = entityClass.newInstance();
                for (ColumnInfo each : columns) {
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
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public <T> T update(T entity) throws NoSuchFieldException, IllegalAccessException {
        if (entity == null) return null;
        QueryBuilder builder = new QueryBuilder();
        Connection conn = DBManager.getConnection();
        Long id = new Long(0);

        // Get Builder Info

        String tableName = EntityUtils.getTableName(entity.getClass());
        List<ColumnInfo> columns = EntityUtils.getColumns(entity.getClass());
        Condition cond = new Condition();

        // Set Columns and condition

        for (ColumnInfo each : columns) {
            Field field = entity.getClass().getDeclaredField(each.getColumnName());
            field.setAccessible(true);
            each.setValue(field.get(entity));
            if (each.isId()) {
                cond.setValue(each.getValue());
                cond.setColumnName(each.getDbColumnName());
                id = (Long) each.getValue();
            }
        }

        // Set Builder

        builder.setTableName(tableName);
        builder.setQueryType(QueryType.UPDATE);
        builder.addQueryColumns(columns);
        builder.addCondition(cond);
        String query = builder.createQuery();

        // Execute Query

        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);
            return (T) findById(entity.getClass(), id);
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
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public void delete(Object entity) throws NoSuchFieldException, IllegalAccessException {
        if (entity == null) return;
        QueryBuilder builder = new QueryBuilder();
        Connection conn = DBManager.getConnection();

        // Get Builder Info

        String tableName = EntityUtils.getTableName(entity.getClass());
        List<ColumnInfo> columns = EntityUtils.getColumns(entity.getClass());
        Condition cond = new Condition();

        // Set Columns and condition

        for (ColumnInfo each : columns) {
            Field field = entity.getClass().getDeclaredField(each.getColumnName());
            field.setAccessible(true);
            each.setValue(field.get(entity));
            if (each.isId()) {
                cond.setValue(each.getValue());
                cond.setColumnName(each.getDbColumnName());
                //id = (Long)each.getValue();
            }
        }

        // Set Builder

        builder.setTableName(tableName);
        builder.setQueryType(QueryType.DELETE);
        builder.addCondition(cond);
        String query = builder.createQuery();

        // Execute Query

        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);
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
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public <T> List<T> findByParams(Class<T> entityClass, Map<String, Object> params) {
        if (entityClass == null || params == null) return null;
        QueryBuilder builder = new QueryBuilder();
        Connection conn = DBManager.getConnection();

        // Get Builder Info

        String tableName = EntityUtils.getTableName(entityClass);
        List<ColumnInfo> columns = EntityUtils.getColumns(entityClass);

        // Set Builder

        builder.setTableName(tableName);
        builder.setQueryType(QueryType.SELECT);
        builder.addQueryColumns(columns);
        for (ColumnInfo each : columns) {
            Condition cond = new Condition();
            cond.setColumnName(each.getDbColumnName());
            cond.setValue(params.get(each.getDbColumnName()));
            builder.addCondition(cond);
        }
        String query = builder.createQuery();

        // Execute Query

        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);
            ArrayList<T> toReturnList = new ArrayList<T>();
            while (resultSet.next()) {
                T elem = entityClass.newInstance();
                for (ColumnInfo each : columns) {
                    Field field = entityClass.getDeclaredField(each.getColumnName());
                    field.setAccessible(true);
                    field.set(elem, EntityUtils.castFromSqlType(
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
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public <T> List<T> selectByString(Class<T> entityClass, String cond, String tableNameA, String tableNameB) {
        Connection conn = DBManager.getConnection();
        ArrayList<T> toReturnList = new ArrayList<T>();

        // Get Builder Info

        String tableName = EntityUtils.getTableName(entityClass);
        List<ColumnInfo> columns = EntityUtils.getColumns(entityClass);

        String query = new String("SELECT a.* FROM " + tableNameA + " a "
                + "INNER JOIN " + tableNameB + " b "
                + "on a.department_id=b.department_id "
                + "where lower(department_name) LIKE '%" + cond + "%\'");

        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                T elem = entityClass.newInstance();
                for (ColumnInfo each : columns) {
                    Field field = entityClass.getDeclaredField(each.getColumnName());
                    field.setAccessible(true);
                    field.set(elem, EntityUtils.castFromSqlType(
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
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public <T> Long insertAllOneTransaction(Class<T> entityClass, List<T> entries) {
        Connection conn = DBManager.getConnection();
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Long nextIdVal = new Long(0);

        String tableName = EntityUtils.getTableName(entityClass);
        List<ColumnInfo> columns = EntityUtils.getColumns(entityClass);
        for(ColumnInfo each : columns){
            if(each.isId()){
                nextIdVal = getNextIdVal(tableName, each.getDbColumnName());
            }
        }

        Statement stmt = null;
        try {
            Long count = new Long(0);
            for (T each : entries) {
                QueryBuilder builder = new QueryBuilder();
                String query;

                for (ColumnInfo fieldInfo : columns) {
                    if (fieldInfo.isId()) {
                        fieldInfo.setValue(nextIdVal);
                        nextIdVal++;
                    } else {
                        Field field = null;
                        try {
                            field = each.getClass().getDeclaredField(fieldInfo.getColumnName());
                            field.setAccessible(true);
                            fieldInfo.setValue(field.get(each));
                        } catch (NoSuchFieldException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
                builder.setQueryType(QueryType.INSERT);
                builder.addQueryColumns(columns);
                builder.setTableName(tableName);

                query = builder.createQuery();

                stmt = conn.createStatement();
                ResultSet resultSet = stmt.executeQuery(query);

                stmt.close();
                stmt = null;

                count++;
            }
            conn.commit();
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
