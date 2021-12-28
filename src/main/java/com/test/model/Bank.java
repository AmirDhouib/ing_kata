package com.test.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "bank")
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_id")
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "code")
    String code;

    @Column(name = "address")
    String address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bank")
    List<Customer> customers;

    public Bank(){
        // uses for persistence issue
    }

    /**
     * constructor
     * @param name: bank name
     * @param code: bank code
     * @param address: bank address
     */
    public Bank(String name, String code, String address) {
        this.name = name;
        this.code = code;
        this.address = address;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return Objects.equals(id, bank.id) &&
                Objects.equals(name, bank.name) &&
                Objects.equals(code, bank.code) &&
                Objects.equals(address, bank.address);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, code, address);
    }
}
