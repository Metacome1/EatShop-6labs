package barBossHouse;

import java.time.LocalDate;
import java.time.Period;

final public class Customer {
    private String firstName;
    private String secondName;

    public LocalDate getBirthDate() {
        return birthDate;
    }

    private LocalDate birthDate;
    private Address address;
    public final static Customer MATURE_UNKNOWN_CUSTOMER = new Customer(LocalDate.now().minusYears(21));
    public final static Customer NOT_MATURE_UNKNOWN_CUSTOMER = new Customer(LocalDate.now().minusYears(14));

    public Customer() {
        this.firstName = "";
        this.secondName = "";
        this.birthDate = LocalDate.now();
        this.address = Address.EMPTY_ADDRESS;
    }

    public Customer(LocalDate birthDate) {
        if(birthDate.isAfter(LocalDate.now())) throw new IllegalArgumentException("Человек из будущего");
        this.firstName = "";
        this.secondName = "";
        this.birthDate = birthDate;
        this.address = Address.EMPTY_ADDRESS;
    }

    public Customer(String firstName, String secondName, LocalDate birthDate, Address address) {
        if(birthDate.isAfter(LocalDate.now())) throw new IllegalArgumentException("Человек из будущего");
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthDate = birthDate;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public int getAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();

    }

    public Address getAddress() {
        return address;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        return builder.append(getClass().getSimpleName())
                .append(":").append(firstName.isEmpty() ? "" :firstName + " ")
                .append(secondName.isEmpty() ? "":secondName)
                .append(", ").append(getAge()).append(" ")
                .append(address.toString()).toString().trim();
    }

    @Override
    public boolean equals(Object obj){
        if (obj == this)
            return true;

        if (obj == null || obj.getClass() != this.getClass())
            return false;

        Customer customer = (Customer) obj;

        return (firstName.equals(customer.getFirstName()) & secondName.equals(customer.getSecondName()) & address.equals(customer.getAddress()));
    }

    @Override
    public int hashCode(){
        return firstName.hashCode()
                ^ secondName.hashCode()
                ^address.hashCode();
    }
}
