package ass2.employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class Manager extends Worker {

    // attributes
    // * subordinates (a list of immediate subordinates)
    // * all subordinates (a list of subordinates in all hierarchy)

    private List<Employee> subordinates;

    public Manager(String firstName, String surname, LocalDate birthDate, BigDecimal salary, Manager manager,
                   LocalDate employmentDate, BigDecimal bonus, List<Employee> subordinates) {
        super(firstName, surname, birthDate, salary, manager, employmentDate, bonus);
        this.subordinates = subordinates;
    }

    public List<Employee> getSubordinates() {
        return subordinates;
    }

    public List<Employee> getSubordinatesAll() {
        List<Employee> subordinatesAll = new ArrayList<>();
        if (subordinates == null) return subordinatesAll;
        subordinatesAll.addAll(subordinates);
        for (Employee sub : subordinates) {
            if (sub.getClass().getName().equals(Manager.class.getName())) {
                subordinatesAll.addAll(((Manager) sub).getSubordinatesAll());
            }
        }
        return subordinatesAll;
    }

    public void setSubordinates(List<Employee> subordinates) {
        this.subordinates = subordinates;
    }

}