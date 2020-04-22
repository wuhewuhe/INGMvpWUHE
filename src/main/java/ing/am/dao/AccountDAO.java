package ing.am.dao;

import ing.am.bean.account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface AccountDAO extends CrudRepository<account, Integer> {
    //query list accounts by bankid
    @Query("select acc from account acc where acc.bankid = :bankid")
    List<account> listbybank(int bankid);

    //query list accounts by userid
    @Query("select acc from account acc where acc.userid = :userid")
    List<account> listbyuserid(int userid);

    //query list accounts by userid
    @Query("select acc from account acc where acc.accountid = :accountid")
    account findByAccountid(int accountid);

    //query solde
    @Query("select acc.balance from account acc where acc.accountid = :accountid")
    BigDecimal findSoldeByAccountid(int accountid);
}
