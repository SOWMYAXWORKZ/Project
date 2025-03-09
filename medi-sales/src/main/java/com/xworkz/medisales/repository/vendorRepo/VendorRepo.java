package com.xworkz.medisales.repository.vendorRepo;

import com.xworkz.medisales.dto.VendorDto;
import com.xworkz.medisales.entity.VendorEntity;

import java.util.List;

public interface VendorRepo {
    boolean save(VendorEntity vendorEntity);

    List<VendorDto> getAllVendorsDetails();

    void updateVendor(VendorDto vendorDto);

    VendorDto fetchVendorById(int id);

    boolean deleteVendorById(int id);
}
