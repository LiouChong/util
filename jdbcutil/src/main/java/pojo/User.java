package pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
    @Column(name = "id")
    private Integer id;

    @Column(name = "u_name")
    private String name;

    @Column(name = "u_age")
    private Integer age;

    @Column(name = "u_address")
    private String address;

    @Column(name = "u_height")
    private Integer height;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(String name, Integer age, String address, Integer height) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.height = height;
    }

    public User(Integer id, String name, Integer age, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
    }


    public User(Integer id, String name, Integer age, String address, Integer height) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.height = height;
    }

    public User(String name, Integer age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public User(Integer age, String address, Integer height) {
        this.age = age;
        this.address = address;
        this.height = height;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"age\":")
                .append(age);
        sb.append(",\"address\":\"")
                .append(address).append('\"');
        sb.append(",\"height\":")
                .append(height);
        sb.append('}');
        return sb.toString();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }
}
