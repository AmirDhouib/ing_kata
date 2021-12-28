package com.test.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "address")
    String address;

    @Column(name = "card_number")
    String cardNumber;

    @Column(name="pin")
    String pin;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    List<Account> accounts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id")
    Bank bank;


    public Customer(){
        // used for persistence issue
    }

    /**
     * constructor
     * @param name: customer name
     * @param address: customer address
     * @param cardNumber: customer cardNumber
     * @param pin: customer pin
     */
    public Customer(String name, String address, String cardNumber, String pin) {
        this.name = name;
        this.address = address;
        this.cardNumber = cardNumber;
        this.pin = pin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) &&
                Objects.equals(name, customer.name) &&
                Objects.equals(address, customer.address) &&
                Objects.equals(cardNumber, customer.cardNumber) &&
                Objects.equals(pin, customer.pin);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, address, cardNumber, pin);
    }
}
