package ass2;

import ass2.employee.Employee;
import ass2.employee.Manager;
import ass2.employee.Trainee;
import ass2.employee.Worker;
import ass2.payroll.PayrollEntry;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HumanResourcesStatisticsTest {

    // Create a HR structure which resembles the below one:
    //
    // Director (an instance of Manager class (Director does not have a manager)
    //   |- Manager01
    //        |- Worker
    //        |- Worker
    //        |- Trainee
    //        |- ...
    //   |- Manager02
    //        |- ...
    //   |- ...
    //   |- Worker
    //   |- Worker
    //   |- Trainee

    private static List<Employee> _allEmployees; // all employees ---  i.e. Workers, Trainees and their Managers and top Director (also an instance of Manager class)

    private static Manager director;
    private static Manager manager1;
    private static Worker worker1;
    private static Worker worker2;
    private static Trainee trainee1;
    private static Manager manager2;
    private static Worker worker3;
    private static Manager manager3;
    private static Worker worker4;
    private static Worker worker5;
    private static Worker worker6;
    private static Worker worker7;
    private static Worker worker8;
    private static Trainee trainee2;
    private static Trainee trainee3;
    private static Manager manager4;
    private static Trainee trainee4;
    private static Trainee trainee5;
    private static Trainee trainee6;
    private static Worker worker9;

    @Before
    public void before() {
        _allEmployees = new ArrayList<>();

        List<Employee> directorSubs = new ArrayList<>();
        List<Employee> manager1Sub = new ArrayList<>();
        List<Employee> manager2Sub = new ArrayList<>();
        List<Employee> manager3Sub = new ArrayList<>();
        List<Employee> manager4Sub = new ArrayList<>();

        manager1 = new Manager("Eon", "Kill", LocalDate.of(1984, 2, 20),
                new BigDecimal(5000), director, LocalDate.of(2016, 4, 2),
                new BigDecimal(500), manager1Sub);
        directorSubs.add(manager1);
        _allEmployees.add(manager1);

        manager2 = new Manager("Goooki", "Pastor", LocalDate.of(1984, 2, 20),
                new BigDecimal(5000), director, LocalDate.of(2016, 4, 2),
                new BigDecimal(500), manager2Sub);
        directorSubs.add(manager2);
        _allEmployees.add(manager2);

        manager3 = new Manager("Folo", "Dolo", LocalDate.of(1984, 2, 20),
                new BigDecimal(5000), director, LocalDate.of(2016, 4, 2),
                new BigDecimal(500), manager3Sub);
        directorSubs.add(manager3);
        _allEmployees.add(manager3);

        manager4 = new Manager("Qwe", "Ewq", LocalDate.of(1984, 2, 20),
                new BigDecimal(5000), director, LocalDate.of(2016, 4, 2),
                new BigDecimal(500), manager4Sub);
        directorSubs.add(manager4);
        _allEmployees.add(manager4);

        worker1 = new Worker("Elon", "Pill", LocalDate.of(1984, 2, 20),
                new BigDecimal(3000), manager1, LocalDate.of(2016, 4, 2),
                new BigDecimal(500));
        manager1Sub.add(worker1);
        _allEmployees.add(worker1);

        worker2 = new Worker("Hop", "Dog", LocalDate.of(1984, 2, 20),
                new BigDecimal(3000), manager1, LocalDate.of(2016, 4, 2),
                new BigDecimal(500));
        manager1Sub.add(worker2);
        _allEmployees.add(worker2);

        trainee1 = new Trainee("Hop", "Dog", LocalDate.of(1984, 2, 20),
                new BigDecimal(2000), manager1, LocalDate.of(2016, 4, 2), 30);
        manager1Sub.add(trainee1);
        _allEmployees.add(trainee1);

        worker3 = new Worker("Elon", "Pill", LocalDate.of(1984, 2, 20),
                new BigDecimal(3000), manager2, LocalDate.of(2016, 4, 2),
                new BigDecimal(500));
        manager2Sub.add(worker3);
        _allEmployees.add(worker3);


        worker4 = new Worker("Gosha", "Fock", LocalDate.of(1984, 2, 20),
                new BigDecimal(3000), manager3, LocalDate.of(2016, 4, 2),
                new BigDecimal(500));
        manager3Sub.add(worker4);
        _allEmployees.add(worker4);

        worker5 = new Worker("Lopa", "Rapo", LocalDate.of(1984, 2, 20),
                new BigDecimal(3000), manager3, LocalDate.of(2016, 4, 2),
                new BigDecimal(500));
        manager3Sub.add(worker5);
        _allEmployees.add(worker5);

        worker6 = new Worker("Hoho", "Ichopla", LocalDate.of(1984, 2, 20),
                new BigDecimal(3000), manager3, LocalDate.of(2016, 4, 2),
                new BigDecimal(500));
        manager3Sub.add(worker6);
        _allEmployees.add(worker6);

        worker7 = new Worker("Ilon", "Rill", LocalDate.of(1984, 2, 20),
                new BigDecimal(3000), manager3, LocalDate.of(2016, 4, 2),
                new BigDecimal(500));
        manager3Sub.add(worker7);
        _allEmployees.add(worker7);

        worker8 = new Worker("Mom", "Dog", LocalDate.of(1984, 2, 20),
                new BigDecimal(4000), director, LocalDate.of(2016, 4, 2),
                new BigDecimal(1000));
        directorSubs.add(worker8);
        _allEmployees.add(worker8);

        trainee2 = new Trainee("Dilop", "Kogot", LocalDate.of(1984, 2, 20),
                new BigDecimal(3000), director, LocalDate.of(2016, 4, 2), 30);
        directorSubs.add(trainee2);
        _allEmployees.add(trainee2);

        trainee3 = new Trainee("Solo", "Tasheer", LocalDate.of(1984, 2, 20),
                new BigDecimal(3000), director, LocalDate.of(2016, 4, 2), 30);
        directorSubs.add(trainee3);
        _allEmployees.add(trainee3);

        trainee4 = new Trainee("G", "G", LocalDate.of(1984, 2, 20),
                new BigDecimal(2000), manager4, LocalDate.of(2016, 4, 2), 30);
        manager4Sub.add(trainee4);
        _allEmployees.add(trainee4);

        trainee5 = new Trainee("G", "G", LocalDate.of(1984, 2, 20),
                new BigDecimal(2000), manager4, LocalDate.of(2016, 4, 2), 30);
        manager4Sub.add(trainee5);
        _allEmployees.add(trainee5);

        trainee6 = new Trainee("Gee", "Gaas", LocalDate.of(1984, 2, 20),
                new BigDecimal(2000), manager4, LocalDate.of(2016, 4, 2), 30);
        manager4Sub.add(trainee6);
        _allEmployees.add(trainee6);

        worker9 = new Worker("Popol", "Girus", LocalDate.of(1984, 2, 20),
                new BigDecimal(3000), manager4, LocalDate.of(2016, 4, 2),
                new BigDecimal(500));
        manager4Sub.add(worker9);
        _allEmployees.add(worker9);

        director = new Manager("Bob", "Hill", LocalDate.of(1954, 2, 20),
                new BigDecimal(10000), null, LocalDate.of(2008, 4, 2),
                new BigDecimal(5000), directorSubs);
        _allEmployees.add(director);

        Assert.assertEquals(20, _allEmployees.size());
        Assert.assertEquals(19, director.getSubordinatesAll().size());
        Assert.assertEquals(3, manager1.getSubordinatesAll().size());
        Assert.assertEquals(1, manager2.getSubordinatesAll().size());
        Assert.assertEquals(4, manager3.getSubordinatesAll().size());
        Assert.assertEquals(4, manager4.getSubordinatesAll().size());
        Assert.assertEquals(7, director.getSubordinates().size());
        Assert.assertEquals(manager1.getSubordinatesAll().size() + manager2.getSubordinatesAll().size() +
                        manager3.getSubordinatesAll().size() + manager4.getSubordinatesAll().size(),
                _allEmployees.size() - 1 - director.getSubordinates().size());
    }

    @Test
    public void payroll() {
        List<PayrollEntry> payrolls = HumanResourcesStatistics.payroll(_allEmployees);
        Assert.assertEquals(20, payrolls.size());
        Assert.assertEquals(new BigDecimal(84000),
                payrolls.stream().map(PayrollEntry::getBigDecimal).reduce(new BigDecimal(0), BigDecimal::add));
    }

    @Test
    public void subordinatesPayroll() {
        List<PayrollEntry> payrolls = HumanResourcesStatistics.subordinatesPayroll(manager1);
        Assert.assertEquals(3, payrolls.size());
        Assert.assertEquals(new BigDecimal(9000),
                payrolls.stream().map(PayrollEntry::getBigDecimal).reduce(new BigDecimal(0), BigDecimal::add));
    }

    @Test
    public void bonusTotal() {
        Assert.assertEquals(new BigDecimal(12000), HumanResourcesStatistics.bonusTotal(_allEmployees));
    }

    /// ...
    // rest of the methods specified in the assignment description
}