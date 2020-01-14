package ass10.dtos;

import java.util.Objects;

public abstract class DTOBase {

    private int _id;

    protected DTOBase() {
    }

    protected DTOBase(int id) {
        _id = id;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        _id = id;
    }

    public boolean hasExistingId() {
        return getId() > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DTOBase)) return false;
        DTOBase dtoBase = (DTOBase) o;
        return _id == dtoBase._id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id);
    }
}