package ass10.repositories;

import ass10.dtos.GroupDTO;

import java.util.List;

public interface IGroupRepository extends IRepository<GroupDTO> {

	List<GroupDTO> findByName(String name);
}