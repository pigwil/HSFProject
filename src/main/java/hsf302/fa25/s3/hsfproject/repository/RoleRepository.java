package hsf302.fa25.s3.hsfproject.repository;

import hsf302.fa25.s3.hsfproject.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    public Role findByRoleName(String roleName);
}
