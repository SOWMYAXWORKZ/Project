package com.xworkz.medisales.services.vendorService;

import com.xworkz.medisales.dto.VendorDto;
import com.xworkz.medisales.entity.MediSalesEntity;
import com.xworkz.medisales.entity.VendorEntity;
import com.xworkz.medisales.repository.medisalesRepo.MediSalesRepository;
import com.xworkz.medisales.repository.vendorRepo.VendorRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j

public class VendorServiceImpl implements VendorService{

    @Autowired
    VendorRepo vendorRepo;

    @Autowired
    MediSalesRepository mediSalesRepository;

    @Override
    public boolean validateAndSave(VendorDto vendorDto) {

        /*MediSalesEntity mediSalesEntity = mediSalesRepository.findById(vendorDto.getId());// here getId is from mediSalesEntity
        if (mediSalesEntity == null){
            throw  new RuntimeException("Invalid MediSales Id");
        }
*/
        boolean isValidateAndSaveVendor = false;
        log.info("validateAndSave in vendor method is invoking....");
        VendorEntity vendorEntity = new VendorEntity();
        if (vendorDto.getVendorName()!=null && !vendorDto.getVendorName().isEmpty()){
            BeanUtils.copyProperties(vendorDto, vendorEntity);
            //set and save the mediSales entity
           // vendorEntity.setMediSalesEntity(mediSalesEntity);
            isValidateAndSaveVendor = vendorRepo.save(vendorEntity);
            log.info("Successfully added the vendor details");
            System.out.println("added vendor details successfully");
        }else {
            log.info("Failed to add the vendor details");
            System.out.println("vendor details failed to add");
        }
        return isValidateAndSaveVendor;
    }

    @Override
    public List<VendorDto> getAllVendorsDetails() {

       return vendorRepo.getAllVendorsDetails();
    }

    @Override
    public void updateVendor(VendorDto vendorDto) {
        vendorRepo.updateVendor(vendorDto);
    }

    @Override
    public VendorDto getVendorById(int id) {
        return vendorRepo.fetchVendorById(id);
    }

    @Override
    public void deleteVendorById(int id) {
        vendorRepo.deleteVendorById(id);
    }
}
