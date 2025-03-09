package com.xworkz.medisales.services.medisalesservice;

import com.xworkz.medisales.dto.MediSalesDto;
import org.springframework.web.multipart.MultipartFile;

public interface MediSalesService {

    boolean validateAndSave(MediSalesDto mediSalesDto);

    MediSalesDto validateAndSignIn(String email, String password);


    boolean upadatePasswordByEmail(String email, String pwd, String confirmPwd);

    boolean validateOtp(String otp, String email);

    MediSalesDto getEntityByEmail(String email);


    MediSalesDto updateById(MediSalesDto mediSalesDto, MediSalesDto mediSalesDto1, MultipartFile multipartFile);


}
