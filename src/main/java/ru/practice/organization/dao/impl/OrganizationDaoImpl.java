package ru.practice.organization.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.practice.organization.dao.OrganizationDao;
import ru.practice.organization.model.Organization;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class OrganizationDaoImpl implements OrganizationDao {

    private final EntityManager em;

    @Autowired
    public OrganizationDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Organization organization) {
        em.persist(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Organization> list(String name, String inn, byte is_active) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Organization> criteriaQuery = criteriaBuilder.createQuery(Organization.class);
        Root<Organization> organizationRoot = criteriaQuery.from(Organization.class);
        Path<Object> org_id = organizationRoot.get("id");
        Path<Object> org_name = organizationRoot.get("name");
        Path<Object> org_is_active = organizationRoot.get("is_active");
        criteriaQuery.multiselect(org_id, org_name, org_is_active);
        criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(organizationRoot.get("name"), name), criteriaBuilder.equal(organizationRoot.get("inn"), inn), criteriaBuilder.equal(organizationRoot.get("is_active"), is_active)));
        TypedQuery<Organization> query = em.createQuery(criteriaQuery);
        List<Organization> allList = query.getResultList();
        return allList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization loadById(Long id) {
        return em.find(Organization.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Organization organization) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaUpdate<Organization> update = criteriaBuilder.createCriteriaUpdate(Organization.class);
        Root<Organization> organizationRoot = update.from(Organization.class);

        update
                .set("NAME", organization.getName())
                .set("full_name", organization.getFull_name())
                .set("inn", organization.getInn())
                .set("kpp", organization.getKpp())
                .set("ADDRESS", organization.getAddress())
                .set("phone", organization.getPhone())
                .set("is_active", organization.isIs_active());
        update.where(criteriaBuilder.equal(organizationRoot.get("id"), organization.getId()));
        em.createQuery(update).executeUpdate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaDelete<Organization> criteriaDelete = criteriaBuilder.createCriteriaDelete(Organization.class);
        Root<Organization> organizationRoot = criteriaDelete.from(Organization.class);
        criteriaDelete.where(criteriaBuilder.equal(organizationRoot.get("id"), id));
        em.createQuery(criteriaDelete).executeUpdate();
    }
}
