package wad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.GroupAccount;

//Käyttäjätilirepository

public interface GroupRepository extends JpaRepository<GroupAccount, Long> {
    public GroupAccount findByUsername(String username);
}