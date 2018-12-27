package dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;

@Repository
public class JDBCUtil {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private AnnoUtil annoUtil;

    /**
     * 没有处理创建人，创建时间、状态。建议传入之前就设置好。否则为使用默认值。
     * @param object
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public int insert(Object object) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
        String tableName = annoUtil.getTableAnno(object);

        Map<String, Object> fieldAndValue = annoUtil.getFieldAndValueForAdd(object);

        String sql = getInsertSql(fieldAndValue, tableName);

        KeyHolder holder = new GeneratedKeyHolder();
        Object[] values = fieldAndValue.values().toArray();

        //用来获取插入后的id值
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < values.length; i++) {
                ps.setObject(i + 1, values[i]);
            }
            return ps;
        }, holder);

        return holder.getKey().intValue();
    }

    public void update(Object object) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
//        获取到表名，用于生成sql语句
        String tableName = annoUtil.getTableAnno(object);
//        map键值对分别为，列名和变量值。用于生成sql语句
        Map<String, Object> fieldAndValue = annoUtil.getFieldAndValueForUpdate(object);
//        生成sql语句
        String sql = getUpdateSql(fieldAndValue, tableName);

        Object[] objects = fieldAndValue.values().toArray();
//        将id移出数组,然后将数组对应问号，因为sql语句中已经将id值写入。
        Object[] removeIdArr = Arrays.copyOfRange(objects, 1, objects.length);
        jdbcTemplate.update(sql, removeIdArr);
    }

    public void delete(Object object) throws NoSuchFieldException, IllegalAccessException, InstantiationException, ClassNotFoundException, InvocationTargetException {
        String tableName = annoUtil.getTableAnno(object);

        Object id = annoUtil.getIdForDel(object);

        String sql = "DELETE FROM " + tableName + " WHERE id =" + id;
        jdbcTemplate.execute(sql);
    }


    public String getUpdateSql(Map<String, Object> fieldAndValue, String tableName) {
        StringBuilder sql = new StringBuilder("UPDATE ").append(tableName).append(" SET ");

        for (String s : fieldAndValue.keySet()) {
            if (s.equals("id")) {
                continue;
            }
            sql.append(s).append("=").append("?, ");
        }

//        此处还是用delete好点，用subString会生成String导致需要再次new Stringbuilder
        sql.delete(sql.length() - 2, sql.length() - 1);
        sql.append(" WHERE id = ").append(fieldAndValue.get("id"));
        return sql.toString();
    }

    public String getInsertSql(Map<String, Object> fieldAndValue, String tableName) {
        StringBuilder sqlHead = new StringBuilder("INSERT INTO ").append(tableName).append(" ( ");

        StringBuilder sqlTail = new StringBuilder("VALUES ( ");

        Set<String> strings = fieldAndValue.keySet();
        for (String string : strings) {
//            INSERT INTO (列名,列名,列名,
            sqlHead.append(string).append(", ");
//            VALUES(*,*,*,
            sqlTail.append("?, ");
        }
//        删除最后一个逗号
        sqlHead.deleteCharAt(sqlHead.length() - 2).append(") ");
//        删除最后一个逗号
        sqlTail.deleteCharAt(sqlTail.length() - 2).append(")");

        return sqlHead.append(sqlTail).toString();
    }

}
