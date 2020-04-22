package ing.am.dao;

import ing.am.bean.transaction_log;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionsDAO extends CrudRepository<transaction_log,Integer> {

    //query list transaction by sender id
    @Query("select tran from transaction_log tran where tran.accountid = :accountid")
    public List<transaction_log> findBySenderId(@Param("accountid") Integer accountid);

    //query list transactions by receiver id
    @Query("select tran from transaction_log tran where tran.otherid = :otherid")
    List<transaction_log> findByReceiverId(Integer otherid);

    //query list transactions by transactions type : deposit, withdraw or transfer
    @Query("select tran from transaction_log tran where tran.ta_type = :ta_type")
    List<transaction_log> findByTransType(Integer ta_type);
}
