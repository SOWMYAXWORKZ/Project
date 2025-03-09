package com.xworkz.medisales.controller;

import com.xworkz.medisales.dto.BillDto;
import com.xworkz.medisales.dto.CreateStockDto;
import com.xworkz.medisales.entity.BillEntity;
import com.xworkz.medisales.repository.billrepo.BillRepo;
import com.xworkz.medisales.repository.distributorRepo.CreateStockRepo;
import com.xworkz.medisales.services.billservice.BillService;
import com.xworkz.medisales.services.distributorService.CreateStockService;
import com.xworkz.medisales.services.medisalesservice.MediSalesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping
public class BillController {

    public BillController() {
        log.info("Bill Controller is now invoking ...");
    }

    @Autowired
    CreateStockService createStockService;

    @Autowired
    MediSalesService mediSalesService;

    @Autowired
    BillService billService;

    @Autowired
    BillRepo billRepo;

    @Autowired
    CreateStockRepo createStockRepo;



    @GetMapping("retailersalesbill")
    public String salesBill() {
        return "retailersalesbill";
    }


    private static List<CreateStockDto> attributeList = new ArrayList<>();

    @PostMapping("retailersalesbill")
    public String vendorSalesBill(@RequestParam("productName") String productName,
                                  @RequestParam("contactPerson") String contactPerson,
                                  @RequestParam("discount") Double discount,
                                  @RequestParam("quantity") Integer quantity,
                                  @RequestParam("gst") Double gst,
                                  Model model, HttpSession session) {


        double finalTotal = 0.0;
        Long existingBillNo = 0L;
        log.info("Recived Bill details:  ProductName = {} , Vendor = {} , Qty = {}  , Discount = {} , GST = {} ", productName,contactPerson, quantity,discount,gst);


        //generate bill number and bill date

        Long billNumber = billService.generateBillNum(existingBillNo);
        String billDate = String.valueOf(billService.getBillDate());

        //product details
        CreateStockDto createStockDto = createStockService.getBillByProductName(productName);
        for (CreateStockDto existingProduct: attributeList){
            if (existingProduct.getProductName().equals(productName)){
                double discountPrice = ((existingProduct.getRate() * quantity  * discount )/100);
                double gstPrice  =( (discountPrice * gst )/100);
                double totalAmount =( existingProduct.getRate() * quantity - discountPrice + gstPrice);


                existingProduct.setQuantity(existingProduct.getQuantity()+quantity);
                existingProduct.setAmount(totalAmount+existingProduct.getAmount());

                model.addAttribute("dtoList",attributeList);
                for (CreateStockDto stockDto  : attributeList){
                    finalTotal = stockDto.getAmount();
                }
                model.addAttribute("total",finalTotal);
                session.setAttribute("final",finalTotal);
                session.setAttribute("listDto",attributeList);
                return "retailersalesbill";
            }

        }

        CreateStockDto dto = createStockService.getBillByProductName(productName);
        double discountPrice = ((dto.getRate() * quantity * discount)/100);
        double gstPrice = ((discountPrice * gst) / 100);
        dto.setAmount(dto.getRate() * quantity - discountPrice + gstPrice);

        CreateStockDto stockDto = new CreateStockDto();
        BeanUtils.copyProperties(dto,stockDto);
        stockDto.setQuantity(quantity);
        stockDto.setDiscount(discount);
        stockDto.setGst(gst);

        attributeList.add(stockDto);
        model.addAttribute("dtoList",attributeList);
        for (CreateStockDto stockDto1  : attributeList){
            finalTotal+= stockDto1.getAmount();
        }
        model.addAttribute("total",finalTotal);
        model.addAttribute("total",finalTotal);

        // saving bill info to database
        BillEntity billEntity = new BillEntity();
        billEntity.setBillNo(billService.generateBillNum(existingBillNo));
        billEntity.setBillDate(LocalDate.parse(billDate));
        billEntity.setTotalAmount(finalTotal);
        billRepo.save(billEntity);

        model.addAttribute("billNumber",billNumber);
        model.addAttribute("billDate",billDate);
        session.setAttribute("listDto",attributeList);

        return  "retailersalesbill";

    }

    @GetMapping("getBillDetails")
    public BillDto getBillDetails() {
        Long billNumber = billService.findBillNo() + 1;
        LocalDate billDate = LocalDate.now();

        return new BillDto(billNumber, billDate);
    }

    }





