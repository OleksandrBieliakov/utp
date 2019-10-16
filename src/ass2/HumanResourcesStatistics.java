package ass2;

import ass2.employee.Employee;
import ass2.employee.Manager;
import ass2.employee.Trainee;
import ass2.employee.Worker;
import ass2.payroll.PayrollEntry;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


final class HumanResourcesStatistics {

    static List<PayrollEntry> payroll(List<Employee> employees) {
        return employees.stream().
                map(e -> new PayrollEntry(e, e.getSalary(), e instanceof Worker ? ((Worker) e).getBonus() : null)).
                collect(Collectors.toList());
    }

    static List<PayrollEntry> subordinatesPayroll(Manager manager) {
        return payroll(manager.getSubordinates());
    }

    static BigDecimal bonusTotal(List<Employee> employees) {
        return employees.stream().
                filter(e -> !(e instanceof Trainee)).
                map(e -> ((Worker) e).getBonus()).
                reduce(new BigDecimal(0), BigDecimal::add);
    }

    static Employee longestSeniority(List<Employee> employees) {
        if (employees == null || employees.size() == 0) return null;
        return employees.stream()
                .filter(e -> e instanceof Worker)
                .max(Comparator.comparing(w -> ((Worker) w).worksDays()))
                .orElse(null);
    }

    static BigDecimal biggestSalary(List<Employee> employees) {
        if (employees == null || employees.size() == 0) return null;
        return employees.stream().
                reduce(employees.get(0), (part, next) -> part.getSalary().
                        compareTo(next.getSalary()) >= 0 ? part : next).
                getSalary();
    }

    static BigDecimal biggestSalaryWithBonus(List<Employee> employees) {
        if (employees == null || employees.size() == 0) return null;
        return payroll(employees).stream().
                map(PayrollEntry::getSalaryPlusBonus).
                max(Comparator.comparing(p -> p)).
                orElse(null);
    }

    static List<Employee> findSubordinatesByName(Manager manager, String key) {
        return manager.getSubordinates().stream().
                filter(e -> e.getSurname().indexOf(key) == 0).
                collect(Collectors.toList());
    }

    static List<Employee> earnMoreThan(List<Employee> employees, BigDecimal key) {
        return payroll(employees).stream().
                filter(e -> e.getSalaryPlusBonus().compareTo(key) > 0).
                map(PayrollEntry::getEmployee).
                collect(Collectors.toList());
    }
    /// ...
    // rest of the methods specified in the assignment description
}