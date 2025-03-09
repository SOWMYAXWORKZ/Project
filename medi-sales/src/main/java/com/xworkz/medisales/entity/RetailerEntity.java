package com.xworkz.medisales.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "retailer")

@NamedQueries({
        @NamedQuery(name = "getAllRetailers",query = "from RetailerEntity retailerentity"),
        @NamedQuery(name = "deleteRetailerById", query  = "delete from RetailerEntity r where r.id= :id")
})
public class RetailerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "retailer_name")
    private String retailerName;

/*    @Column(name = "retailer_company")
    private String retailerCompanyName;*/

    @Column(name = "retailer_phone")
    private Long retailerPhone;

    @Column(name = "retailer_email")
    private String retailerEmail;

    @Column(name = "gst_no")
    private String retailerGstNumber;

    @Column(name = "retailer_address")
    private String retailerAddress;

}
