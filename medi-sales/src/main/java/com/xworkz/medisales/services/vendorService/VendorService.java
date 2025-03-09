package com.xworkz.medisales.services.vendorService;

import com.xworkz.medisales.dto.VendorDto;

import java.util.List;

public interface VendorService {
    boolean validateAndSave(VendorDto vendorDto);

    List<VendorDto> getAllVendorsDetails();

    void updateVendor(VendorDto vendorDto);

    VendorDto getVendorById(int id); // fetch

    void deleteVendorById(int id);
}
