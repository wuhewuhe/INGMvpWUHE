package ing.am.bean;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountid;

    @Column(unique = true, nullable = false)
    private int cardnumber;

    @Column(nullable = false)
    private int password;

    private BigDecimal balance;

    @Column(nullable = false)
    private int bankid;

    @Column(nullable = false)
    private int clientid;

    public account() {
        super();
    }

    public account(int accountid, int cardnumber, int password, BigDecimal balance, int userid, int bankid) {
        this.accountid = accountid;
        this.cardnumber = cardnumber;
        this.password = password;
        this.balance = balance;
        this.clientid = clientid;
        this.bankid = bankid;
    }

    public Integer getAccountid() {
        return accountid;
    }

    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

    public int getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(int cardnumber) {
        this.cardnumber = cardnumber;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public int getBankid() {
        return bankid;
    }

    public void setBankid(int bankid) {
        this.bankid = bankid;
    }

    public int getClientid() {
        return clientid;
    }

    public void setClientid(int clientid) {
        this.clientid = clientid;
    }

    @Override
    public String toString() {
        return "account{" +
                "accountid=" + accountid +
                ", cardnumber='" + cardnumber + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", bankid=" + bankid +
                ", clientid=" + clientid +
                '}';
    }
}
