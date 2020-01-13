package ass10;

import ass10.dtos.UserDTO;
import ass10.repositories.IUserRepository;
import org.junit.Test;

public final class UserRepositoryTest extends RepositoryTestBase<UserDTO, IUserRepository> {

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
	protected IUserRepository Create() {
		throw new UnimplementedException();
	}
}