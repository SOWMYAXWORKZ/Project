package com.xworkz.medisales.dto;


import com.xworkz.medisales.constants.BusinessType;
import com.xworkz.medisales.constants.Gender;
import lombok.*;

import javax.validation.constraints.*;
import java.sql.Time;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode


public class MediSalesDto {

    private int id;


    @NotEmpty
    @NotNull
    @Size(min = 3, max = 10, message = "companyName must be of 3 to 10 char")
    private String companyName;

    @NotEmpty
    @NotNull
    @Size(min = 3, max = 30, message = " contactPerson must be of 3 to 30 char")
    private String contactPerson;

    @Email(message = "Please provide a valid email address, e.g., example@gmail.com")
    @NotEmpty
    @Size
    private String email;

    private Gender gender;


    @NotNull(message = "Mobile number cannot be null")
    @Min(value = 1000000000L, message = "Mobile number must be 10 digits")
    @Max(value = 9999999999L, message = "Mobile number must be 10 digits")
    private long mobile;

    private BusinessType businesstype;

    @NotEmpty
    @NotNull
    @Size(min = 3, max = 50, message = "Enter the proper address")
    private String address;


    @NotNull(message = "Pincode cannot be null")
    @Min(value = 100000, message = "Pincode must be exactly 6 digits")
    @Max(value = 999999, message = "Pincode must be exactly 6 digits")
    private Integer pincode;

    @NotNull
    @Size(min = 8, max= 16 ,message = "The password must be 8 to 16 with 1 uppercase, 1 lowercase and with #$... requirements ")
    private String password;

    @NotNull
    @Size(min = 8, max=16, message = "the Password don't Match")
    private String confirmPassword;

    private String createdBy;

    private LocalDate createdDate;

    private String updatedBy;

    private LocalDate updateDate;

    private Time createdTime;

    private String fileName;

    private String fileType;

    private String otp;

    private int attempt;




}
