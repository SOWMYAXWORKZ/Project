package com.xworkz.medisales.entity;

import com.xworkz.medisales.constants.BusinessType;
import com.xworkz.medisales.constants.Gender;
import lombok.*;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode

@Entity
@Table(name ="medisales")
@NamedQueries({
        @NamedQuery(name = "checkEmail", query = "SELECT COUNT(m) FROM MediSalesEntity m WHERE m.email = :email"),
        @NamedQuery(name = "getEntityByEmail",query = "select m from MediSalesEntity m  where m.email=:email"),
        @NamedQuery(name = "updatePasswordByEmail", query = "update MediSalesEntity m set m.password = :password, m.confirmPassword = confirmPassword where m.email=:email"),
        @NamedQuery(name = "getContactPersons",query = "from MediSalesEntity mediSalesEntity")

})

public class MediSalesEntity {


    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "medisales_id")
    private int id;

    @Column(name = "companyname")
    private String companyName;

    @Column(name = "contactperson")
    private String contactPerson;


    @Column(name = "email")
    private String email;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "mobile")
    private long mobile;

    @Column(name = "businesstype")
    @Enumerated(EnumType.STRING)
    private BusinessType businesstype;

    @Column(name = "address")
    private String address;

    @Column(name = "pincode")
    private Integer pincode;

    @Column(name = "password")
    private String password;

    @Column(name = "confirmpassword")
    private String confirmPassword;

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

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "otp")
    private String otp;

    @Column(name = "attempt")
    private int attempt;

   // @OneToMany-listofvendors,listofretailers.

    @OneToMany(mappedBy = "mediSalesEntity", cascade = CascadeType.ALL)
    private List<VendorEntity> vendorEntityList = new ArrayList<>();

}
