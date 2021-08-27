package cn.zachary.greendaodemo.database.table;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * 类一定是要是public
 */
@Entity
public class Student {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private String sex;
    private String address;
    @Generated(hash = 1082129564)
    public Student(Long id, String name, String sex, String address) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.address = address;
    }
    @Generated(hash = 1556870573)
    public Student() {
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
