package com.xworkz.medisales.repository.distributorRepo;

import com.xworkz.medisales.dto.CreateStockDto;
import com.xworkz.medisales.entity.CreateStockEntity;

import java.util.List;

public interface CreateStockRepo {
    boolean addStock(CreateStockEntity createStockEntity);

    CreateStockDto getStockById(int id);

    List<CreateStockDto> getAllStockDetails();

    CreateStockEntity getBillByProductName(String productName);

    List<CreateStockEntity> getProducts();

    int getStockByProduct(String productName);

    List<CreateStockDto> getStockDetailsByDistributor(String loggedInEmail);

    List<CreateStockEntity> findAllStockBillDetails();
}
