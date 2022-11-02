package _10a.academy.controller;


import _10a.academy.model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
public class UserController {


    @PersistenceContext
    EntityManager entityManager;

    public List<User> getAllUsers(){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> userBeanCriteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userBeanRoot = userBeanCriteriaQuery.from(User.class);
        userBeanRoot.fetch("trainingDetailsList", JoinType.LEFT);
        return entityManager.createQuery(userBeanCriteriaQuery.select(userBeanRoot).distinct(true)).getResultList();
    }



    public User getUserById(Long id){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        userRoot.fetch("trainingDetailsList", JoinType.LEFT);
        return entityManager.createQuery(criteriaQuery.select(userRoot)
                        .where(criteriaBuilder.equal(userRoot.get("userId"),id))
                        .distinct(true)).getSingleResult();
    }

}
