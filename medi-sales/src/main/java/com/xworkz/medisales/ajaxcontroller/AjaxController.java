package com.xworkz.medisales.ajaxcontroller;


import com.xworkz.medisales.dto.CreateStockDto;
import com.xworkz.medisales.dto.MediSalesDto;
import com.xworkz.medisales.entity.CreateStockEntity;
import com.xworkz.medisales.services.ajaxservice.AjaxService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/")
@Slf4j

public class AjaxController {

    @Autowired
    private AjaxService ajaxService;

    public AjaxController() {
        log.info("invoking in the ajaxcontroller");

    }

    @GetMapping("checkEmail/{email}")
    public String checkEmailExistence(@PathVariable("email") String email) {
        log.info("invoking in the checkemailexist method {} ", email);
        return ajaxService.checkEmail(email);
    }

    @GetMapping("checkPhone/{mobile}")
    public String checkPhoneNum(@PathVariable("mobile") long mobile) {
        log.info("invoking the checkPhoneNum method {}",mobile);
        return ajaxService.checkPhone(mobile);
    }

    @GetMapping("sentOtp/{email}")
    public String sendOtp(@PathVariable("email") String email) {
        log.info("invoking the sendOtp method {} ",email);
        return ajaxService.sendOtp(email);
    }

    @GetMapping("getProducts")
    public List<String> getProducts(Model model) {
        List<CreateStockDto> listOfProducts = ajaxService.getProducts();
        return listOfProducts.stream()
                .map(CreateStockDto::getProductName)
                .collect(Collectors.toList());
    }

    @GetMapping("getContactPersons")
    public List<String> getContactPersons() {
        List<MediSalesDto> listOfContactPersons = ajaxService.getContactPersons();
        return listOfContactPersons.stream()
                .map(MediSalesDto::getContactPerson)
                .collect(Collectors.toList());

    }

    @GetMapping("getStockByProduct")
        public int getStockByProduct(String productName) {

        return ajaxService.getStockByProduct(productName);
    }


}
