package com.xworkz.medisales.controller;

import com.xworkz.medisales.dto.CreateStockDto;
import com.xworkz.medisales.services.distributorService.CreateStockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@RequestMapping
@Controller
public class ViewStockController {

    @Autowired
    CreateStockService createStockService;

    public ViewStockController() {
        log.info("ViewStockController method is invoking");
    }

    @GetMapping("viewstock")
    public String viewStockDetails(Model model) {
        List<CreateStockDto> stocks = createStockService.getAllStockDetails();
        model.addAttribute("stocks", stocks);
        log.info("Now view the stock details successffuly");
        return "viewstock";
    }

    @GetMapping("viewstockbyemail")
    public String viewStockDetails(Model model, HttpSession session) {

        String loggedInEmail = (String) session.getAttribute("loggedInEmail"); // through email that particular can view the stock

        //fetch the details by logged in persons
        List<CreateStockDto> stocks = createStockService.getStockDetailsByDistributor(loggedInEmail);
        model.addAttribute("stocks", stocks);
        log.info("Now fetch the details in viewstockbyemail successffuly: {}", loggedInEmail);
        return "viewstockbyemail";
    }

//    @GetMapping("fetch")
//    public String fetchStockByEmail(@RequestParam("email") String email, Model model) {
//        List<CreateStockDto> stockDtoList = createStockService.fetchStockByEmail(email);
//        if (!stockDtoList.isEmpty()) {
//            model.addAttribute("stocks", stockDtoList);
//            model.addAttribute("email", email);
//            log.info("Update the product details : {}", email);
//            return "fetch";
//        }
//        model.addAttribute("info", "no stock found for the email");
//        return "fetch";
//    }

//    @PostMapping("updateStock")
//    public String updateStock(CreateStockDto createStockDto, @RequestParam("email") String email, Model model) {
//        boolean isUpdated = createStockService.updateStock(createStockDto);
//        if (isUpdated) {
//            model.addAttribute("message", "Stock updated successfully!");
//        } else {
//            model.addAttribute("message", "Failed to update stock!");
//        }
//        return "fetch"; // Redirect to stock list
//    }
}