
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



${cart.cartProducts}
<div class="album py-5 bg-light">

    <div class="container">
        <h1 class="fw-light">What's in your cart currently</h1>
        <div class="container">
    <c:choose>
    <c:when test = "${cartItemsN == 0}">
        Your cart is empty.
    </c:when>
        <c:otherwise>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Name</th>
                    <th scope="col">Price</th>
                    <th scope="col">Quantity</th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items = "${cart.cartProducts}" var ="product">
                    <tr>
                        <th scope='row'>${product.productID}</th>
                        <td>${product.productName}</td>
                        <td>${product.productPrice}$</td>
                        <td>${product.productQuantity}</td>
                        <td></td>
                        <td><form action="${pageContext.request.contextPath}/removeFromCartUser" method="get">

                            <button type="submit" class="btn-close" aria-label="Close" value="${product.productID}" name="productid"></button></form>
                        </td>
                    </tr>
                </c:forEach>
                    <tr><td></td><td></td><td><strong>Total:</strong> ${total}$</td><td></td><td></td></tr>
                </tbody>
            </table>
        </div>
        <a href="${pageContext.request.contextPath}/buyCartUser" class="btn btn-primary">Buy the items in your cart</a>
    </div>
    </c:otherwise>

</c:choose>

        </div>

<footer class="text-muted py-5">
    <div class="container">
        <p class="float-end mb-1">
            <a href="#">Back to top</a>
        </p>
        <p class="mb-1">Album example is &copy; Bootstrap, but please download and customize it for yourself!</p>
        <p class="mb-0">New to Bootstrap? <a href="/">Visit the homepage</a> or read our <a href="{{< docsref "/getting-started/introduction" >}}">getting started guide</a>.</p>
    </div>
</footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
</body>
</html>
