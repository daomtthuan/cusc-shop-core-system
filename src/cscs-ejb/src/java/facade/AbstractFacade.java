package facade;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public abstract class AbstractFacade<T> {

  private final Class<T> entityClass;

  protected AbstractFacade(Class<T> entityClass) {
    this.entityClass = entityClass;
  }

  protected abstract EntityManager getEntityManager();

  public void create(T entity) {
    getEntityManager().persist(entity);
  }

  public void edit(T entity) {
    getEntityManager().merge(entity);
  }

  public void remove(T entity) {
    getEntityManager().remove(getEntityManager().merge(entity));
  }

  public T find(Object id) {
    return getEntityManager().find(entityClass, id);
  }

  public List<T> findAll() {
    CriteriaQuery criteriaQuery = getEntityManager()
      .getCriteriaBuilder()
      .createQuery();

    Root<T> root = criteriaQuery.from(entityClass);

    criteriaQuery.select(root);

    Query query = getEntityManager().createQuery(criteriaQuery);

    return query.getResultList();
  }

  public List<T> findRange(int[] range) {
    CriteriaQuery criteriaQuery = getEntityManager()
      .getCriteriaBuilder()
      .createQuery();

    Root<T> root = criteriaQuery.from(entityClass);

    criteriaQuery.select(root);

    Query query = getEntityManager().createQuery(criteriaQuery);
    query.setMaxResults(range[1] - range[0] + 1);
    query.setFirstResult(range[0]);

    return query.getResultList();
  }

  public int count() {
    CriteriaQuery criteriaQuery = getEntityManager()
      .getCriteriaBuilder()
      .createQuery();

    Root<T> root = criteriaQuery.from(entityClass);
    criteriaQuery.select(getEntityManager().getCriteriaBuilder().count(root));

    Query query = getEntityManager().createQuery(criteriaQuery);

    return ((Long) query.getSingleResult()).intValue();
  }
}
