package com.xworkz.medisales.controller;

import com.xworkz.medisales.constants.BusinessType;
import com.xworkz.medisales.dto.MediSalesDto;
import com.xworkz.medisales.services.medisalesservice.MediSalesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;


@RequestMapping
@Slf4j
@Controller
public class MediSalesController {

    @Autowired
    MediSalesService mediSalesService;

    MediSalesController() {
        System.out.println("invoking the MediSalesController");
    }


    @GetMapping("index") //page action
    public String getIndex() {
        return "index";
    }


    //Register page action
    @GetMapping("signup")
    public String getSignup() {
        return "signup";
    }

    @PostMapping("signup") //form action
    public String register(@Valid MediSalesDto mediSalesDto, BindingResult bindingResult, Model model) {
        log.info("register method is invoked: {0}");

        boolean hasErrors = bindingResult.hasErrors();
        if (hasErrors) {
            List<ObjectError> objectErrors = bindingResult.getAllErrors();
            objectErrors.forEach(objectError -> {
                FieldError fieldError = (FieldError) objectError;
                System.out.println(fieldError.getField() + " ErrorMessage : " + fieldError.getDefaultMessage());
                model.addAttribute(fieldError.getField() + "Error", fieldError.getDefaultMessage());
            });
            model.addAttribute("mediSalesDto", mediSalesDto);
            return "signup"; // Return to signup page if validation fails
        }

        // Proceed with saving the user if email is not taken
        boolean reg = mediSalesService.validateAndSave(mediSalesDto);
        if (reg) {
            System.out.println("User has been registered");
            model.addAttribute("contactPerson", mediSalesDto.getContactPerson());
            return "response"; // Redirect to success page
        }

        // In case saving fails
        model.addAttribute("errorMessage", "Registration failed! Please try again.");
        return "signup"; // Return to signup page
    }

    //Login page action
    @GetMapping("signin")
    public String getSignIn() {
        return "signin";
    }

    //Login Form action
    @PostMapping("signin")
    public String signIn(@RequestParam String email, @RequestParam String password, @RequestParam BusinessType businesstype, Model model, HttpSession session) {
        log.info("The Email is : {}", email);
        log.info("The Password is: {}", password);
        log.info("The Bussiness Type is : {}", businesstype);


        MediSalesDto dto = mediSalesService.validateAndSignIn(email, password);
        log.info("Login method stared {}", email);
        if (dto != null
                && dto.getEmail() != null
                && dto.getEmail().equalsIgnoreCase(email)) {

            log.info("{} signed in successfully", email);
            model.addAttribute("dto", dto);

            // Stores the email in Session
            session.setAttribute("loggedInEmail",email);

            //redirect based on business type
            if ("distributor".equalsIgnoreCase(String.valueOf(dto.getBusinesstype()))) {
                log.info("{} redirect to distributor dashboard", email);
                return "distributorDashboard";
            } else if ("retailer".equalsIgnoreCase(String.valueOf(dto.getBusinesstype()))) {
                log.info("{} redirect to retailer dashboard", email);
                return "retailerDashboard";
            }
        } else { //for invalid credentials
            log.info("Invalid credentials in signin:{}", email);
            model.addAttribute("error", "Email or password Invalid Credentials. ");
            model.addAttribute("found", email);
            return "signin";
        }
        //number of attempts to login
        if (dto.getAttempt() >= 3) {
            model.addAttribute("error", "Account is blocked Please reset the Password to login");

        }
        return "signin";
    }

    //forgot password page action
    @GetMapping("forgotpassword")
    public String resetOtp(){
        return "resetOtp";
    }

    // for resetting the password otp has to enter form action
    @PostMapping("resetOtp")
    public String validateOtp(@RequestParam String otp, @RequestParam String email, Model model){
        boolean isOtpValid = mediSalesService.validateOtp(otp,email);
        if (isOtpValid){
            log.info("checking  given email and otp is valid or not : {} {}", email,otp);
            model.addAttribute("email","Email is being verified");
            model.addAttribute("found",email);
            return "resetpassword";
        }else{
            log.info("enter the valid otp ");
            model.addAttribute("email","Please enter the valid Otp");
        }
        return "resetOtp";
    }


    @PostMapping("resetpassword")
    public String updatePassword(@RequestParam("email") String email, @RequestParam("password") String pwd, @RequestParam("confirmPassword") String confirmPwd, Model model) {

        log.info("update password(resetPassword mapping) method is invoked... ");

        boolean isUpdated = mediSalesService.upadatePasswordByEmail(email, pwd, confirmPwd);
        if (isUpdated) {
            model.addAttribute("FoundEmail",email); // set email if update is successfully
            model.addAttribute("FoundEmail", "Password Updated Successfully");
            return "signin";
        } else {
            model.addAttribute("FoundEmail",email); // set email if update fails
            model.addAttribute("notFoundEmail", "Invalid Credential");

            return "resetpassword";
        }
    }



    @GetMapping("fetch")
    public String fetchDetails(@RequestParam String email, Model model, HttpSession session) {
        log.info("email logged in : {}", email);
        log.trace(" checking that if Fetch method is invoked  ");

        MediSalesDto mediSalesDto = mediSalesService.getEntityByEmail(email);
        log.info("{}", mediSalesDto);
        session.setAttribute("detailsUpdate", mediSalesDto);
        model.addAttribute("detailsUpdate", mediSalesDto);
        return "update";
    }

    @PostMapping("update")
    public String update(MediSalesDto mediSalesDto, BindingResult result, Model model, HttpServletRequest request, @RequestParam("file") MultipartFile multipartFile) {
        log.info(" update method is invoked  :  {}  ", mediSalesDto);
        MediSalesDto mediSalesDto1 = (MediSalesDto) request.getSession().getAttribute("detailsUpdate");
        log.info("  this is the dto   :  {}" , mediSalesDto1);
        MediSalesDto updatedDto = mediSalesService.updateById(mediSalesDto, mediSalesDto1,multipartFile);
        if (result.hasErrors()) {
            log.info("getting errors: {}"," These are the errors generated");
            result.getAllErrors().forEach(objectError -> {
                        FieldError fieldError = (FieldError) objectError;
                        model.addAttribute(fieldError.getField() + "Error", fieldError.getDefaultMessage());

                    }
            );

            model.addAttribute("firstName", mediSalesDto.getContactPerson());
            model.addAttribute("detailsUpdate",updatedDto);

        }

        return "update";
    }
}



