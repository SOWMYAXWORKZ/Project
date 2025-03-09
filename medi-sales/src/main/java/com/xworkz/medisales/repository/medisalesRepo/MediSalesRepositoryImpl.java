package com.xworkz.medisales.repository.medisalesRepo;


import com.xworkz.medisales.dto.MediSalesDto;
import com.xworkz.medisales.dto.VendorDto;
import com.xworkz.medisales.entity.MediSalesEntity;
import com.xworkz.medisales.entity.VendorEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

@Repository
@Slf4j
public class MediSalesRepositoryImpl implements MediSalesRepository {


    @Autowired
    EntityManagerFactory entityManagerFactory;

    // for register and sending mail
    @Override
    public boolean save(MediSalesEntity mediSalesEntity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {

            log.info("the email sent from entity: {}", mediSalesEntity.getEmail());
            Query query = entityManager.createNamedQuery("checkEmail").
                    setParameter("email", mediSalesEntity.getEmail());
            entityManager.getTransaction().begin();
            entityManager.persist(mediSalesEntity);
            System.out.println("Saving Entity: " + mediSalesEntity);
            entityManager.getTransaction().commit();
            entityManager.close();
            log.info("User is saved {}", mediSalesEntity);
            return true;
        } catch (Exception e) {
            log.info("getting exception in save method in repo" + e.getMessage());
            throw new RuntimeException(e);
        }

    }

    @Override
    public MediSalesEntity checkEmail(String email) {
        log.info("invoking in the check email method {}", email);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createNamedQuery("findUserByEmail");
            query.setParameter("email", email);
           Object singleResult = query.getSingleResult();
           MediSalesEntity mediSalesEntity  = (MediSalesEntity) singleResult;
           log.info("the email is : {} ",mediSalesEntity);
           entityManager.getTransaction().commit();
           return mediSalesEntity;
        } catch (Exception e) {
            log.info("getting exception " +e.getMessage());
            throw new RuntimeException(e);
        }finally {
            entityManager.close();
        }


    }

    @Override
    public boolean updatePasswordByEmail(String email, String pwd, String confirmPwd) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createNamedQuery("updatePasswordByEmail");
        query.setParameter("email", email);
        query.setParameter("pwd", pwd);
        query.setParameter("confirmPwd", confirmPwd);
        query.executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();

        return true;
    }

    @Override
    public MediSalesEntity updateById(MediSalesEntity entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        log.info("{} mediSalesEntity", entity);
        MediSalesEntity mediSalesEntity = entityManager.merge(entity);
        entityManager.getTransaction().commit();
        entityManager.close();
        return mediSalesEntity;
    }

    @Override
    public List<MediSalesEntity> getContactPersons() {
      return   entityManagerFactory.createEntityManager()
                .createNamedQuery("getContactPersons")
                .getResultList();
    }

    @Override
    public MediSalesEntity findById(int id) {
        log.info("Looking for MediSalesEntity with ID: {}", id);
        log.info("findById method is invoking.....");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        MediSalesEntity mediSalesEntity = entityManager.find(MediSalesEntity.class, id);
        if (mediSalesEntity == null){
            log.info(" MediSalesEntity id is not found : {}",id);
            throw  new IllegalArgumentException("MediSalesEntity with ID: " + id + "not found");
        }
        MediSalesDto mediSalesDto = new MediSalesDto();
        BeanUtils.copyProperties(mediSalesEntity, mediSalesDto);
        log.info(" MediSalesId is : {}",id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return mediSalesEntity;
    }

    @Override
    public boolean checkPhone(Long mobile) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createNamedQuery("checkPhone");
            query.setParameter("mobile", mobile);
            Long count = (Long) query.getSingleResult();
            return count > 0;
        } catch (Exception e) {
            log.info("the error is: {}", e.getMessage());
        }

        return false;
    }

    //for login
    @Override
    public MediSalesEntity getEntityByEmail(String email) {
        log.info("this email is going to login: {}", email);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createNamedQuery("getEntityByEmail", MediSalesEntity.class);
            query.setParameter("email", email);
            List<MediSalesEntity> results = query.getResultList();
            if (results.isEmpty()) {
                return null;
            }
            // If multiple results are found, handle accordingly
            if (results.size() > 1) {
                log.warn("Multiple users found for the same email: {}", email);
            }
            return results.get(0);  // Return the first result or handle as needed
        } catch (NoResultException e) {
            return null;
        } finally {
            entityManager.close();
        }
    }


    @Override
    public MediSalesEntity updateEntityByEmail(MediSalesEntity mediSalesEntity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        MediSalesEntity entity;
        try {
            entityManager.getTransaction().begin();
            entity = entityManager.merge(mediSalesEntity);
            entityManager.getTransaction().commit();
            log.info("the email is {}", mediSalesEntity.getEmail());
        } catch (Exception e) {
            log.info("Failed to login{}", e.getMessage());
            throw e;
        } finally {
            entityManager.close();
        }
        return entity;
    }
}

