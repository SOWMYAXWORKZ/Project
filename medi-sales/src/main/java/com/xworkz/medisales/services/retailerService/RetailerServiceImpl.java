package com.xworkz.medisales.services.retailerService;

import com.xworkz.medisales.dto.RetailerDto;
import com.xworkz.medisales.entity.RetailerEntity;
import com.xworkz.medisales.entity.VendorEntity;
import com.xworkz.medisales.repository.retailerRepo.RetailerRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j

public class RetailerServiceImpl implements RetailerService{

    @Autowired
    RetailerRepo retailerRepo;

    @Override
    public boolean validateAndSave(RetailerDto retailerDto) {
        boolean isValidateAndSaveRetailer = false;
        log.info("validateAndSave in retailer method is invoking....");
        RetailerEntity retailerEntity = new RetailerEntity();
        if (retailerDto.getRetailerName()!=null && !retailerDto.getRetailerName().isEmpty()){
            BeanUtils.copyProperties(retailerDto, retailerEntity);
            isValidateAndSaveRetailer = retailerRepo.save(retailerEntity);
            log.info("Successfully added the retailer details");
        }else {
            log.info("Failed to add the retailer details");

        }
        return isValidateAndSaveRetailer;
    }

    @Override
    public List<RetailerDto> getAllRetailersDetails() {
        return retailerRepo.getAllVendorsDetails();
    }

    @Override
    public void updateRetailer(RetailerDto retailerDto) {
            retailerRepo.updateRetailer(retailerDto);
    }

    @Override
    public RetailerDto getRetailerById(int id) {
        return retailerRepo.fetchRetailerById(id);
    }

    @Override
    public void deleteRetailerById(int id) {
            retailerRepo.deleteRetailerById(id);
    }


}
