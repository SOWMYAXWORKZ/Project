package com.xworkz.medisales.controller;

import com.xworkz.medisales.dto.CreateStockDto;
import com.xworkz.medisales.dto.MediSalesDto;
import com.xworkz.medisales.services.distributorService.CreateStockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@RequestMapping
@Controller

public class CreateStockController {

    @Autowired
    CreateStockService createStockService;

    public CreateStockController() {
        log.info("CreateStockController method is invoking");
    }

    @GetMapping("createstock") //page action
    public String getstock() {
        return "createstock";
    }

    @PostMapping("createstock")
    public String createAndSaveStock(@Valid CreateStockDto createStockDto, BindingResult bindingResult, Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {
            log.info("Validation errors found: {}", bindingResult.getAllErrors());
            return "createstock";
        }

        // only login email person can create a stock
        String loggedInEmail = (String) session.getAttribute("loggedInEmail");
        if (loggedInEmail != null) {
            createStockDto.setEmail(loggedInEmail); // adding email(values) to the dto
            log.info("Set email : {}", loggedInEmail);
        } else {
            log.info("The distributor is not logged in pls login and create a stock");
            model.addAttribute("message", "Login and create a stock");
            return "siginin";
        }

        // now calling the  service in validateAndSaveStock method to validate and save  stock
        boolean isCreate = createStockService.validateAndSaveStock(createStockDto);
        if (isCreate) {
            model.addAttribute("message", "stock detailes are added successfully");
            return "createstock";
        } else {
            model.addAttribute("message", "failed to add the stock details");
            return "createstock";
        }

    }

}

