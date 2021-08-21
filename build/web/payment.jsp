<%-- 
    Document   : payment
    Created on : Jun 29, 2021, 12:58:33 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payment Page</title>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link href="css/login.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        
        <div class="container" id="container">
            <div class="form-container sign-in-container">
                <form action="PaymentServlet?action=buy" method="post">
                    <c:if test="${message != null}"><div class="alert alert-danger" role="alert">${message}</div></c:if>
                    <h1>Checkout Form</h1>
                    <input type="text" placeholder="Address" name="address"/>
                    <c:if test="${InputMes != null and not empty InputMes[0]}" >
                     <div class="alert alert-danger" role="alert">${InputMes[0]}</div>
                    </c:if>
                    <input type="text" placeholder="Phone number" name="phoneNumber"/>
                    <c:if test="${InputMes != null and not empty InputMes[1]}" >
                     <div class="alert alert-danger" role="alert">${InputMes[1]}</div>
                    </c:if>
                     <button type="submit">Buy</button>
                </form>
            </div>
            <div class="overlay-container">
                <div class="overlay">
                    <div class="overlay-panel overlay-right">
                        <h1>Finally step!</h1>
                        <p>To complete payment please enter your info for delivery</p>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
