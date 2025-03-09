package com.xworkz.medisales.repository.billrepo;

import com.xworkz.medisales.entity.BillEntity;
import com.xworkz.medisales.services.billservice.BillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@Slf4j
@Repository


public class BillRepoImpl implements BillRepo {

    @Autowired
    EntityManagerFactory entityManagerFactory;


    @Override
    public void save(BillEntity billEntity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            if (billEntity.getBillNo() == null || billEntity.getBillNo() <= 0) {
                Query query = entityManager.createQuery("SELECT COALESCE(MAX(b.billNo), 1000) FROM BillEntity b");
                Long lastBillNo = (Long) query.getSingleResult();
                Long newBillNo = lastBillNo + 1;

                billEntity.setBillNo(newBillNo);
                log.info("Generated new bill number: {}", newBillNo);
            } else {
                log.info("Using existing bill number: {}", billEntity.getBillNo());
            }

            entityManager.persist(billEntity);
            entityManager.getTransaction().commit();
            log.info("Bill saved successfully with BillNo: {}", billEntity.getBillNo());
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            log.error("Error saving bill: {}", e.getMessage());
            throw e;
        } finally {
            entityManager.close();
        }
    }


    @Override
    public Long findBillNo() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createQuery("SELECT MAX(b.billNo) FROM BillEntity b");
            Long lastBillNo = (Long) query.getSingleResult();

            // Ensure we do not return 0 as a valid bill number
            return (lastBillNo != null && lastBillNo > 0) ? lastBillNo : null;
        } catch (Exception e) {
            log.error("Error fetching latest bill number: {}", e.getMessage());
            return null; // Return null instead of 0
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<BillEntity> findAll() {
        return entityManagerFactory.createEntityManager()
                .createNamedQuery("findAllBills", BillEntity.class)
                .getResultList();
    }

    @Override
    public void updateBillNumber(Long newBillNo) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createNamedQuery("updateBillNumber");
            query.setParameter("newBillNo", newBillNo);
            query.executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback(); // Ensure rollback on failure
            throw e;
        } finally {
            entityManager.close();
        }
    }
}
