<%-- 
    Document   : login
    Created on : Jun 22, 2021, 2:01:53 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/login.css" rel="stylesheet" type="text/css"/>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <title>Sign In/Sign Up Page</title>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <div class="container ${context.contains("SignUpServlet") ? "right-panel-active" : ""}" id="container" >
            <div class="form-container sign-up-container">
               <form action="DispatchServlet" method="post">
                    <h2>Create Account</h2>
                    <input type="text" placeholder="Full Name" name="fullname"/>
                    <c:if test="${not empty ErrSignUp[0]}" >
                        <div class="alert alert-danger" role="alert">${ErrSignUp[0]}</div>
                    </c:if>
          
                    <input type="text" placeholder="UserName" name="signUpUsername"/>
                    <c:if test="${not empty ErrSignUp[1]}" >
                        <div class="alert alert-danger" role="alert">${ErrSignUp[1]}</div>
                    </c:if>
        
                    <input type="password" placeholder="Password" name="signUpPassword"/>
                    <c:if test="${not empty ErrSignUp[2]}" >
                        <div class="alert alert-danger" role="alert">${ErrSignUp[2]}</div>
                    </c:if>
     
                    <input type="password" placeholder="Confirm Password" name="confirmPass" />  
                    <c:if test="${not empty ErrSignUp[3]}" >
                        <div class="alert alert-danger" role="alert">${ErrSignUp[3]}</div>
                    </c:if>
                    
                    <c:if test="${not empty ErrDfgSignUp}" >
                        <div class="alert alert-danger" role="alert">${ErrDfgSignUp}</div>
                    </c:if>
                    <button name="btAction" type="submit" value="SignUp">Sign Up</button>
                </form>
            </div>
            <div class="form-container sign-in-container">
                <form action="DispatchServlet" method="post">
                    <h2>Sign in</h2>
                    <input type="text" placeholder="UserName" name="username"/>
                    <input type="password" placeholder="Password" name="password"/>
                    <c:if test="${not empty message}">
                        <div class="alert alert-danger" role="alert">${message}</div>
                    </c:if>
                    <button name="btAction" value="Login" type="submit">Sign In</button>
                </form>
            </div>
            <div class="overlay-container">
                <div class="overlay">
                    <div class="overlay-panel overlay-left">
                        <h1>Welcome Back!</h1>
                        <p>To keep connected with us please login with your personal info</p>
                        <button class="ghost" onclick="signIn()"> Sign In</button>
                    </div>
                    <div class="overlay-panel overlay-right">
                        <h1>Hello, Friend!</h1>
                        <p>Enter your personal details and start journey with us</p>
                        <button class="ghost" onclick="signUp()">Sign Up</button>
                    </div>
                </div>
            </div>
        </div>
        <script>
            function signUp() {
                document.getElementById('container').classList.add("right-panel-active");
            }
            function signIn() {
                document.getElementById('container').classList.remove("right-panel-active");
            }
        </script>
    </body>
</html>
