package com.xworkz.medisales.dto;

import lombok.*;

import javax.validation.constraints.*;


@Setter
@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@NoArgsConstructor

public class RetailerDto {

    private int id;

    @NotNull
    @Size(min = 3, max = 15, message = "The Length of the name must be minimum of 3 letters and max of 15 letters.")
    private String retailerName;

    /*@NotNull
    @Size(min = 3, max = 15, message = "The length of the Company must be  min of 3 letters and max of 15 letters" )
    private String retailerCompanyName;*/

    @NotNull(message = "Mobile number cannot be null")
    @Min(value = 1000000000L, message = "Mobile number must be 10 digits")
    @Max(value = 9999999999L, message = "Mobile number must be 10 digits")
    private Long retailerPhone;

    @Email(message = "Please provide a valid email address, e.g., example@gmail.com")
    @NotEmpty
    @Size
    private String retailerEmail;

    @NotNull
    @Size(min = 15,max = 15,message = "Please enter the correct Gst format")
    private String retailerGstNumber;

    @NotEmpty
    @NotNull
    @Size(min = 3, max = 50, message = "Enter the proper address")
    private String retailerAddress;

}
