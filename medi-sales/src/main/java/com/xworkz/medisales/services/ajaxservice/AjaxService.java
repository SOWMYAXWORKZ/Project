package com.xworkz.medisales.services.ajaxservice;

import com.xworkz.medisales.dto.CreateStockDto;
import com.xworkz.medisales.dto.MediSalesDto;

import java.util.List;

public interface AjaxService {

    String checkEmail(String email);

    String checkPhone(long mobile);

    String sendOtp(String email);

    List<CreateStockDto> getProducts();

    List<MediSalesDto> getContactPersons();

    int getStockByProduct(String productName);
}
