package ing.am.bean;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class clients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private int clientid;

    @Column(nullable = false)
    private String realname;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private boolean sex;

    public clients() {
    }

    public clients(int id, int clientid, String realname, int age, boolean sex) {
        this.id = id;
        this.clientid = clientid;
        this.realname = realname;
        this.age = age;
        this.sex = sex;
    }

    public int getClientid() {
        return clientid;
    }

    public void setClientid(int clientid) {
        this.clientid = clientid;
    }

    public boolean isSex() {
        return sex;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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


    public void setSex(boolean sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "clients{" +
                "id=" + id +
                ", clientid=" + clientid +
                ", realname='" + realname + '\'' +
                ", age=" + age +
                ", sex='" + (sex ? "male" : "female") + '\'' +
                '}';
    }
}
