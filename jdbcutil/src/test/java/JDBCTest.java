import config.JDBCConfig;
import dao.JdbcTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pojo.PatchFile;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JDBCConfig.class})
public class JDBCTest {
    @Autowired
    private JdbcTest jdbcTest;

    @Test
    public void addpatchFIle(Object object) {
        Class<?> aClass = object.getClass();
        jdbcTest.addpatchFIle(new PatchFile("1234",1,201,"asca13","http"));
    }
}
