package ass10.dtos;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class UserDTO extends DTOBase {

    private String _login;
    private String _password;
    private List<GroupDTO> _groups;

    public UserDTO() {
    }

    public UserDTO(int id, String login, String password) {
        super(id);
        _login = login;
        _password = password;
    }

    public String getLogin() {
        return _login;
    }

    public void setLogin(String login) {
        _login = login;
    }

    public String getPassword() {
        return _password;
    }

    public void setPassword(String password) {
        _password = password;
    }

    public List<GroupDTO> getGroups() {
        return _groups;
    }

    public void setGroups(List<GroupDTO> groups) {
        _groups = groups;
    }

    public void addGroup(GroupDTO group) {
        if (_groups == null) {
            _groups = new LinkedList<>();
        }
        _groups.add(group);
    }

    public void deleteGroup(GroupDTO group) {
        if (_groups != null) {
            _groups.remove(group);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDTO)) return false;
        if (!super.equals(o)) return false;
        UserDTO userDTO = (UserDTO) o;
        return _login.equals(userDTO._login) &&
                _password.equals(userDTO._password) &&
                Objects.equals(_groups, userDTO._groups);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), _login, _password, _groups);
    }
}