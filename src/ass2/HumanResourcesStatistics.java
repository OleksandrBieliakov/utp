package ass2;

import ass2.employee.Employee;
import ass2.employee.Manager;
import ass2.employee.Trainee;
import ass2.employee.Worker;
import ass2.payroll.PayrollEntry;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


final class HumanResourcesStatistics {

    static List<PayrollEntry> payroll(List<Employee> employees) {
        return employees.stream().
                map(e -> e.getClass().getName().equals(Trainee.class.getName()) ?
                        new PayrollEntry(e, e.getSalary(), null) :
                        new PayrollEntry(e, e.getSalary(), ((Worker) e).getBonus())).
                collect(Collectors.toList());
    }

    static List<PayrollEntry> subordinatesPayroll(Manager manager) {
        return payroll(manager.getSubordinates());
    }

    static BigDecimal bonusTotal(List<Employee> employees) {
        return employees.stream().filter(e -> !e.getClass().getName().equals(Trainee.class.getName())).
                map(e -> ((Worker) e).getBonus()).reduce(new BigDecimal(0), BigDecimal::add);
    }

    static Employee longestSeniority(List<Employee> employees) {
        return null;
    }

    /// ...
    // rest of the methods specified in the assignment description
}