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
            <div class="header-logo"> <a href="/bdapp" title="Car HTML" class="logo">
              <div><img src="images/logo.png" alt="Car Store"></div>
              </a> </div>
            <div class="header__nav">
              <div class="fl-header-right">
                <div class="fl-links">
                  <div class="no-js"> <a title="" class="clicker"></a>
                    <div class="fl-nav-links">
                       <h3>My Acount</h3>
                      <ul class="links">
                        <li><a href="${pageContext.servletContext.contextPath}/fluxo/perfil.jsp" title="Profile">Profile</a></li>
                        <li><a href="${pageContext.servletContext.contextPath}/carteira" title="Logout">Carteira</a> $ 700</li>
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
