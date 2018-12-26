package pojo;


import javax.persistence.Table;

@Table(name = "patch_file")
public class PatchFile {
    private int id;

    private String patchNo;

    private int osBit;

    private int fileSize;

    private String md5;

    private String fileUrl;

    public PatchFile() {
    }

    public PatchFile(String patchNo, int osBit, int fileSize, String md5, String fileUrl) {
        this.patchNo = patchNo;
        this.osBit = osBit;
        this.fileSize = fileSize;
        this.md5 = md5;
        this.fileUrl = fileUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPatchNo() {
        return patchNo;
    }

    public void setPatchNo(String patchNo) {
        this.patchNo = patchNo;
    }

    public int getOsBit() {
        return osBit;
    }

    public void setOsBit(int osBit) {
        this.osBit = osBit;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
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
