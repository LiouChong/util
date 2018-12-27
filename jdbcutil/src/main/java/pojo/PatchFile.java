package pojo;


import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "patch_file")
public class PatchFile {
    @Column(name = "id")
    private Integer id;

    @Column(name = "patch_no")
    private String patchNo;

    @Column(name = "os_bit")
    private Integer osBit;

    @Column(name = "file_size")
    private Integer fileSize;

    @Column(name = "md5")
    private String md5;

    @Column(name = "file_url")
    private String fileUrl;

    public PatchFile() {
    }

    public PatchFile(Integer id, String patchNo, Integer osBit, Integer fileSize, String md5) {
        this.id = id;
        this.patchNo = patchNo;
        this.osBit = osBit;
        this.fileSize = fileSize;
        this.md5 = md5;
    }

    public PatchFile(String patchNo, Integer osBit, Integer fileSize, String md5, String fileUrl) {
        this.patchNo = patchNo;
        this.osBit = osBit;
        this.fileSize = fileSize;
        this.md5 = md5;
        this.fileUrl = fileUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPatchNo() {
        return patchNo;
    }

    public void setPatchNo(String patchNo) {
        this.patchNo = patchNo;
    }

    public Integer getOsBit() {
        return osBit;
    }

    public void setOsBit(Integer osBit) {
        this.osBit = osBit;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"patchNo\":\"")
                .append(patchNo).append('\"');
        sb.append(",\"osBit\":")
                .append(osBit);
        sb.append(",\"fileSize\":")
                .append(fileSize);
        sb.append(",\"md5\":\"")
                .append(md5).append('\"');
        sb.append(",\"fileUrl\":\"")
                .append(fileUrl).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
