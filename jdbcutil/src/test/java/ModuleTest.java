import config.JDBCConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pojo.PatchFile;
import pojo.User;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JDBCConfig.class})
public class ModuleTest {
    @Autowired
        private JdbcTemplate jdbcTemplate;
    public void addpatchFIle(Object object) {
        Class aClass = object.getClass();
//        jdbcTest.addpatchFIle(new PatchFile("1234",1,201,"asca13","http"));
    }
    @Test
    public void test() throws IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {
//        PatchFile patchFile = new PatchFile("1234",1,201,"asca13","http");
        User user = new User("zxc",21,"cd",164);
//        getAnno(user);
//        jdbcTest.add(user);
        getFieldValue(user);

    }


    public void printMethod(Object object) {
        String modifier = Modifier.toString(object.getClass().getModifiers());
        Class aClass = object.getClass();
        Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            String modifierMethod = Modifier.toString(method.getModifiers());
            System.out.print( modifierMethod + " " + method.getReturnType() + " " + method.getName() + "(");
            Class<?>[] parameterTypes = method.getParameterTypes();
            for (int i = 0; i < parameterTypes.length; i++) {
                if (i > 0)
                    System.out.print(" ," +" " );
                System.out.print(parameterTypes[i].getName());
            }
            System.out.print(" )");
            System.out.println();
        }
    }

    public void printFields(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            String modifier = Modifier.toString(field.getModifiers());
            System.out.println(modifier + " " + field.getType().getName() + " " + field.getName() );
        }
    }

    public List<String> getFieldValue(Object object) throws IllegalAccessException {
        Class aClass = object.getClass();
        Field[] fields = aClass.getDeclaredFields();
        List<String> fieldValue = new LinkedList<String>();
        for (Field field : fields) {
            field.setAccessible(true);
            Object o = field.get(object);
            fieldValue.add(o.toString());
        }
        return fieldValue;
    }

    /**
     * 获取到类上的Table注解的name值即对应数据库的表名
     * @param object
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     */
    public String getTableAnno(Object object) throws InvocationTargetException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        Class aClass = object.getClass();
        Table table = (Table) aClass.getAnnotation(Table.class);
        String tableName = "";
        if (table != null) {
            Method[] methods = table.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (method.getName().equals("name")) {
                    method.setAccessible(true);
                    return (String) method.invoke(table,null);
                }
            }
        }
        return null;
    }

    /**
     * 获取到域的注解上的name值，即对应数据库的字段名。
     * @param object
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
        public List<String> getFieldAnno(Object object) throws InvocationTargetException, IllegalAccessException {
            Class aClass = object.getClass();
            Field[] fields = aClass.getDeclaredFields();
            List<String> fieldList = new LinkedList<String>();

            for (Field field : fields) {
                field.setAccessible(true);
                Column column = field.getAnnotation(Column.class);
                if (column != null) {
                    Method[] declaredMethods = column.getClass().getDeclaredMethods();
                    for (Method declaredMethod : declaredMethods) {
                        if (declaredMethod.getName().equals("name")) {
                            declaredMethod.setAccessible(true);
                            fieldList.add((String) declaredMethod.invoke(column, null));
                        }
                    }
                } else {
                    return null;
                }
            }
            return fieldList;
        }

        public List<String> getFieldName(Object object) {
            Class aClass = object.getClass();
            Field[] declaredFields = aClass.getDeclaredFields();
            List<String> fieldList = new LinkedList<>();
            for (Field declaredField : declaredFields) {
                fieldList.add(declaredField.getName());
            }
            return  null;
        }
        public LinkedHashMap<String,Object> getColumnAndValue(Object object) throws InvocationTargetException, IllegalAccessException {
            Class aClass = object.getClass();
            Field[] fields = aClass.getDeclaredFields();
            LinkedHashMap<String,Object> result = new LinkedHashMap<String,Object>();
            for (Field field : fields) {
                if (field.getName().equals("id"))
                    continue;
                field.setAccessible(true);
                //object为对象的域值
                Object o = field.get(object);
                Column column = field.getAnnotation(Column.class);
                //如果有column注解
                if (column != null) {
                    Method[] methods = column.getClass().getDeclaredMethods();
                    for (Method method : methods) {
                        if (method.getName().equals("name")) {
                            method.setAccessible(true);
                            String columnString = (String) method.invoke(column, null);
                            result.put(columnString, o);
                        }
                    }
                } else {
                    result.put(field.getName(), o);
                }
            }
            return result;
        }



        @Test
        public void insert() throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
//            User object = new User("lc", 21, "cd",165);
            PatchFile object = new PatchFile("6324asd", 1, 2048, "asuh1ife", "httpS/asd");
            String tableName = getTableAnno(object);
            LinkedHashMap<String, Object> linkedHashMap = getColumnAndValue(object);

            StringBuilder sql = new StringBuilder("INSERT INTO ").append(tableName).append("(");
            Set<String> strings = linkedHashMap.keySet();
            for (Map.Entry<String, Object> e : linkedHashMap.entrySet()) {
                if (e.getValue() != null)
                    sql.append("`").append(e.getKey()).append("`,");  //拼接字段名称
            }
            sql.deleteCharAt(sql.length() - 1).append(") VALUES(");        //删除最后一个逗号
            int size = linkedHashMap.size();
            for(int i = 0; i < size; i++) {
                sql.append("?,");           //拼接问号
            }
            sql.deleteCharAt(sql.length() - 1);     //删除最后一个逗号
            sql.append(")");
            System.out.println(sql);
            Collection<Object> values = linkedHashMap.values();
            jdbcTemplate.update(sql.toString(), values.toArray());
        }


}
