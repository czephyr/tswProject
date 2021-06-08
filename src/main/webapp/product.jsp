
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">

    <link href="../css/headers.css" rel="stylesheet">

    <title>Hello, world!</title>
</head>

<body>
${accountSession.userEmail}
${accountSession.userID}
${cartItemsN}
${cart.cartProducts}
${cart.cartID}
<header class="p-3 bg-dark text-white">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
                <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap"><use xlink:href="#bootstrap"/></svg>
            </a>

            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li><a href="${pageContext.request.contextPath}/index" class="nav-link px-2 text-white">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/catalogueServlet" class="nav-link px-2 text-white">Catalogue</a></li>
                <c:choose>
                    <c:when test = "${accountSession.userIsAdmin == false}">
                        <li><a href="${pageContext.request.contextPath}/ordersPageUser" class="nav-link px-2 text-white">Orders</a></li>
                    </c:when>
                </c:choose>
            </ul>

            <c:choose>
                <c:when test = "${accountSession.userIsAdmin == false}">
                    <div class="text-end">
                        <a href="${pageContext.request.contextPath}/cartUser" class="btn btn-warning">Cart (${cartItemsN})</a>
                        <a href="${pageContext.request.contextPath}/logoutUser" class="btn btn-warning">Log Out</a>
                    </div>
                </c:when>

                <c:when test ="${accountSession.userIsAdmin == true}">
                    <div class="text-end">
                        <a href="${pageContext.request.contextPath}/adminDashboard" class="btn btn-warning">Admin Panel</a>
                        <a href="${pageContext.request.contextPath}/logoutUser" class="btn btn-warning">Log Out</a>
                    </div>
                </c:when>

                <c:otherwise>
                    <div class="row row-cols-lg-auto">
                        <div class="col-auto text-warning" id="logError"></div>
                        <div class="col-auto"><input id="email" name="email" type="email" class="form-control form-control-dark" placeholder="Email" aria-label="Search"></div>
                        <div class="col-auto"><input id="password" name="password" type="password" class="form-control form-control-dark" placeholder="Password" aria-label="Search"></div>
                        <div class="col-auto"><button class="btn btn-outline-light me-2" onclick="login()">Login</button></div>
                    </div>

                    <div class="text-end">
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
        <div class="row">
            <div class="col">
                <img src="https://via.placeholder.com/500.png" class="img-fluid" alt="...">
            </div>
            <div class="col">
                <h1 class="fw-light">${product.productName}</h1>
                <h3 class="fw-light">${product.productCategory}</h3>
                <h2 class="fw-light">${product.productPrice}$</h2>
                <h4 class="fw-light">${product.productText}</h4>

                <c:choose>
                <c:when test = "${accountSession != null}">
                    <form action="${pageContext.request.contextPath}/addToCartUser" method="get">
                        <button type="submit" class="btn" value="${product.productID}" name="productid">Add to cart</button>
                    </form>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/registration.jsp" class="btn">Register to buy</a>
                </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>

<footer class="text-muted py-5">
    <div class="container">
        <p class="float-end mb-1">
            <a href="#">Back to top</a>
        </p>
        <p class="mb-1">Album example is &copy; Bootstrap, but please download and customize it for yourself!</p>
    </div>
</footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
<script type='text/javascript' src='js/login.js'></script>
</body>
</html>