package ass10;

import ass10.dtos.GroupDTO;
import ass10.repositories.IGroupRepository;
import org.junit.Test;

public class GroupRepositoryTest extends RepositoryTestBase<GroupDTO, IGroupRepository> {

	@Test
	public void add() {
	}

	@Test
	public void update() {
	}
	
	@Test
	public void addOrUpdate() {
	}

	@Test
	public void delete() {
	}

	@Test
	public void findById() {
	}
	
	@Test
	public void findByName() {
	}

	@Override
	protected IGroupRepository Create() {
		throw new UnimplementedException();
	}
}