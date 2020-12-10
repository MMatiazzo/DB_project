<%-- 
    Document   : head
    Created on : 7 de dez. de 2020, 14:08:56
    Author     : mathe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
     <header>
     <div class="container">
      <div class="row">
        <div id="header">
          <div class="header-container">
            <div class="header-logo"> <a href="${pageContext.servletContext.contextPath}/fluxo/grid/" title="Car HTML" class="logo">
              <div><img src="${pageContext.servletContext.contextPath}/images/logo.png" alt="Car Store"></div>
              </a> </div>
            <div class="header__nav">
              <div class="fl-header-right">
                <div class="fl-links">
                  <div class="no-js"> <a title="" class="clicker"></a>
                    <div class="fl-nav-links">
                       <h3>My Acount</h3>
                      <ul class="links">
                        <li><a href="${pageContext.servletContext.contextPath}/fluxo/perfil.jsp" title="Profile">Profile</a></li>
                        <li><a href="${pageContext.servletContext.contextPath}/logout" title="Logout">Logout</a></li>
                        </ul>
                    </div>
                  </div>
                </div>
                <!--mini-cart-->
                <div>
                  <form class="navbar-form" role="search" action="${pageContext.servletContext.contextPath}/fluxo/search" method="GET">
                    <div class="input-group">
                      <input type="text" name="search_by" class="form-control" placeholder="Search">
                      <span class="input-group-btn">
                      <button type="submit" class="search-btn"> <span class="glyphicon glyphicon-search"> <span class="sr-only">Search</span> </span> </button>
                      </span> </div>
                  </form>
                </div>
                <!--links--> 
              </div>
              <div class="fl-nav-menu">
                <nav>
                  <div class="mm-toggle-wrap">
                    <div class="mm-toggle"><i class="fa fa-bars"></i><span class="mm-label">Menu</span> </div>
                  </div>
                  <div class="nav-inner"> 
                    <!-- BEGIN NAV -->
                    <ul id="nav" class="hidden-xs">
                      <li class="active"> <a class="level-top" href="http://localhost:8080/bdapp/fluxo/grid"><span>Home</span></a></li>
                      <li class="level0 parent drop-menu"> <a class="level-top" href="#"><span>Listing‎</span></a>
                        <ul class="level1">
                          <li class="level1 first"><a href="fluxo/grid.jsp"><span>Car Grid</span></a></li>
                          <li class="level1 nav-10-2"> <a href="fluxo/list.html"> <span>Car List</span> </a> </li>
                          <li class="level1 nav-10-3"> <a href="fluxo/grid.jsp"> <span>Accessories Grid</span> </a> </li>
                          <li class="level1 nav-10-4"> <a href="fluxo/list1.html"> <span>Accessories List</span> </a> </li>
                          <li class="level1 first parent"><a href="fluxo/car-detail.html"><span>Car Detail</span></a> </li>
                          <li class="level1 first parent"><a href="fluxo/accessories-detail.html"><span>Accessories Detail</span></a> </li>
                        </ul>
                      </li>
                      <li class="level0 parent drop-menu"><a href="#"><span>Pages</span> </a> 
                        <!--sub sub category-->
                        <ul class="level1">
                          <li class="level1"> <a href="fluxo/about-us.html"> <span>About us</span> </a> </li>
                          <li class="level1 nav-10-4"> <a href="fluxo/shopping-cart.html"> <span>Cart Page</span> </a> </li>
                          <li class="level1 first parent"><a href="fluxo/checkout.html"><span>Checkout</span></a> 
                            <!--sub sub category-->
                            <ul class="level2 right-sub">
                              <li class="level2 nav-2-1-1 first"><a href="fluxo/checkout-method.html"><span>Method</span></a></li>
                              <li class="level2 nav-2-1-5 last"><a href="fluxo/checkout-billing-info.html"><span>Billing Info</span></a></li>
                            </ul>
                            <!--sub sub category--> 
                          </li>
                          <li class="level1 nav-10-4"> <a href="fluxo/wishlist.html"> <span>Wishlist</span> </a> </li>
                          <li class="level1"> <a href="fluxo/dashboard.html"> <span>Dashboard</span> </a> </li>
                          <li class="level1"> <a href="fluxo/multiple-addresses.html"> <span>Multiple Addresses</span> </a> </li>
                          <li class="level1"><a href="fluxo/contact-us.html"><span>Contact us</span></a> </li>
                          <li class="level1"><a href="fluxo/404error.html"><span>404 Error Page</span></a> </li>
                          <li class="level1"><a href="fluxo/login.html"><span>Login Page</span></a> </li>
                          <li class="level1"><a href="fluxo/quickview.html"><span>Quick View</span></a> </li>
                          <li class="level1"><a href="fluxo/newsletter.html"><span>Newsletter</span></a> </li>
                        </ul>
                      </li>
                    </ul>
                    <!--nav--> 
                  </div>
                </nav>
              </div>
            </div>
            
            <!--row--> 
            
          </div>
        </div>
      </div>
    </div>
  </header>
</html>
