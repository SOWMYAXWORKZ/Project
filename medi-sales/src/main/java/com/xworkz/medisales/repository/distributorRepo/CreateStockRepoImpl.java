package com.xworkz.medisales.repository.distributorRepo;

import com.xworkz.medisales.dto.CreateStockDto;
import com.xworkz.medisales.entity.CreateStockEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@Slf4j
@Repository
public class CreateStockRepoImpl implements CreateStockRepo {

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Override
    public boolean addStock(CreateStockEntity createStockEntity) {
        log.info("addStock method is invoking {}", createStockEntity);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            entityManager.persist(createStockEntity);
            entityManager.getTransaction().commit();
            log.info("The Stock Details are saved : {}", createStockEntity);
        } catch (Exception e) {
            log.info("Exception erorr : {}",e.getMessage());
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }

        return true;
    }

    @Override
    public CreateStockDto getStockById(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CreateStockDto dto = entityManager.find(CreateStockDto.class, id);
        entityManager.close();
        return dto;
    }

    @Override
    public List<CreateStockDto> getAllStockDetails() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createNamedQuery("getAllStock");
            log.info("Fetch view Details  ");
            return query.getResultList();

        } catch (Exception e) {
            log.info("Exception erorrs");
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public CreateStockEntity getBillByProductName(String productName) {
        try {
            return (CreateStockEntity) entityManagerFactory.createEntityManager()
                    .createNamedQuery("getBillByProductName", CreateStockEntity.class)
                    .setParameter("productName", productName) // check entiyy
                    .getSingleResult();
        } catch (Exception e) {
           return null;
        }
    }

    @Override
    public List<CreateStockEntity> getProducts() {
       return entityManagerFactory.createEntityManager()
                .createNamedQuery("getProducts",CreateStockEntity.class)
                .getResultList();


    }

    @Override
    public int getStockByProduct(String productName) {

        return (int) entityManagerFactory.createEntityManager().createQuery("select d.quantity from CreateStockEntity d where d.productName =: productName").setParameter("productName", productName).getSingleResult();
    }

    @Override
    public List<CreateStockDto> getStockDetailsByDistributor(String loggedInEmail) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            Query query =entityManager.createNamedQuery("getStockDetailsByDistributor");
            query.setParameter("email",loggedInEmail);
           return query.getResultList();
        } catch (Exception e) {
            log.info("error is occuring when details are fetching from distributor {}",loggedInEmail,e);
            throw new RuntimeException(e);
        }finally {
            entityManager.close();
        }
    }

    @Override
    public List<CreateStockEntity> findAllStockBillDetails() {
        return  entityManagerFactory.createEntityManager()
                .createNamedQuery("findAllStockBillDetails", CreateStockEntity.class)
                .getResultList();
    }



}
