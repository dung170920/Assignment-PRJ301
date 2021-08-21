<%-- 
    Document   : user
    Created on : Jun 23, 2021, 9:15:34 PM
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
        <title>Admin Page</title>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <style>

            #form-update {
                height: auto;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>

            <div class="container-fluid bg-gradient p-5">
                <div class="row m-auto text-center">
                    <aside class="col-md-3">
                        <div class="card">
                            <article class="filter-group">
                                <header class="card-header">
                                    <h6 class="title">
                                        <a class="list-group-item list-group-item-action" href="DispatchServlet?btAction=CreateForm">Add a new book</a>
                                    </h6>
                                </header>
                            </article>
                            <article class="filter-group">
                                <header class="card-header">
                                    <h6 class="title">Category </h6>
                                </header>
                                <div class="filter-content collapse show" id="collapse_3" style="">
                                    <div class="card-body list-group-flush">
                                    <c:forEach items="${listCategory}" var="c">
                                        <a class="list-group-item list-group-item-action ${tag == c.getId() ? "active" : ""}" 
                                           href="DispatchServlet?btAction=Category&cid=${c.getId()}">${c.getName()}</a>
                                    </c:forEach>
                                </div>  
                            </div>
                        </article>
                        <article class="filter-group">
                            <header class="card-header">
                                <h6 class="title">Search </h6>
                            </header>
                            <div class="filter-content collapse show" id="collapse_3" style="">
                                <div class="card-body">
                                    <form action="DispatchServlet" method="post">
                                        <input value="${txtSearch}" type="text" name="txtSearch">
                                        <button name="btAction" value="Search" type="submit">Search</button>
                                    </form>
                                </div>  
                            </div>
                        </article>
                        <article class="filter-group">
                            <header class="card-header">
                                <h6 class="title">Price range</h6>
                            </header>
                            <div class="filter-content collapse show" id="collapse_3" style="">
                                <div class="card-body">
                                    <form action="DispatchServlet" method="post">
                                        <div class="row">
                                            <div class="form-group col-md-6">
                                                <label>Min</label>
                                                <input class="form-control" placeholder="$0" type="number" name="min" value="${min}">
                                            </div>
                                            <div class="form-group text-right col-md-6">
                                                <label>Max</label>
                                                <input class="form-control" placeholder="$10000" type="number" name="max" value="${max}">
                                            </div>
                                        </div>
                                            <button name="btAction" value="Search" type="submit">Search</button>
                                    </form>
                                </div>
                            </div>
                        </article>
                    </div> 
                </aside>
                <div class="col-md-9">
                    <div class="row">
                        <c:if test="${message != null}"><div class="alert alert-danger" role="alert">${message}</div></c:if>
                        <c:forEach items="${listBook}" var="b">
                            <div class="col-md-4" id="form-update">
                                <form action="UpdateServlet?id=${b.getBookId()}&i=${index}" method="post" enctype="multipart/form-data">
                                    <div class="pricing-divider">
                                        <h4>${b.getBookId()}</h4>
                                        <img src="photo/${b.getImg()}" height="200" width="200"/>
                                        <input id="file-upload" type="file" name="photo"/>
                                    </div>
                                    <div class="card-body bg-white mt-0 shadow" id="form-update">
                                        <input type="text" name="name" value="${b.getBookName()}"/>
                                        <ul class="list-unstyled mb-5 position-relative">
                                            <li><b>Price</b><input type="text" name="price" value="${b.getPrice()}"/></li>
                                            <li><b>Author </b><input type="text" name="author" value="${b.getAuthor()}"/></li>
                                            <li><b>Year </b><input type="number" name="year" value="${b.getYear()}"/></li>
                                            <li><b>Quantity </b><input type="number" name="quantity" value="${b.getQuantity()}"/></li>
                                            <li><b>Description </b><textarea name="description">${b.getDescription()}</textarea></li>
                                            <li>
                                                <select name="category">
                                                    <c:forEach items="${listCategory}" var="c">
                                                        <option value="${c.getId()}" ${(c.getId() eq b.getCategoryID().getId()) ? "selected" : ""}>
                                                            ${c.getName()}
                                                        </option>
                                                    </c:forEach>   
                                                </select>
                                            </li>
                                        </ul>
                                        <button value="Update" type="submit">Edit</button>
                                        <button><a href="DispatchServlet?btAction=Delete&id=${b.getBookId()}"
                                           onclick="return confirm('Are you sure to delete?')">Delete</a></button>
                                    </div>
                                    <br>
                                </form>
                            </div>
                        </c:forEach>
                        <nav aria-label="Page navigation example">
                            <ul class="pagination justify-content-center">
                                <c:set scope="request" var="pre" value="${(index-1 <= 1) ? 1 : (index-1)}"/>
                                <li class="page-item">
                                    <a class="page-link" href="DispatchServlet?btAction=Admin&i=${pre}">Previous</a>
                                </li>
                                <c:forEach begin="1" end="${endPage}" var="i">
                                    <li class="page-item ${index == i ? "active" : ""}">
                                        <a class="page-link" href="DispatchServlet?btAction=Admin&i=${i}">${i}</a>
                                    </li>
                                </c:forEach>
                                <c:set scope="request" var="next" value="${(index+1 >= endPage) ? endPage : (index+1)}"/>
                                <li class="page-item">
                                    <a class="page-link" href="DispatchServlet?btAction=Admin&i=${next}">Next</a>
                                </li>    
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
