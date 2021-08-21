<%-- 
    Document   : create
    Created on : Jul 3, 2021, 12:08:33 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Page</title>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link href="css/create.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <div class="container" id="container" >
                <form action="CreateServlet" method="post" enctype="multipart/form-data">
                    <h1>Create A New Book</h1>
                    <input type="text" placeholder="Book Id (example: B001)" name="id"/>
                    <c:if test="${not empty message or not empty ErrCreateMsg[1]}" >
                    <div class="alert alert-danger" role="alert">
                        ${message}          
                        ${ErrCreateMsg[1]}
                    </div>
                    </c:if>
                <input id="file-upload" type="file" name="photo"/>
                    <c:if test="${not empty ErrCreateMsg[0]}" >
                    <div class="alert alert-danger" role="alert">
                        ${ErrCreateMsg[0]}
                    </div>
                    </c:if>
                <input type="text" placeholder="Book Name" name="name"/>
                <c:if test="${not empty message or not empty ErrCreateMsg[2]}" >
                <div class="alert alert-danger" role="alert">
                        ${message}                 
                        ${ErrCreateMsg[2]}                  
                </div>
                </c:if>
                <textarea placeholder="Description" name="description"></textarea>
                <c:if test="${not empty message or not empty ErrCreateMsg[3]}" >
                <div class="alert alert-danger" role="alert">
                        ${message}                
                        ${ErrCreateMsg[3]}                  
                </div>
                </c:if>
                <input type="number" name="quantity" placeholder="Quantity"/>
                <c:if test="${not empty message or not empty ErrCreateMsg[4]}" >
                <div class="alert alert-danger" role="alert">
                        ${message}                 
                        ${ErrCreateMsg[4]}                  
                </div>
                </c:if>
                <input type="number" name="year" placeholder="Year Of Production"/>
                <c:if test="${not empty message or not empty ErrCreateMsg[5]}" >
                <div class="alert alert-danger" role="alert">
                        ${message}
                        ${ErrCreateMsg[5]}                   
                </div>
                </c:if>
                <input type="text" name="author" placeholder="Author"/>
                <c:if test="${not empty message or not empty ErrCreateMsg[6]}" >
                <div class="alert alert-danger" role="alert">
                        ${message}                   
                        ${ErrCreateMsg[6]}                  
                </div>
                </c:if>
                <input type="text" name="price" placeholder="Price"/>
                <c:if test="${not empty message or not empty ErrCreateMsg[7]}" >
                <div class="alert alert-danger" role="alert">
                        ${message}
                        ${ErrCreateMsg[7]}                   
                </div>
                </c:if>
                <select name="category">
                    <c:forEach items="${listCategory}" var="c">
                        <option value="${c.getId()}" ${(c.getId() eq b.getCategoryID().getId()) ? "selected" : ""}>
                            ${c.getName()}
                        </option>
                    </c:forEach>   
                </select>
                <button value="Create" type="submit">Create</button>
            </form>
        </div>
    </body>
</html>
