<%-- 
    Document   : detail.jsp
    Created on : Jul 6, 2021, 1:13:15 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Details Page</title>
        <link href="css/detail.css" rel="stylesheet" type="text/css"/>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <div class="container">
            <div class="row">
                <aside class="col-sm-6 border-right">
                    <article class="gallery-wrap"> 
                        <div class="img-big-wrap">
                            <div> <a href="photo/${b.getImg()}"><img src="photo/${b.getImg()}"></a></div>
                        </div> <!-- slider-product.// -->
                        <div class="img-small-wrap">
                            <div class="item-gallery"> <img src="photo/${b.getImg()}"> </div>
                            <div class="item-gallery"> <img src="photo/${b.getImg()}"> </div>
                            <div class="item-gallery"> <img src="photo/${b.getImg()}"> </div>
                            <div class="item-gallery"> <img src="photo/${b.getImg()}"> </div>
                        </div> <!-- slider-nav.// -->
                    </article> <!-- gallery-wrap .end// -->
                </aside>
                <div class="details col-sm-6">
                    <h3 class="product-title">${b.getBookName()}</h3>
                    <p class="product-description">${b.getDescription()}</p>
                    <h4 class="price">current price: <span>$${b.getPrice()}</span></h4>
                    <p class="vote"><strong>91%</strong> of buyers enjoyed this book!</p>
                    <h5>Author:
                        <span>${b.getAuthor()}</span>
                    </h5>
                    <h5>Year:
                        <span>${b.getYear()}</span>
                    </h5>
                    <button ${user.getRole() eq 1 ? "hidden" : ""}>
                        <a href="DispatchServlet?btAction=Add&id=${b.getBookId()}">add to cart</a>
                    </button>
                </div>
            </div>
        </div>           
    </body>
</html>
