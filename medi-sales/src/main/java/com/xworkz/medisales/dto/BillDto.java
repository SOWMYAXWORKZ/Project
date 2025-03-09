package com.xworkz.medisales.dto;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode

public class BillDto {

    private int id;

    private Long billNo;

    private double totalAmount;

    private LocalDate billDate;

    public BillDto(Long billNumber, LocalDate billDate) {
        this.billNo = billNumber;
        this.billDate = billDate;
    }
}
