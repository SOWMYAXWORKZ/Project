package com.xworkz.medisales.services.medisalesservice;

import com.xworkz.medisales.constants.ServiceConstant;
import com.xworkz.medisales.dto.MediSalesDto;
import com.xworkz.medisales.entity.MediSalesEntity;
import com.xworkz.medisales.repository.medisalesRepo.MediSalesRepository;
import com.xworkz.medisales.services.mailsenderservice.MailSenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

@Slf4j
@Service
public class MediSalesServiceImpl implements MediSalesService {

    @Autowired
    private MediSalesRepository mediSalesRepository;

    @Autowired
    private MailSenderService mailSenderService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public boolean validateAndSave(MediSalesDto mediSalesDto) {
        boolean isValidateAndSave = false;
        MediSalesEntity mediSalesEntity = new MediSalesEntity();
        mediSalesDto.setPassword(passwordEncoder.encode(mediSalesDto.getPassword()));
        if (mediSalesDto.getContactPerson() != null && !mediSalesDto.getContactPerson().isEmpty()) {
            BeanUtils.copyProperties(mediSalesDto, mediSalesEntity);
            mediSalesEntity.setCreatedBy(mediSalesEntity.getContactPerson());
            mediSalesEntity.setCreatedDate(LocalDate.now());
            mediSalesEntity.setFileName("blank.jpg");
            mediSalesEntity.setFileType("jpg/png");

            isValidateAndSave = mediSalesRepository.save(mediSalesEntity);

            if (isValidateAndSave) {
                mailSenderService.sendRegistrationEmail("Registration Confirmed",
                        "Dear User,\n\n" +
                                "Thank you for registering with us! We are delighted to assist you in managing your work efficiently.\n\n" +
                                "Your registration details are as follows:\n" +
                                " - Login Email: " + mediSalesDto.getEmail() + "\n" +
                                " - Login Password: " + mediSalesEntity.getConfirmPassword() + "\n" +
                                " - Registered as: " + mediSalesEntity.getBusinesstype() + "\n\n" +
                                "For any assistance, feel free to reach out to us using the details below:\n" +
                                " - Contact Email: " + ServiceConstant.FROM_MAIL.getPath() + "\n" +
                                " - Contact Number: 7878787878\n\n" +
                                "<p>Click the link below to access your account:</p>" +
                                "<p><a href='http://localhost:2992/medi-sales/signin' target='_blank'>Login to Your Account</a></p>" +
                                "Thank you for choosing Med Sales Pharmacy.\n\n" +
                                "Best Regards,\n" +
                                "Med Sales Pharmacy Team", mediSalesDto.getEmail()
                );

                System.out.println("Registration is done !!");
            } else {
                log.info("The email from the entity is already exits : {} ", mediSalesEntity.getEmail());
            }

        }

        return isValidateAndSave;
    }

    @Override
    public MediSalesDto validateAndSignIn(String email, String password) {
        MediSalesDto signInDto = new MediSalesDto();
        MediSalesEntity signInEntity = mediSalesRepository.getEntityByEmail(email);
        if (signInEntity != null
                && signInEntity.getEmail() != null
                && signInEntity.getEmail().equalsIgnoreCase(email)
                && signInEntity.getPassword() != null
                && passwordEncoder.matches(password, signInEntity.getPassword())
                && signInEntity.getAttempt() <= 3) {
            log.info("{} {} this is validate and signIn email and password", email, password);
            BeanUtils.copyProperties(signInEntity, signInDto);
            signInEntity.setAttempt(0);
            mediSalesRepository.updateEntityByEmail(signInEntity);
            return signInDto;
        } else {
            MediSalesEntity signInEntity1 = mediSalesRepository.getEntityByEmail(email);
            log.info("the signInEntity is: {}", signInEntity1);
            signInEntity1.setAttempt(signInEntity1.getAttempt() + 1);
            log.info("the signInEntity after entering the password is {}", signInEntity1);
            MediSalesDto mediSalesDto = new MediSalesDto();
            mediSalesDto.setAttempt(signInEntity1.getAttempt());
            mediSalesRepository.updateEntityByEmail(signInEntity1);
            return mediSalesDto;
        }

    }

    @Override
    public boolean upadatePasswordByEmail(String email, String pwd, String confirmPwd) {

        mediSalesRepository.updatePasswordByEmail(email,passwordEncoder.encode(pwd),confirmPwd);
        return false;
    }

    @Override
    public boolean validateOtp(String otp, String email) {
       MediSalesEntity mediSalesEntity =  mediSalesRepository.getEntityByEmail(email);
       if (mediSalesEntity.getEmail() != null && mediSalesEntity.getEmail().equalsIgnoreCase(email) && mediSalesEntity.getOtp() != null && passwordEncoder.matches(otp,mediSalesEntity.getOtp())){
           log.info("Email not found : {}",email);
           return false;
       }
        boolean matches = passwordEncoder.matches(otp, mediSalesEntity.getOtp());
        if (matches) {
            log.info("OTP Validation Success");
            return true;
        } else {
            log.info("OTP Validation Failed");
            return false;
        }
    }

    @Override
    public MediSalesDto getEntityByEmail(String email) {
        log.info(" this is the email to fetch the details{}", email);
        MediSalesDto mediSalesDto = new MediSalesDto();
        MediSalesEntity mediSalesEntity = mediSalesRepository.getEntityByEmail(email);
        BeanUtils.copyProperties(mediSalesEntity, mediSalesDto);
        return mediSalesDto;
    }

    @Override
    public MediSalesDto updateById(MediSalesDto mediSalesDto, MediSalesDto mediSalesDto1, MultipartFile multipartFile) {
        log.info("{} Session Dto", mediSalesDto1);

        if (multipartFile.isEmpty()) {
            log.info("file is empty");
        } else {
            try {
                byte[] bytes = multipartFile.getBytes();
                Path path = Paths.get(ServiceConstant.FILE_PATH.getPath() + multipartFile.getOriginalFilename());
                Files.write(path, bytes);
            } catch (IOException e) {
                log.error("{} the error message is :  ", e.getMessage());
            }
        }
        log.info("{} This is the session Dto Passed :  ", mediSalesDto1);
        MediSalesEntity entity = new MediSalesEntity();
        BeanUtils.copyProperties(mediSalesDto, entity);
        entity.setCreatedBy(String.valueOf(mediSalesDto1.getId()));
        entity.setCreatedBy(mediSalesDto1.getContactPerson());
        entity.setCreatedBy(mediSalesDto1.getCompanyName());
        entity.setCreatedBy(mediSalesDto1.getEmail());
        entity.setCreatedDate(LocalDate.now());
        entity.setUpdatedBy(mediSalesDto1.getCreatedBy());
        entity.setUpdateDate(LocalDate.now());
        entity.setFileName(multipartFile.getOriginalFilename());
        entity.setFileType(multipartFile.getContentType());
        log.info("{} the entity passed is ", entity);
        MediSalesEntity mediSalesEntity = mediSalesRepository.updateById(entity);

        MediSalesDto mediSalesDto2 = new MediSalesDto();
        BeanUtils.copyProperties(mediSalesEntity, mediSalesDto2);
        return mediSalesDto2;

    }


}

