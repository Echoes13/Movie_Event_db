package wad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.GroupAccount;

public interface GroupRepository extends JpaRepository<GroupAccount, Long> {

}