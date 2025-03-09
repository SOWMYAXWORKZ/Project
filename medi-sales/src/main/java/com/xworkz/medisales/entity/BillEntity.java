package com.xworkz.medisales.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "bill")

@NamedQueries({
        @NamedQuery(name ="findAllBills",query =  "select b from BillEntity b"),
        @NamedQuery(name = "updateBillNumber",query = "UPDATE  BillEntity b set b.billNo = :newBillNo")

})


public class BillEntity {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "bill_No")
   private Long billNo;;

    @Column(name = "total_amount")
    private double totalAmount;

    @Column(name = "bill_date")
    private LocalDate billDate;
}
