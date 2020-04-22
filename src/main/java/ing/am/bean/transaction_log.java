package ing.am.bean;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Entity
public class transaction_log {

    public static Map<Integer, String> transType;
    static {
        transType = new HashMap<>();
        transType.put(1, "deposit");
        transType.put(2, "withdraw");
        transType.put(3, "transfer");
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer accountid;

    @Column(nullable = false)
    private Integer otherid;

    @Column(nullable = false)
    private Integer senderbankid;

    @Column(nullable = false)
    private Integer receiverbankid;

    public Integer getSenderbankid() {
        return senderbankid;
    }

    public void setSenderbankid(Integer senderbankid) {
        this.senderbankid = senderbankid;
    }

    public Integer getReceiverbankid() {
        return receiverbankid;
    }

    public void setReceiverbankid(Integer receiverbankid) {
        this.receiverbankid = receiverbankid;
    }

    @Column(nullable = false)
    private BigDecimal tr_money;

    @Column(nullable = false)
    private Timestamp datetime;

    @Column(nullable = false)
    private int ta_type;

    public transaction_log() {
        super();
    }

    public transaction_log(int id, int accoundid, int otherid, BigDecimal tr_money, Timestamp datetime, int ta_type, int senderbankid, int receiverbankid) {
        this.id = id;
        this.accountid = accoundid;
        this.otherid = otherid;
        this.tr_money = tr_money;
        this.datetime = datetime;
        this.ta_type = ta_type;
        this.senderbankid = senderbankid;
        this.receiverbankid = receiverbankid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccoundid() {
        return accountid;
    }

    public void setAccoundid(Integer accoundid) {
        this.accountid = accoundid;
    }

    public Integer getOtherid() {
        return otherid;
    }

    public void setOtherid(Integer otherid) {
        this.otherid = otherid;
    }

    public BigDecimal getTr_money() {
        return tr_money;
    }

    public void setTr_money(BigDecimal tr_money) {
        this.tr_money = tr_money;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    public int getTa_type() {
        return ta_type;
    }

    public void setTa_type(int ta_type) {
        this.ta_type = ta_type;
    }

    @Override
    public String toString() {
        return "transaction_log{" +
                "id=" + id +
                ", accountid=" + accountid +
                ", otherid=" + otherid +
                ", senderbankid=" + senderbankid +
                ", receiverbankid=" + receiverbankid +
                ", tr_money=" + tr_money +
                ", datetime=" + datetime +
                ", ta_type=" + transType.get(ta_type) +
                '}';
    }
}
