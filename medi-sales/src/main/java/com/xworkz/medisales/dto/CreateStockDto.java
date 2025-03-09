package com.xworkz.medisales.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode

public class CreateStockDto {


    private int id;

    @NotNull
    private String productCompany;

    @NotNull
    private Double mrp;

    @NotNull
    private String productName;

    @NotNull
    private String description;

    private String hsn;

    @NotNull
    private int pack; // no of sheets

    private String batchNumber;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate mfgDate;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expDate;

    @NotNull
    private Integer quantity;

    @NotNull
    private Double rate;

    private String createdBy;

    private LocalDate createdDate;

    private String updatedBy;

    private LocalDate updateDate;

    private LocalTime createdTime;


    private Double discount;


    private Double gst;


    private Double amount;

    private String email; // distributor login email through session

}
