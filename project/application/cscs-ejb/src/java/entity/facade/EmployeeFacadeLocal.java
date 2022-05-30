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
package entity.facade;

import java.util.List;
import javax.ejb.Local;
import entity.Employee;

/**
 *
 * @author Daomtthuan
 */
@Local
public interface EmployeeFacadeLocal {

  void create(Employee employee);

  void edit(Employee employee);

  void remove(Employee employee);

  Employee find(Object id);

  List<Employee> findAll();

  List<Employee> findRange(int[] range);

  int count();

  Employee findByUsername(String username);

  Employee findNotShipperByUsername(String username);

  List<Employee> findByFilter(Object idRole);

  Employee findShipperByUsername(String username);

  List<Employee> findAllShipper();

}
