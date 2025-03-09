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
@Table(name = "vendor")

@NamedQueries({
        @NamedQuery(name = "getAllVendors",query = "from VendorEntity vendorentity"),
        @NamedQuery(name = "deleteVendorById", query  = "delete from VendorEntity v where v.id= :id")
})

public class VendorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "vendor_name")
    private String vendorName;

   /* @Column(name = "vendor_company")
    private String vendorCompanyName;*/

    @Column(name = "vendor_phone")
    private Long vendorPhone;

    @Column(name = "vendor_email")
    private String vendorEmail;

    @Column(name = "gst_no")
    private String vendorGstNumber;

    @Column(name = "vendor_address")
    private String vendorAddress;


    @ManyToOne
    @JoinColumn(name = "medisales_id", nullable = false)
    private MediSalesEntity mediSalesEntity;


}
