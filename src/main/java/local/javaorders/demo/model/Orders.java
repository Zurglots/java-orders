package local.javaorders.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Orders
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ordnum;

    @Column(unique = true,
            nullable = false)
    private double ordamount;


    private double advanceamount;
    private String orddescription;

    @ManyToOne
    @JoinColumn(name = "custcode",
                nullable = false)
    @JsonIgnoreProperties("orders")
    private Customers customer;

    public Orders() // JPA requires default constructor first.
    {
    }

    public Orders(double ordamount, double advanceamount, String orddescription, Customers customer) {
        this.ordamount = ordamount;
        this.advanceamount = advanceamount;
        this.orddescription = orddescription;
        this.customer = customer;
    }

    public long getOrdnum() {
        return ordnum;
    }

    public void setOrdnum(long ordnum) {
        this.ordnum = ordnum;
    }

    public double getOrdamount() {
        return ordamount;
    }

    public void setOrdamount(double ordamount) {
        this.ordamount = ordamount;
    }

    public double getAdvanceamount() {
        return advanceamount;
    }

    public void setAdvanceamount(double advanceamount) {
        this.advanceamount = advanceamount;
    }

    public String getOrddescription() {
        return orddescription;
    }

    public void setOrddescription(String orddescription) {
        this.orddescription = orddescription;
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }
}

