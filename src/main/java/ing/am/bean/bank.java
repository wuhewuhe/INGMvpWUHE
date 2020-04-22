package ing.am.bean;

import javax.persistence.*;

@Entity
public class bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bankid;

    @Column(unique = true, nullable = false)
    private String bankname;

    @Column(nullable = false)
    private String adress;

    public bank() {
    }

    public bank(int bankid, String bankname, String adress) {
        this.bankid = bankid;
        this.bankname = bankname;
        this.adress = adress;
    }

    public Integer getBankid() {
        return bankid;
    }

    public void setBankid(Integer bankid) {
        this.bankid = bankid;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Override
    public String toString() {
        return "bank{" +
                "bankid=" + bankid +
                ", bankname='" + bankname + '\'' +
                ", adress='" + adress + '\'' +
                '}';
    }
}
