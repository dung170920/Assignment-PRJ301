<%-- 
    Document   : history
    Created on : Jul 6, 2021, 12:50:09 PM
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
        <title>Payment History Page</title>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link href="css/create.css" rel="stylesheet" type="text/css"/>
        <style>
            body {
                text-align: center;
            }
            .container {
                width: 1150px;
            }
        </style>
    </head>

    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <div class="container">
                <div class="row">
                <c:if test="${message != null}"><div class="alert alert-danger" role="alert">${message}</div></c:if>
                    <div class="col-md-9">
                        <table class="table table-striped table-condensed">
                            <thead>
                                <tr>
                                    <th></th>
                                    <th>Payment Id</th>
                                    <th>Date created</th>
                                    <th>Address</th>
                                    <th>Phone Number</th>
                                    <th>Status</th>                                          
                                </tr>
                            </thead>   
                            <tbody>
                            <c:forEach items="${listPayment}" var="p">
                                <tr>
                                    <td>
                                        <button>
                                            <a href="DispatchServlet?btAction=HistoryDetail&id=${p.getPaymentId()}">View detail</a>
                                        </button>
                                    </td>
                                    <td>${p.getPaymentId()}</td>
                                    <td>${p.getDateCreate()}</td>
                                    <td>${p.getAddress()}</td>
                                    <td>${p.getPhoneNumber()}</td>
                                    <c:if test="${p.getStatus()==0}"><td><span>Success</span></td></c:if>
                                    <c:if test="${p.getStatus()==1}"><td><span>Fail</span></td></c:if>                                      
                                    </tr>
                            </c:forEach>                 
                        </tbody>
                    </table>
                </div>
                <div class="col-md-3">
                    <c:if test="${listDetail != null}">
                        <table class="table table-striped table-condensed">
                            <thead>
                                <tr>
                                    <th>Book Name</th>
                                    <th>Quantity</th>
                                    <th>SubTotal</th>                                      
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${listDetail}" var="d">
                                    <tr>
                                        <th>${d.getBook().getBookName()}</th>
                                        <th>${d.getQuantity()}</th>
                                        <th>${d.getSubTotal()}</th>                                      
                                    </tr>
                                </c:forEach>
                        </table>
                    </c:if>
                </div>
            </div>
        </div>
    </body>
</html>
