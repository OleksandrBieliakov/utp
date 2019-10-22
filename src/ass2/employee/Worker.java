package ass2.employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class Worker extends Employee {

    // attributes
    // * employment date
    // * bonus
    private final LocalDate employmentDate;
    private BigDecimal bonus;
    private boolean hasBonus = false;

    public Worker(String firstName, String surname, LocalDate birthDate, BigDecimal salary, Manager manager,
                  LocalDate employmentDate, BigDecimal bonus) {
        super(firstName, surname, birthDate, salary, manager);
        this.employmentDate = employmentDate;
        this.bonus = bonus != null ? bonus : new BigDecimal(0);
        if (bonus != null && !bonus.equals(new BigDecimal(0))) hasBonus = true;
    }

    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus != null ? bonus : new BigDecimal(0);
        if (bonus != null && !bonus.equals(new BigDecimal(0.))) hasBonus = true;
    }

    public boolean hasBonus() {
        return hasBonus;
    }

    public long worksDays() {
        return ChronoUnit.DAYS.between(employmentDate, LocalDate.now());
    }

    public Period worksPeriod() {
        return Period.between(employmentDate, LocalDate.now());
    }

    public boolean worksLongerThan(Worker other) {
        return employmentDate.compareTo(other.employmentDate) < 0;
    }

    public boolean worksLessThan(Worker other) {
        return employmentDate.compareTo(other.employmentDate) > 0;
    }
    // (assignment 03)
    // attributes:
    // * has bonus
    //
    // methods:
    // * seniority is longer than given number of years (seniority - sta¿)
    // * seniority is longer than given number of months
    // * has bonus greater than given amount of money

    public boolean seniorityLongerThanYears(int years) {
        return employmentDate.compareTo(LocalDate.now().minusYears(years)) < 0;
    }

    public boolean seniorityLessThanYears(int years) {
        return employmentDate.compareTo(LocalDate.now().minusYears(years)) > 0;
    }

    public boolean seniorityLongerThanMonths(int months) {
        return employmentDate.compareTo(LocalDate.now().minusMonths(months)) < 0;
    }

    public boolean seniorityLessThanMonths(int months) {
        return employmentDate.compareTo(LocalDate.now().minusMonths(months)) > 0;
    }

    public boolean hasBonusGreaterThan(BigDecimal sum) {
        return hasBonus && bonus.compareTo(sum) > 0;
    }

    public boolean hasBonusSmallerThan(BigDecimal sum) {
        return !hasBonus || bonus.compareTo(sum) < 0;
    }

}