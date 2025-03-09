package com.xworkz.medisales.repository.medisalesRepo;

import com.xworkz.medisales.dto.MediSalesDto;
import com.xworkz.medisales.entity.MediSalesEntity;

import java.util.List;

public interface MediSalesRepository {
    boolean save(MediSalesEntity mediSalesEntity);

    boolean checkPhone(Long mobile);

    MediSalesEntity getEntityByEmail(String email);

    MediSalesEntity updateEntityByEmail(MediSalesEntity signInEntity);

    MediSalesEntity checkEmail(String email);


    boolean updatePasswordByEmail(String email, String pwd, String confirmPwd);

    MediSalesEntity updateById(MediSalesEntity entity);

    List<MediSalesEntity> getContactPersons();

    MediSalesEntity findById(int id);


}
