<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/navResponsive.css">
    <title>Hello, world!</title>
</head>

<body class="bg-light">
<header class="p-3 bg-dark text-white">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li><a href="${pageContext.request.contextPath}/index" class="nav-link px-2 text-white">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/catalogueServlet" class="nav-link px-2 text-white">Catalogue</a>
                </li>
                <c:choose>
                    <c:when test="${accountSession.userIsAdmin == false}">
                        <li><a href="${pageContext.request.contextPath}/ordersPageUser"
                               class="nav-link px-2 text-white">Orders</a></li>
                    </c:when>
                </c:choose>
            </ul>

            <c:choose>
                <c:when test="${accountSession.userIsAdmin == false}">
                    <div class="text-end">
                        <a href="${pageContext.request.contextPath}/cartUser" class="btn btn-warning">Cart
                            (${cartItemsN})</a>
                        <a href="${pageContext.request.contextPath}/logoutUser" class="btn btn-warning">Log Out</a>
                    </div>
                </c:when>

                <c:when test="${accountSession.userIsAdmin == true}">
                    <div class="text-end">
                        <a href="${pageContext.request.contextPath}/adminDashboard" class="btn btn-warning">Admin
                            Panel</a>
                        <a href="${pageContext.request.contextPath}/logoutUser" class="btn btn-warning">Log Out</a>
                    </div>
                </c:when>

                <c:otherwise>
                    <div class="row row-cols-lg-auto custom" id="logForm">
                        <div class="col-auto text-warning custom2" id="logError"></div>
                        <div class="col-auto custom2"><input id="email" name="email" type="email"
                                                             class="form-control form-control-dark" placeholder="Email"
                                                             aria-label="Search"></div>
                        <div class="col-auto custom2"><input id="password" name="password" type="password"
                                                             class="form-control form-control-dark" placeholder="Password"
                                                             aria-label="Search"></div>
                        <div class="col-auto custom2">
                            <button class="btn btn-outline-light me-2" onclick="login()">Login</button>
                        </div>
                    </div>

                    <div class="text-end" id="signForm">
                        or
                        <a href="registration.jsp" class="btn btn-warning">Sign-up</a>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</header>


<div class="album py-5 bg-light">

    <div class="container">
        <h1 class="fw-light">Products</h1>
        <div class="container">
            <table class="table caption-top">
                <caption><a href="${pageContext.request.contextPath}/adminAccessAddProduct">Add a product</a></caption>
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Name</th>
                    <th scope="col">Price</th>
                    <th scope="col">Quantity</th>
                    <th scope="col">Category</th>
                    <th scope="col">Image</th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <jsp:useBean id="products" scope="request" type="java.util.List"/>
                <c:forEach items="${products}" var="product">
                    <tr>
                        <th scope='row'>${product.productID}</th>
                        <td>${product.productName}</td>
                        <td>${product.productPrice}$</td>
                        <td>${product.productQuantity}</td>
                        <td>${product.productCategory}</td>
                        <td></td>
                        <td>
                            <form action="${pageContext.request.contextPath}/adminDeleteProduct" method="get">

                                <button type="submit" class="btn-close" aria-label="Close" value="${product.productID}"
                                        name="productid"></button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <h1 class="fw-light">Users</h1>
        <div class="container">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Email</th>
                    <th scope="col">Name</th>
                    <th scope="col">Surname</th>
                    <th scope="col">Admin</th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <jsp:useBean id="users" scope="request" type="java.util.List"/>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <th scope='row'>${user.userID}</th>
                        <td>${user.userEmail}</td>
                        <td>${user.userName}</td>
                        <td>${user.userSurname}</td>
                        <td><c:choose>
                            <c:when test="${user.userIsAdmin}">
                                YES
                            </c:when>
                            <c:otherwise>
                                NO
                            </c:otherwise>
                        </c:choose></td>
                        <td>
                            <form action="${pageContext.request.contextPath}/adminDeleteUser" method="get">

                                <button type="submit" class="btn-close" aria-label="Close" value="${user.userID}"
                                        name="userid"></button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
        crossorigin="anonymous"></script>
</body>
</html>
