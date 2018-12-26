package dao;

import com.sun.jna.platform.win32.Netapi32Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pojo.PatchFile;
import pojo.User;

@Repository
public class JdbcTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void add(User user) {
//        String sql = "insert into patch_file(patch_no,os_bit,file_size,md5,file_url) values (?,?,?,?,?)";
//        jdbcTemplate.update(sql,patchFile.getPatchNo(),patchFile.getOsBit(),patchFile.getFileSize(),patchFile.getMd5(),patchFile.getFileUrl());
        String sql = "insert into User(name,age,address,height) values(?,?,?,?)";
        jdbcTemplate.update(sql, user.getName(), user.getAge(), user.getAddress(), user.getHeight());

    }
}
