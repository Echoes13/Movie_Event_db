package eventdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import eventdb.domain.GroupAccount;

public interface GroupRepository extends JpaRepository<GroupAccount, Long> {

}