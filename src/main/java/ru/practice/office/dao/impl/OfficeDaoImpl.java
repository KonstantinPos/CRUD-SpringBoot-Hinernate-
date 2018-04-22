package ru.practice.office.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.practice.office.dao.OfficeDao;
import ru.practice.office.model.Office;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class OfficeDaoImpl implements OfficeDao {

    private final EntityManager em;

    @Autowired
    public OfficeDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Office office) {
        em.persist(office);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Office> list(Long organization_id, String name, String phone, byte is_active) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Office> criteriaQuery = criteriaBuilder.createQuery(Office.class);
        Root<Office> officeRoot = criteriaQuery.from(Office.class);
        Path<Object> office_id = officeRoot.get("id");
        Path<Object> office_name = officeRoot.get("name");
        Path<Object> office_is_active = officeRoot.get("is_active");
        criteriaQuery.multiselect(office_id, office_name, office_is_active);
        criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(officeRoot.get("organization_id"), organization_id), criteriaBuilder.equal(officeRoot.get("name"), name), criteriaBuilder.equal(officeRoot.get("phone"), phone), criteriaBuilder.equal(officeRoot.get("is_active"), is_active)));
        TypedQuery<Office> query = em.createQuery(criteriaQuery);
        List<Office> list = query.getResultList();
        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Office loadById(Long id) {
        return em.find(Office.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Office update(Office office) {
        return em.merge(office);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaDelete<Office> criteriaDelete = criteriaBuilder.createCriteriaDelete(Office.class);
        Root<Office> officeRoot = criteriaDelete.from(Office.class);
        criteriaDelete.where(criteriaBuilder.equal(officeRoot.get("id"), id));
        em.createQuery(criteriaDelete).executeUpdate();
    }
}