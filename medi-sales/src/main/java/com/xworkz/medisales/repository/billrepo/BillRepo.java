package com.xworkz.medisales.repository.billrepo;

import com.xworkz.medisales.entity.BillEntity;

import java.util.List;

public interface BillRepo {
    void save(BillEntity billEntity);

    Long findBillNo();

    List<BillEntity> findAll();


    void updateBillNumber(Long newBillNo);
}
