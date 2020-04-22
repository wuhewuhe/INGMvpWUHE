package ing.am.bean;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class clients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private int userid;

    @Column(nullable = false)
    private String realname;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private String sex;

    @Column(nullable = false, unique = true)
    private String passportID;
    public clients(){
    }
    public clients(int id, int userid, String realname, int age, String sex, String passportID){
        this.id = id;
        this.userid=userid;
        this.realname=realname;
        this.age=age;
        this.sex=sex;
        this.passportID=passportID;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPassportID() {
        return passportID;
    }

    public void setPassportID(String passportID) {
        this.passportID = passportID;
    }

    @Override
    public String toString() {
        return "clients{" +
                "id=" + id +
                ", userid=" + userid +
                ", realname='" + realname + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", passportID='" + passportID + '\'' +
                '}';
    }
}
