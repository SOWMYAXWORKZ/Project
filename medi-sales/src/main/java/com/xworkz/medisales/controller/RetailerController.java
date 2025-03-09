package com.xworkz.medisales.controller;

import com.xworkz.medisales.dto.RetailerDto;
import com.xworkz.medisales.dto.VendorDto;
import com.xworkz.medisales.services.retailerService.RetailerService;
import com.xworkz.medisales.services.vendorService.VendorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequestMapping

public class RetailerController {

    @Autowired
    RetailerService retailerService;

    public RetailerController() {
        log.info("retailer controller method is invoking...");
    }

    // add retailers page  action
    @GetMapping("addretailers")
    public String addvendor() {
        return "addretailers";
    }

    // add retailers form action
    @PostMapping("addretailers")
    public String addRetailers(@Valid RetailerDto retailerDto, BindingResult bindingResult, Model model) {
        log.info("addRetailers method is invoking...");
        boolean hasErrors = bindingResult.hasErrors();
        if (hasErrors) {
            List<ObjectError> objectErrors = bindingResult.getAllErrors();
            objectErrors.forEach(objectError -> {
                FieldError fieldError = (FieldError) objectError;
                System.out.println(fieldError.getField() + " ErrorMessage : " + fieldError.getDefaultMessage());
                model.addAttribute(fieldError.getField() + "Error", fieldError.getDefaultMessage());
            });
            model.addAttribute("retailerDto", retailerDto);
            return "addretailers"; // Return to addretailers page if validation fails
        }

        // Proceed with saving the retailer
        boolean reg = retailerService.validateAndSave(retailerDto);
        if (reg) {
            System.out.println("retailer has been added");
            model.addAttribute("retailerName", retailerDto.getRetailerName());
            return "addretailers"; // Redirect to success page
        }

        // In case saving fails
        model.addAttribute("errorMessage", "add retailer details is  failed! Please try again.");
        return "addvendors"; // Return to same page

    }


    //form action
    @GetMapping("getAllRetailers")
    public String getAllRetailersDetails(Model model) {
        log.info("getAllRetailersDetails method is invoking...");
        List<RetailerDto> retailers = retailerService.getAllRetailersDetails();
        model.addAttribute("retailers", retailers);
        log.info("Now view the retailer details successffuly");
        return "getAllRetailers";
    }

    // update the retailer details
    @PostMapping("updateRetailer")
    public RedirectView updateRetailer(@Valid RetailerDto retailerDto, BindingResult bindingResult, Model model, HttpSession httpSession , HttpServletRequest request) {
        log.info("updateRetailer method is invoking...");
        RedirectView  redirectView = new RedirectView();
        redirectView.setUrl(request.getContextPath()+"/getAllRetailers");
        if (bindingResult.hasErrors()) {
            model.addAttribute("retailer", retailerDto);

            return redirectView;
        }

        retailerService.updateRetailer(retailerDto);
        return redirectView;
    }


    // fetching the retailer details
    @GetMapping("fetchretailer")
    public String fetchVendor(@RequestParam("id") int id, Model model) {
        RetailerDto retailer = retailerService.getRetailerById(id);
        model.addAttribute("retailer", retailer);
        return "fetchretailer";
    }

    // delete the retailer details from the table
    @GetMapping("deleteretailer")
    public RedirectView deleteRetailer(@RequestParam("id") int id, HttpServletRequest request) {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(request.getContextPath()+"/getAllRetailers");
        retailerService.deleteRetailerById(id);
        return redirectView;
    }

}
