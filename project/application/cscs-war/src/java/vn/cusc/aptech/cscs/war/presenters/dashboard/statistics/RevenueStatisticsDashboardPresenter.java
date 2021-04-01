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
package vn.cusc.aptech.cscs.war.presenters.dashboard.statistics;

import com.google.gson.Gson;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import vn.cusc.aptech.cscs.ejb.beans.facades.DateRevenueFacadeLocal;
import vn.cusc.aptech.cscs.ejb.entities.DateRevenue;
import vn.cusc.aptech.cscs.war.app.helpers.DateHelper;
import vn.cusc.aptech.cscs.war.app.helpers.ValidationHelper;
import vn.cusc.aptech.cscs.war.models.manager.ChartModel;
import vn.cusc.aptech.cscs.war.models.manager.DataChartModel;
import vn.cusc.aptech.cscs.war.models.manager.DatasetChartModel;
import vn.cusc.aptech.cscs.war.models.manager.OptionsChartModel;

/**
 *
 * @author Daomtthuan
 */
@Named(value = "revenueStatisticsDashboardPresenter")
@ViewScoped
public class RevenueStatisticsDashboardPresenter implements Serializable {

  @EJB
  private DateRevenueFacadeLocal dateRevenueFacade;

  @Inject
  private DateHelper dateHelper;

  private int dayFromDate;
  private int monthFromDate;
  private int yearFromDate;
  private int dayToDate;
  private int monthToDate;
  private int yearToDate;
  private int by;

  private String fromDateInputStyleClass;
  private String toDateInputStyleClass;

  private LocalDate fromDate;
  private LocalDate toDate;

  private Gson gson;

  @PostConstruct
  public void init() {
    fromDate = LocalDate.now();
    toDate = LocalDate.now();
    dayFromDate = fromDate.getDayOfMonth();
    monthFromDate = fromDate.getMonthValue();
    yearFromDate = fromDate.getYear();
    dayToDate = toDate.getDayOfMonth();
    monthToDate = toDate.getMonthValue();
    yearToDate = toDate.getYear();
    by = 0;

    fromDateInputStyleClass = null;
    toDateInputStyleClass = null;

    gson = new Gson();
  }

  public String getChart() {
    boolean fromDateValid = true;
    boolean toDateValid = true;
    try {
      fromDate = dateHelper.localDateOf(yearFromDate, monthFromDate, dayFromDate);
    } catch (Exception e) {
      fromDateValid = false;
      fromDateInputStyleClass = ValidationHelper.StyleClass.INVALID;
    }
    try {
      toDate = dateHelper.localDateOf(yearToDate, monthToDate, dayToDate);
    } catch (Exception e) {
      toDateValid = false;
      toDateInputStyleClass = ValidationHelper.StyleClass.INVALID;
    }

    if (!fromDateValid || !toDateValid) {
      return "{}";
    }

    ChartModel chartModel = new ChartModel(
      "line", // type
      new DataChartModel( // data
        new ArrayList<>(), // labels
        new ArrayList<>() // datasets
      ),
      new OptionsChartModel() // options
    );

//    List<Revenue> revenues = dateRevenueFacade.findBetween(dateHelper.dateOf(fromDate), dateHelper.dateOf(toDate.plusDays(1)));//
    List<DateRevenue> revenues = dateRevenueFacade.findAll();
    System.out.println(revenues.size());
    DatasetChartModel datasetChartModel = new DatasetChartModel( // datasets
      "Revenue statistics", // label
      "rgb(255, 99, 132)", // backgroundColor
      "rgb(255, 99, 132)", // borderColor
      revenues// data
    );
    for (DateRevenue revenue : revenues) {
      chartModel.getData().getLabels().add(dateHelper.stringDateTimeOf(revenue.getDate()));
    }
    chartModel.getData().getDatasets().add(datasetChartModel);

    return gson.toJson(chartModel);
  }

  public int getDayFromDate() {
    return dayFromDate;
  }

  public void setDayFromDate(int dayFromDate) {
    this.dayFromDate = dayFromDate;
  }

  public int getMonthFromDate() {
    return monthFromDate;
  }

  public void setMonthFromDate(int monthFromDate) {
    this.monthFromDate = monthFromDate;
  }

  public int getYearFromDate() {
    return yearFromDate;
  }

  public void setYearFromDate(int yearFromDate) {
    this.yearFromDate = yearFromDate;
  }

  public int getDayToDate() {
    return dayToDate;
  }

  public void setDayToDate(int dayToDate) {
    this.dayToDate = dayToDate;
  }

  public int getMonthToDate() {
    return monthToDate;
  }

  public void setMonthToDate(int monthToDate) {
    this.monthToDate = monthToDate;
  }

  public int getYearToDate() {
    return yearToDate;
  }

  public void setYearToDate(int yearToDate) {
    this.yearToDate = yearToDate;
  }

  public int getBy() {
    return by;
  }

  public void setBy(int by) {
    this.by = by;
  }

  public String getFromDateInputStyleClass() {
    return fromDateInputStyleClass;
  }

  public void setFromDateInputStyleClass(String fromDateInputStyleClass) {
    this.fromDateInputStyleClass = fromDateInputStyleClass;
  }

  public String getToDateInputStyleClass() {
    return toDateInputStyleClass;
  }

  public void setToDateInputStyleClass(String toDateInputStyleClass) {
    this.toDateInputStyleClass = toDateInputStyleClass;
  }

}
