package com.xworkz.medisales.services.ajaxservice;


import com.xworkz.medisales.dto.CreateStockDto;
import com.xworkz.medisales.dto.MediSalesDto;
import com.xworkz.medisales.entity.CreateStockEntity;
import com.xworkz.medisales.entity.MediSalesEntity;
import com.xworkz.medisales.repository.distributorRepo.CreateStockRepo;
import com.xworkz.medisales.repository.medisalesRepo.MediSalesRepository;
import com.xworkz.medisales.services.mailsenderservice.MailSenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class AjaxServiceImpl implements AjaxService {

    @Autowired
    private MediSalesRepository mediSalesRepository;

    @Autowired
    private CreateStockRepo createStockRepo;

    @Autowired
    private MailSenderService mailSenderService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String checkEmail(String email) {
        MediSalesEntity mediSalesEntity = mediSalesRepository.checkEmail(email);
        if (mediSalesEntity != null && mediSalesEntity.getEmail().equals(email)) {
            return "email is already exists";
        } else {
            return "email doesn't exist please register";
        }
    }

    @Override
    public String checkPhone(long mobile) {

        boolean mobileNum = mediSalesRepository.checkPhone(mobile);
        log.info("checking the mobile through ajax {}", mobile);
        if (mobileNum) {
            return "Mobile number Exists";
        }
        return "Mobile Number Doesn't Exists";
    }

    //otp
    @Override
    public String sendOtp(String email) {
        log.info("Invoking sendOtp method with email: {}", email);
        MediSalesEntity mediSalesEntity = mediSalesRepository.getEntityByEmail(email);

        if (mediSalesEntity != null && mediSalesEntity.getEmail() != null) {
            Random random = new Random();
            Integer otp = random.nextInt(9999);
            log.info("Generated OTP: {}", otp);

            mailSenderService.sendRegistrationEmail(
                    "OTP Code: ",
                    "Dear " + mediSalesEntity.getContactPerson() + "," +
                            "\nYour OTP IS :: " + otp +
                            ", Please do not share the OTP with anyone." +
                            "\nHave a good day!" +
                            "\nThank You" +
                            "\nMedi-Sales: " + otp + " " + email,
                    email
            );

            mediSalesEntity.setOtp(passwordEncoder.encode(otp.toString()));
            mediSalesRepository.updateEntityByEmail(mediSalesEntity);
            log.info("Encoded OTP saved: {}", mediSalesEntity.getOtp());

            return "Otp Sent Successfully"; // Match frontend expectation (case-sensitive)
        } else {
            log.error("No user found with email: {}", email);
            return "Failed to send OTP. Email not registered.";
        }
    }

    @Override
    public List<CreateStockDto> getProducts() {
        List<CreateStockEntity> createStockEntities = createStockRepo.getProducts();
        List<CreateStockDto> createStockDtos = new ArrayList<>();

        for (CreateStockEntity createStockEntity : createStockEntities) {
            CreateStockDto createStockDto = new CreateStockDto();
            BeanUtils.copyProperties(createStockEntity, createStockDto);
            createStockDtos.add(createStockDto);
        }

        return createStockDtos;
    }

    @Override
    public List<MediSalesDto> getContactPersons() {
        List<MediSalesEntity> mediSalesEntities = mediSalesRepository.getContactPersons();
        List<MediSalesDto> mediSalesDtos = new ArrayList<>();

        for (MediSalesEntity mediSalesEntity : mediSalesEntities) {
            MediSalesDto mediSalesDto = new MediSalesDto();
            BeanUtils.copyProperties(mediSalesEntity, mediSalesDto);
            mediSalesDtos.add(mediSalesDto);
        }
        return mediSalesDtos;
    }

    @Override
    public int getStockByProduct(String productName) {
        return createStockRepo.getStockByProduct(productName);
    }
}
