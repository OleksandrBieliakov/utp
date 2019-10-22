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

    // The best solution is to impleent the below features as static methods having a list of Employee as the first input argument.
    // In addition the list of arguments may be augmented with parameters required for the given feature.
    // najlepiej zaimplementowaæ poni¿sze metody jako statyczne, gdzie argumentem lista pracowników i odpowiednio dodatkowo to co wymagane w danym punkcie


    // (assignment 03)
    // methods:
    //
    // * search for Employees older than given employee and earning less than him
    //   wyszukaj osoby zatrudnione (Employee), które s¹ starsze od podanej innej zatrudnionej osoby oraz zarabiaj¹ mniej od niej
    static List<Employee> olderThenAndEarnLess(List<Employee> allEmployees, Employee employee) {
        return allEmployees.stream().
                filter(e -> e.isOlder(employee) && e.compareSalary(employee) < 1).
                collect(Collectors.toList());
    }

    //
    // * search for Trainees whose practice length is longer than given number of days and raise their salary by 5%
    //   wyszukaj praktykantów (Trainee), których praktyka jest d³u¿sza od podanej liczby dni,
    //   a nastêpnie podnieœ ich uposa¿enie o 5%
    static List<Trainee> practiceLengthLongerThan(List<Employee> allEmployees, int daysCount) {
        return allEmployees.stream().
                filter(e -> e instanceof Trainee).
                filter(e -> ((Trainee) e).practiceLongerThan(daysCount)).
                map(e -> {
                    e.giveRaise(0.05);
                    return (Trainee) e;
                }).collect(Collectors.toList());
    }

    //
    // * search for Workers whose seniority is longer than given number of months and give them bonus of 300 if their bonus is smaller
    //   wyszukaj pracowników o sta¿u d³u¿szym ni¿ podana liczba miesiêcy,
    //   a nastêpnie przyznaj im premiê w wysokoœci 300 jeœli ich premia jest ni¿sza
    static List<Worker> seniorityLongerThan(List<Employee> allEmployees, int monthCount) {
        return allEmployees.stream().
                filter(e -> e instanceof Worker).
                filter(e -> ((Worker) e).seniorityLongerThanMonths(monthCount)
                        && ((Worker) e).hasBonusSmallerThan(new BigDecimal(300))).
                map(e -> {
                    ((Worker) e).setBonus(new BigDecimal(300));
                    return (Worker) e;
                }).collect(Collectors.toList());
    }

    //
    // * search for Workers whose seniority is between 1 and 3 years and give them raise of salary by 10%
    //   wyszukaj pracowników o sta¿u pomiêdzy 1 a 3 lata i przyznaj im podwy¿kê w wysokoœci 10%
    static List<Worker> seniorityBetweenOneAndThreeYears(List<Employee> allEmployees) {
        return allEmployees.stream().
                filter(e -> e instanceof Worker).
                filter(e -> ((Worker) e).seniorityLongerThanYears(1) && ((Worker) e).seniorityLessThanYears(3)).
                map(e -> {
                    e.giveRaise(0.1);
                    return (Worker) e;
                }).collect(Collectors.toList());
    }

    //
    // * search for Workers whose seniority is longer than the seniority of a given employee and earn less than him and align their salary with the given employee
    //   wyszukaj pracowników o sta¿u d³u¿szym ni¿ sta¿ podanego pracownika i którzy zarabiaj¹ mniej od niego,
    //   nastêpnie zrównaj ich wynagrodzenie z wynagrodzeniem danego pracownika
    static List<Worker> seniorityLongerThan(List<Employee> allEmployees, Employee employee) {
        return null;
    }

    //
    // * search for Workers whose seniority is between 2 and 4 years and whose age is greater than given number of years
    //   wyszukaj pracowników o sta¿u pomiêdzy 2 i 4 lata i starszych ni¿ podana liczba lat
    static List<Worker> seniorityBetweenTwoAndFourYearsAndAgeGreaterThan(List<Employee> allEmployees, int age) {
        return null;
    }

}