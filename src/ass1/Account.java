package ass1;

public class Account implements IAggregable<Account, Double>, IDeeplyCloneable<Account> {

    private Person owner;
    private Double sum;

    Account(Person owner, Double sum) {
        this.owner = owner;
        this.sum = sum;
    }

    Person getOwner() {
        return owner;
    }

    Double getSum() {
        return sum;
    }

    public Double aggregate(Double intermediateResult) {
        return intermediateResult == null ? sum : intermediateResult + sum;
    }

    public Account deepClone() {
        return new Account(owner.deepClone(), sum);
    }
}