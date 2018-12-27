package dao;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class AnnoUtil {

    /**
     * 方法中没有取id的值，即result中没有id这个键（mysql自动递增）
     *
     * @param object
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public Map<String, Object> getFieldAndValueForAdd(Object object) throws InvocationTargetException, IllegalAccessException {
        Class aClass = object.getClass();
        Field[] fields = aClass.getDeclaredFields();

        Map<String, Object> result = new LinkedHashMap<>();
        return getFieldAndValue(object, fields, result, "add");
    }


    /**
     * @param object
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public Map<String, Object> getFieldAndValueForUpdate(Object object) throws InvocationTargetException, IllegalAccessException {
        Class aClass = object.getClass();
        Field[] fields = aClass.getDeclaredFields();
        Map<String, Object> result = new LinkedHashMap<>();
        return getFieldAndValue(object, fields, result, "update");
    }

    /**
     * 获取字段值，若变量上有注解Column则取出name值作为映射的字段名，若没有注解
     * 则以变量名作为映射的字段值
     *
     * @param object
     * @param fields
     * @param result
     * @param opration
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public Map<String, Object> getFieldAndValue(Object object, Field[] fields, Map<String, Object> result, String opration) throws IllegalAccessException, InvocationTargetException {
        for (Field field : fields) {

            if (opration.equals("add") && field.getName().equals("id")) {
                continue;
            }

//            将域设置为可以取值，然后将值赋给o
            field.setAccessible(true);
            Object o = field.get(object);
            if (o == null) {
                continue;
            }

            Column column = field.getAnnotation(Column.class);
//            如果有column注解
            if (column != null) {
//                column注解中,name在注解中是方法，因此先获取column里的方法。
                Method[] methods = column.getClass().getDeclaredMethods();
                for (Method method : methods) {
                    //方法名如果为name，即@Column(name="列名")
                    if (method.getName().equals("name")) {
                        //获取到name方法的值，即列名。并将值赋给columnString。
                        method.setAccessible(true);
                        String columnString = (String) method.invoke(column, null);
                        //linkedHashMap中存储的键为列名，值为对象的变量值。
                        result.put(columnString, o);
                    }
                }
            } else {
                //如果没有column注解，则linkedhashmap存储变量名和变量值。
                result.put(field.getName(), o);
            }
        }
        return result;
    }

    public Object getIdForDel(Object object) throws NoSuchFieldException, IllegalAccessException {
        Class aClass = object.getClass();
        Field id = aClass.getDeclaredField("id");
        Object o = new Object();
        if (id != null) {
            id.setAccessible(true);
            o = id.get(object);
            System.out.println(o);
        }
        return o;
    }

    /**
     * 从@Table注解上获取类对应的表明。
     *
     * @param
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     */
    public String getTableAnno(Object object) throws InvocationTargetException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        Class aClass = object.getClass();
        Table table = (Table) aClass.getAnnotation(Table.class);
        if (table != null) {
//            @Table注解中name为方法，因此先获取所有方法，再在方法里面找到name方法取出
            Method[] methods = table.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (method.getName().equals("name")) {
                    method.setAccessible(true);
                    return (String) method.invoke(table, null);
                }
            }
        }
        return null;
    }
}
