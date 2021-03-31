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
package vn.cusc.aptech.cscs.war.filters;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vn.cusc.aptech.cscs.war.session.AuthSession;

/**
 *
 * @author Daomtthuan
 */
@WebFilter(filterName = "ShopFilter", urlPatterns = {"/pages/dashboard/shop/*"})
public class ShopFilter implements Filter {

  @Inject
  private AuthSession authSession;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse res = (HttpServletResponse) response;

    String nameRole = authSession.getAccount().getRole().getName();

    if (req.getRequestURI().startsWith("/cscs-war/pages/dashboard/shop/bill/") && nameRole.equalsIgnoreCase("salesman")) {
      chain.doFilter(request, response);
    } else if (!nameRole.equalsIgnoreCase("manager")) {
      res.sendRedirect(req.getContextPath());
    } else {
      if (req.getRequestURI().startsWith("/cscs-war/pages/dashboard/shop/bill/confirm")) {
        res.sendRedirect(req.getContextPath());
      } else {
        chain.doFilter(request, response);
      }

    }

  }

  @Override
  public void destroy() {
  }

}
