package volz.vanessa.codingtask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import volz.vanessa.codingtask.model.Data;

@Repository
public interface DataRepository extends JpaRepository<Data, Long> {
	
}
