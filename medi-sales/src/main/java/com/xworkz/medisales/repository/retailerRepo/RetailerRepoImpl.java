package com.xworkz.medisales.repository.retailerRepo;

import com.xworkz.medisales.dto.RetailerDto;
import com.xworkz.medisales.dto.VendorDto;
import com.xworkz.medisales.entity.RetailerEntity;
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

public class RetailerRepoImpl implements RetailerRepo {

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Override
    public boolean save(RetailerEntity retailerEntity) {
        log.info("the save method in repo is invoking...");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(retailerEntity);
        log.info("the retailer details are saved successfully");
        entityManager.getTransaction().commit();
        entityManager.close();
        return true;
    }

    @Override
    public List<RetailerDto> getAllVendorsDetails() {
        log.info("getAllRetailers method is invoking...");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createNamedQuery("getAllRetailers");
            List resultList = query.getResultList();
            log.info("fetch the retailer details");
            return resultList;

        } catch (Exception e) {
            log.info("exception errors");
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void updateRetailer(RetailerDto retailerDto) {
        log.info("updateRetailer method is invoking ...");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        RetailerEntity retailerEntity = new RetailerEntity();
        BeanUtils.copyProperties(retailerDto, retailerEntity);
        entityManager.merge(retailerEntity);
        log.info("Successfully updated the retailer details");
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    @Override
    public RetailerDto fetchRetailerById(int id) {
        log.info("fetchRetailerById method is invoking ... ");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        RetailerEntity retailerEntity = entityManager.find(RetailerEntity.class, id);
        RetailerDto retailerDto = new RetailerDto();
        BeanUtils.copyProperties(retailerEntity, retailerDto);
        log.info("fetch the retailer details by id successfully");
        entityManager.getTransaction().commit();
        entityManager.close();

        return retailerDto;

    }

    @Override
    public boolean deleteRetailerById(int id) {
        log.info("deleteRetailerById method is invoking ....");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createNamedQuery("deleteRetailerById");
        query.setParameter("id", id);
        log.info("delete the retailer by id successfully: {}",id);
        int noOfRowsDeleted = query.executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
        return noOfRowsDeleted > 0;

    }
}
