/*
 * The MIT License
 *
 * Copyright 2021 Daomtthuan.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package vn.cusc.aptech.cscs.ejb.beans.facades;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import vn.cusc.aptech.cscs.ejb.entities.Employee;

/**
 *
 * @author Daomtthuan
 */
@Stateless
public class EmployeeFacade extends AbstractFacade<Employee> implements EmployeeFacadeLocal {

  @PersistenceContext(unitName = "cscs-ejbPU")
  private EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public EmployeeFacade() {
    super(Employee.class);
  }

  @Override
  public Employee findByUsername(String username) {
    List<Employee> employees = em.createNamedQuery("Employee.findByUsername").setParameter("username", username).getResultList();
    return employees.isEmpty() ? null : employees.get(0);
  }

  @Override
  public Employee findShipperByUsername(String username) {
    List<Employee> employees = em.createQuery("SELECT e FROM Employee e WHERE e.username = :username AND e.role.id = :idRole")
      .setParameter("username", username)
      .setParameter("idRole", 4)
      .getResultList();
    return employees.isEmpty() ? null : employees.get(0);
  }

  @Override
  public List<Employee> findByFilter(Object idRole) {
    List<Employee> employees = em.createQuery("SELECT e FROM Employee e WHERE e.role.id = :idRole OR :idRole = 0")
      .setParameter("idRole", idRole)
      .getResultList();
    return employees;
  }

  @Override
  public List<Employee> findAllShipper() {
    return em.createQuery("SELECT e FROM Employee e WHERE e.role.id = :idRole")
      .setParameter("idRole", 4)
      .getResultList();
  }

}
