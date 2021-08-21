<%-- 
    Document   : header
    Created on : Jul 7, 2021, 4:33:17 PM
    Author     : ASUS
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<header>
    <nav class="navbar navbar-light navbar-expand-md justify-content-center fixed-top">
        <div class="navbar-collapse collapse justify-content-between align-items-center">
            <ul class="topBotomBordersOut navbar-nav mx-auto text-center">
                <li class="nav-item"><a class="nav-link" href="DispatchServlet?btAction=User">Home</a></li>
                    <c:if test="${user != null}">
                    <li class="nav-item"><a class="nav-link" href="DispatchServlet?btAction=Info">Hello ${user.getFullname()}</a></li>
                    <li class="nav-item"><a class="nav-link" href="DispatchServlet?btAction=History">History Payment</a></li>
                    <li class="nav-item"><a class="nav-link" href="DispatchServlet?btAction=Logout">Logout</a></li>
                        <c:if test="${user.getRole() == 0}">
                        <li class="nav-item">
                            <a class="nav-link" href="DispatchServlet?btAction=Cart">
                                <i style='font-size:20px' class='fas fa-shopping-cart'></i> Cart
                                <span class="item-number ">${(cart.size() eq 0 || cart == null) ? 0 : cart.size()}</span>
                            </a>   
                        </li>
                    </c:if>
                    <c:if test="${user.getRole() == 1}">
                        <li class="nav-item"><a class="nav-link" href="DispatchServlet?btAction=Admin">Manage Book</a></li>
                        </c:if>
                    </c:if>
                <c:if test="${user == null}">
                    <li class="nav-item"><a class="nav-link" href="DispatchServlet">Sign Up/Sign In</a></li>
                    </c:if>
            </ul>
        </div>
    </nav>
</header>
