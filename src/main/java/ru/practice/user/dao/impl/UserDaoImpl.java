package ru.practice.user.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.practice.country.model.Country;
import ru.practice.doctype.model.DocType;
import ru.practice.office.model.Office;
import ru.practice.user.dao.UserDao;
import ru.practice.user.model.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class UserDaoImpl implements UserDao {

    private final EntityManager em;

    @Autowired
    public UserDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(User user) {
        em.persist(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> list(Office office_id, String first_name, String second_name, String middle_name, String position, String docCode, String citizenshipCode) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        Path<Object> user_is = userRoot.get("id");
        Path<Object> user_first_name = userRoot.get("first_name");
        Path<Object> user_second_name = userRoot.get("second_name");
        Path<Object> user_middle_name = userRoot.get("middle_name");
        Path<Object> user_position = userRoot.get("position");
        Join<User, DocType> docTypeJoin = userRoot.join("id", JoinType.INNER);
        Join<User, Country> countryJoin = userRoot.join("id", JoinType.INNER);
        criteriaQuery.multiselect(user_is, user_first_name, user_second_name, user_middle_name, user_position);
        criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(userRoot.get("office_id"), office_id)), criteriaBuilder.equal(userRoot.get("first_name"), first_name), criteriaBuilder.equal(userRoot.get("second_name"), second_name), criteriaBuilder.equal(userRoot.get("middle_name"), middle_name), criteriaBuilder.equal(userRoot.get("position"), position), criteriaBuilder.equal(docTypeJoin.get("code"), docCode), criteriaBuilder.equal(countryJoin.get("code"), citizenshipCode));
        TypedQuery query = em.createQuery(criteriaQuery);
        List list = query.getResultList();
        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User loadById(Long id) {
        return em.find(User.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User update(User user) {
        User olduser = em.find(User.class, user.getId());
        olduser.setFirst_name(user.getFirst_name());
        olduser.setSecond_name(user.getSecond_name());
        olduser.setMiddle_name(user.getMiddle_name());
        olduser.setPosition(user.getPosition());
        olduser.setPhone(user.getPhone());
        olduser.setDoc_date(user.getDoc_date());
        olduser.setIs_identified(user.getIs_identified());
        olduser.setCountry(user.getCountry());
        olduser.setDoc_type(user.getDoc_type());
        olduser.setOffice(user.getOffice());
        return em.merge(olduser);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaDelete<User> criteriaDelete = criteriaBuilder.createCriteriaDelete(User.class);
        Root<User> userRoot = criteriaDelete.from(User.class);
        criteriaDelete.where(criteriaBuilder.equal(userRoot.get("id"), id));
        em.createQuery(criteriaDelete).executeUpdate();
    }
}
