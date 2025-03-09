package com.xworkz.medisales.controller;


import com.xworkz.medisales.dto.CreateStockDto;
import com.xworkz.medisales.dto.VendorDto;
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
@RequestMapping
@Slf4j

public class VendorController {

    @Autowired
    VendorService vendorService;

    public VendorController() {
        log.info("vendor controller method is invoking...");
    }

    // add vendors page  action
    @GetMapping("addvendors")
    public String addvendor() {
        return "addvendors";
    }

    // add vendors form action
    @PostMapping("addvendors")
    public String addVendors(@Valid VendorDto vendorDto, BindingResult bindingResult, Model model) {

        boolean hasErrors = bindingResult.hasErrors();
        if (hasErrors) {
            List<ObjectError> objectErrors = bindingResult.getAllErrors();
            objectErrors.forEach(objectError -> {
                FieldError fieldError = (FieldError) objectError;
                System.out.println(fieldError.getField() + " ErrorMessage : " + fieldError.getDefaultMessage());
                model.addAttribute(fieldError.getField() + "Error", fieldError.getDefaultMessage());
            });
            model.addAttribute("vendorDto", vendorDto);
            return "addvendors"; // Return to addvendors page if validation fails
        }


        // Proceed with saving the vendor
        boolean reg = vendorService.validateAndSave(vendorDto);
        if (reg) {
            System.out.println("vendor has been added");
            model.addAttribute("vendorName", vendorDto.getVendorName());
            return "addvendors"; // Redirect to success page
        }

        // In case saving fails
        model.addAttribute("errorMessage", "add vendor details is  failed! Please try again.");
        return "addvendors"; // Return to same page

    }


    //form action
    @GetMapping("getAllVendors")
    public String getAllVendorsDetails(Model model) {
        List<VendorDto> vendors = vendorService.getAllVendorsDetails();
        model.addAttribute("vendors", vendors);
        log.info("Now view the vendor details successffuly");
        return "getAllVendors";
    }

    // update the vendor details
    @PostMapping("updateVendor")
    public RedirectView updateVendor(@Valid VendorDto vendorDto, BindingResult bindingResult, Model model, HttpSession httpSession , HttpServletRequest request) {
        RedirectView  redirectView = new RedirectView();
        redirectView.setUrl(request.getContextPath()+"/getAllVendors");
        if (bindingResult.hasErrors()) {
            model.addAttribute("vendor", vendorDto);

            return redirectView;
        }

        vendorService.updateVendor(vendorDto);
        return redirectView;
    }


    // fetching the vendor details
    @GetMapping("fetchvendor")
    public String fetchVendor(@RequestParam("id") int id, Model model) {
        VendorDto vendor = vendorService.getVendorById(id);
        model.addAttribute("vendor", vendor);
        return "fetchvendor";
    }

    // delete the vendor details from the table
    @GetMapping("deletevendor")
    public RedirectView deleteVendor(@RequestParam("id") int id, HttpServletRequest request) {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(request.getContextPath()+"/getAllVendors");
        vendorService.deleteVendorById(id);
        return redirectView;
    }


}
