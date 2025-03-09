package com.xworkz.medisales.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode

@Entity
@Table(name = "create_stock")
@NamedQueries({
        @NamedQuery(name = "getAllStock",query = "from CreateStockEntity CreateStockEntity"),
        @NamedQuery(name = "getBillByProductName", query= "select c from CreateStockEntity c where c.productName=:productName"),
        @NamedQuery(name = "getProductByName",query="select c from CreateStockEntity c where c.productName = :productName"),
        @NamedQuery(name = "getProducts",query = "from CreateStockEntity createStockEntity"),
        @NamedQuery(name = "getStockByProduct", query = "select c from CreateStockEntity c where c.productName = :productName"),
        @NamedQuery(name = "getStockDetailsByDistributor", query = "select c from CreateStockEntity c where c.email = :email"),
        @NamedQuery(name = "fetchStockByEmail", query = "select s from  CreateStockEntity s where s.email = :email"),
        @NamedQuery(name = "findAllStockBillDetails",query = "select b from CreateStockEntity b")

})

public class CreateStockEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "product_company")
    private String productCompany;

    @Column(name = "mrp")
   // @Positive(message = "MRP must be positive")
    private double mrp;    // vendor to customer

    @Column(name = "product_name")
    private String productName;

    @Column(name = "description")
    private String description;

    @Column(name = "hsn")
    private String hsn;

    @Column(name = "pack")
    private int pack ; //no of sheets

    @Column(name = "batch_number")
    private String batchNumber;

    @Column(name = "mfg_date")
    private LocalDate mfgDate;

    @Column(name = "exp_date")
    private LocalDate expDate;

    @Column(name = "qty")
    private int quantity;

    @Column(name = "rate")
    private double rate;   // distributor to vendor  *(wholesale price the distributor buys)

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_on")
    private LocalDate createdDate;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_on")
    private LocalDate updateDate;

    @Column(name = "created_on_time")
    private Time createdTime;

    @Column(name = "discount")
    private double discount;

    @Column(name = "gst")
    private double gst;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "email")
    private String email;

}
