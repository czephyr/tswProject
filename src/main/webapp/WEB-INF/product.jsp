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

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/product.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/navResponsive.css">


    <title>${product.productName}</title>
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
                        <a href="${pageContext.request.contextPath}/accessRegistration" class="btn btn-warning">Sign-up</a>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</header>


<div class="album py-5 bg-light">

    <div class="container">
        <div class="row kustom">
            <div class="col">
                <img src="${product.productImg}" class="img-fluid" alt="...">
            </div>
            <div class="col">
                <h1 class="fw-light">${product.productName}</h1>
                <h3 class="fw-light">${product.productCategory}</h3>
                <h2 class="fw-light">${product.productPrice}$</h2>
                <h4 class="fw-light">${product.productText}</h4>

                <c:choose>
                    <c:when test="${accountSession != null}">
                        <form action="${pageContext.request.contextPath}/addToCartUser" method="get">
                            <button type="submit" class="btn btn-primary" value="${product.productID}" name="productid">
                                Add to cart
                            </button>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/accessRegistration" class="btn btn-primary">Register
                            to buy</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
        crossorigin="anonymous"></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/js/login.js'></script>
</body>
</html>