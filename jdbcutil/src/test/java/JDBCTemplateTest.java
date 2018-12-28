import config.JDBCConfig;
import dao.JDBCTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pojo.AssetVulnerabilityRel;
import pojo.PatchFile;
import pojo.User;

import java.lang.reflect.InvocationTargetException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JDBCConfig.class})
public class JDBCTemplateTest {
    @Autowired
    private JDBCTemplate jdbcTemplate;

    @Test
    public void test() throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
//        AssetVulnerabilityRel object = new AssetVulnerabilityRel(1, "KB1858", "白象", 1, 1, "计算机", "128.265.154", 1, 2);
        User object = new User("2222",21,"cd");
        System.out.println(jdbcTemplate.insert(object));
    }


    @Test
    public void addTest() throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
//        User object = new User(21,"lq",180);
        AssetVulnerabilityRel object = new AssetVulnerabilityRel(1, "KB1858", "白象", 1, 1, "计算机", "128.265.154", 1, 2);
        System.out.println(jdbcTemplate.insert(object));
    }

    @Test
    public void testUpdate() throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
//        User object = new User(3,"zzj",21,"lq");
//        AssetVulnerabilityRel object = new AssetVulnerabilityRel(5,5, "GB123", "红象", 0, 0, "手机", "128.265.154", 1, 2);
        PatchFile object = new PatchFile(2,"GB123",1,512,"6324");
        jdbcTemplate.update(object);
    }


    @Test
    public void testDelete() throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException, InvocationTargetException, InstantiationException {
//        User user = new User(7);
        PatchFile object = new PatchFile(5);
        jdbcTemplate.delete(object);
    }
}
