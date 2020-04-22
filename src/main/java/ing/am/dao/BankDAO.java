package ing.am.dao;

import ing.am.bean.bank;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankDAO extends CrudRepository<bank,Integer> {
}
