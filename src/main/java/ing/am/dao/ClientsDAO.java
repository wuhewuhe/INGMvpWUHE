package ing.am.dao;

import ing.am.bean.clients;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientsDAO extends CrudRepository<clients,Integer> {
}
