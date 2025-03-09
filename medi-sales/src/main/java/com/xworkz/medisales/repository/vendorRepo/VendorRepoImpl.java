package com.xworkz.medisales.repository.vendorRepo;

import com.xworkz.medisales.dto.VendorDto;
import com.xworkz.medisales.entity.VendorEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;


@Repository
@Slf4j


public class VendorRepoImpl implements VendorRepo {

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Override
    public boolean save(VendorEntity vendorEntity) {
        log.info("save method in vendor repo is invoking...");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(vendorEntity);
        entityManager.getTransaction().commit();
        entityManager.close();
        log.info("Successfully saved the vendor details ");
        System.out.println("Saved the vendor details Successfully");

        return true;
    }

    @Override
    public List<VendorDto> getAllVendorsDetails() {
        log.info("getAllVendorsDetails method is invoking...");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createNamedQuery("getAllVendors");
            log.info("Fetch vendor Details  ");
            return query.getResultList();

        } catch (Exception e) {
            log.info("Exception erorrs");
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void updateVendor(VendorDto vendorDto) {
        log.info("update vendor method is invoking");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        VendorEntity vendorEntity = new VendorEntity();
        BeanUtils.copyProperties(vendorDto, vendorEntity);
        entityManager.merge(vendorEntity);
        log.info("updated the vendor details successfully");
        System.out.println("Successfully updated vendor details ");
        entityManager.getTransaction().commit();
        entityManager.close();


    }

    @Override
    public VendorDto fetchVendorById(int id) {
        log.info("fetchVendorById method is invoking.....");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        VendorEntity vendorEntity = entityManager.find(VendorEntity.class, id);
        VendorDto vendorDto = new VendorDto();
        BeanUtils.copyProperties(vendorEntity, vendorDto);
        log.info("fetching the vendor details : {}",id);
        System.out.println("Fetching the details" + id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return vendorDto;

    }

    @Override
    public boolean deleteVendorById(int id) {
        log.info("deleteVendorById method is invoking....");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createNamedQuery("deleteVendorById");
        query.setParameter("id", id);
        log.info("delete the vendor by id successfully: {}",id);
        System.out.println("Deleted the vendor details successfully"+id);
        int noOfRowsDeleted = query.executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
        return noOfRowsDeleted > 0;


    }
}
