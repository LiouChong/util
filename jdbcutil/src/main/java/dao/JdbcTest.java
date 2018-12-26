package dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pojo.PatchFile;

@Repository
public class JdbcTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addpatchFIle(PatchFile patchFile) {
       String sql = "insert into patch_file(patch_no,os_bit,file_size,md5,file_url) values (?,?,?,?,?)";
       jdbcTemplate.update(sql,patchFile.getPatchNo(),patchFile.getOsBit(),patchFile.getFileSize(),patchFile.getMd5(),patchFile.getFileUrl());
    }
}
