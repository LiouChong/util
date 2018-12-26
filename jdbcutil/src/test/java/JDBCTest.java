import config.JDBCConfig;
import dao.JdbcTest;
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
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JDBCConfig.class})
public class JDBCTest {
    @Autowired
    private JdbcTest jdbcTest;

    public void addpatchFIle(Object object) {
        Class aClass = object.getClass();
//        jdbcTest.addpatchFIle(new PatchFile("1234",1,201,"asca13","http"));
    }
    @Test
    public void test() throws IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {
//        PatchFile patchFile = new PatchFile("1234",1,201,"asca13","http");
        User user = new User("ly",21,"cd",164);
//        getAnno(user);
        getAnno(user);
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

    public void getObjectValue(Object object) throws IllegalAccessException {
        Class aClass = object.getClass();
        Field[] fields = aClass.getDeclaredFields();
//        String sql = "insert into ";
        for (Field field : fields) {
            field.setAccessible(true);
            Object o = field.get(object);
            System.out.println(field.getName() + " " + o);
        }
    }

    public void getAnno(Object object) throws InvocationTargetException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        Class aClass = object.getClass();
        Table table = (Table) aClass.getAnnotation(Table.class);
        String tableName = "";
        if (table != null) {
            Method[] methods = table.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (method.getName().equals("name")) {
                    method.setAccessible(true);
                    tableName = (String) method.invoke(table,null);
                }
            }
        }

        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Column column = field.getAnnotation(Column.class);
            Method[] columnMethods = column.getClass().getDeclaredMethods();
            for (Method columnMethod : columnMethods) {
                field.set(Class.forName(aClass.getClass().getName()),columnMethod.invoke(column, null) );
                System.out.println(field.get(Class.forName(aClass.getClass().getName())));
            }
        }
//        String sql = "insert into " + tableName;
    }
}
