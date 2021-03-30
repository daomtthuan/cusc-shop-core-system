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
package vn.cusc.aptech.cscs.war.models.shipper;

import java.util.List;
import vn.cusc.aptech.cscs.war.models.Model;

/**
 *
 * @author Daomtthuan
 */
public class OrderBillCustomerModel implements Model {

  private List<OrderBillDetailsCustomerModel> billDetails;

  public OrderBillCustomerModel() {
  }

  public OrderBillCustomerModel(List<OrderBillDetailsCustomerModel> billDetails) {
    this.billDetails = billDetails;
  }

  public List<OrderBillDetailsCustomerModel> getBillDetails() {
    return billDetails;
  }

  public void setBillDetails(List<OrderBillDetailsCustomerModel> billDetails) {
    this.billDetails = billDetails;
  }

  @Override
  public boolean isEmpty() {
    if (billDetails == null || billDetails.isEmpty()) {
      return false;
    }

    return billDetails.stream().noneMatch(billDetail -> (billDetail.isEmpty()));
  }

}
