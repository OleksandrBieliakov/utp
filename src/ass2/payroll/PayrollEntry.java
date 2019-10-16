package ass2.payroll;

import ass2.employee.Employee;

import java.math.BigDecimal;

public final class PayrollEntry {

    private final Employee employee;
    private final BigDecimal salaryPlusBonus;

    public PayrollEntry(Employee employee, BigDecimal salary, BigDecimal bonus) {
        this.employee = employee;
        if (salary != null && bonus != null)
            salaryPlusBonus = salary.add(bonus); // validate whether salary and bonus are not null
        else if (salary == null) salaryPlusBonus = bonus;
        else salaryPlusBonus = salary;
    }

    public Employee getEmployee() {
        return employee;
    }

    public BigDecimal getSalaryPlusBonus() {
        return salaryPlusBonus;
    }

    @Override
    public String toString() {
        return employee.getFirstName() + " " + employee.getSurname() + ": " + salaryPlusBonus;
    }

}