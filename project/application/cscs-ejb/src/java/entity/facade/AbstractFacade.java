package entity.facade;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 * Facade
 * @author Daomtthuan
 * @param <T> Entity class
 */
public abstract class AbstractFacade<T> {

  /** Entity class */
  private final Class<T> entityClass;

  /**
   * Initialize Facade
   * @param entityClass Entity class
   */
  public AbstractFacade(Class<T> entityClass) {
    this.entityClass = entityClass;
  }

  /**
   * Get Entity Manager
   * @return Entity Manager
   */
  protected abstract EntityManager getEntityManager();

  /**
   * Create Entity
   * @param entity Created Entity
   */
  public void create(T entity) {
    getEntityManager().persist(entity);
  }

  /**
   * Edit Entity
   * @param entity Edited Entity
   */
  public void edit(T entity) {
    getEntityManager().merge(entity);
  }

  /**
   * Remove Entity
   * @param entity Removed Entity
   */
  public void remove(T entity) {
    getEntityManager().remove(getEntityManager().merge(entity));
  }

  /**
   * Find Entity by id object
   * @param id Entity id object
   * @return Entity if found, null otherwise
   */
  public T find(Object id) {
    return getEntityManager().find(entityClass, id);
  }

  /**
   * Find Entity by id string
   * @param id Entity id string
   * @return Entity if found, null otherwise
   */
  public T findID(String id) {
    return getEntityManager().find(entityClass, id);
  }

  /**
   * Find all Entities
   * @return Entities list
   */
  public List<T> findAll() {
    CriteriaQuery query
      = getEntityManager().getCriteriaBuilder().createQuery();
    query.select(query.from(entityClass));

    return getEntityManager().createQuery(query).getResultList();
  }

  public List<T> findRange(int[] range) {
    CriteriaQuery query
      = getEntityManager().getCriteriaBuilder().createQuery();
    query.select(query.from(entityClass));
    Query q = getEntityManager().createQuery(query);
    q.setMaxResults(range[1] - range[0] + 1);
    q.setFirstResult(range[0]);

    return q.getResultList();
  }

  public int count() {
    CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();

    javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
    cq.select(getEntityManager().getCriteriaBuilder().count(rt));
    javax.persistence.Query q = getEntityManager().createQuery(cq);
    return ((Long) q.getSingleResult()).intValue();
  }

}
