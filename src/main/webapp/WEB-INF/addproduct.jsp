<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">

    <link href="css/headers.css" rel="stylesheet">

    <title>Hello, world!</title>
</head>

<body>
${accountSession.userEmail}
${accountSession.userIsAdmin}
<header class="p-3 bg-dark text-white">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
                <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap"><use xlink:href="#bootstrap"/></svg>
            </a>

            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li><a href="#" class="nav-link px-2 text-white">Home</a></li>
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
                    <form action="${pageContext.request.contextPath}/loginUser" method="post" class="row row-cols-lg-auto">
                        <div class="col-auto"><input name="email" type="email" class="form-control form-control-dark" placeholder="Email" aria-label="Search"></div>
                        <div class="col-auto"><input name="password" type="password" class="form-control form-control-dark" placeholder="Password" aria-label="Search"></div>
                        <div class="col-auto"><button type="submit" class="btn btn-outline-light me-2">Login</button></div>
                    </form>

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
        <h1 class="fw-light">Add a new product</h1>
        <form action="${pageContext.request.contextPath}/adminAddProduct" method="get" class="position-relative">
            <div class="mb-3">
                <label for="name" class="form-label">Name</label>
                <input type="text" name="name" class="form-control" id="name" aria-describedby="emailHelp">
            </div>
            <div class="mb-3">
                <label for="price" class="form-label">Price</label>
                <input type="number" name="price" class="form-control" id="price" aria-describedby="emailHelp">
            </div>
            <div class="mb-3">
                <label for="description" class="form-label">Description</label>
                <input type="text" name="description" class="form-control" id="description" aria-describedby="emailHelp">
            </div>
            <div class="mb-3">
                <label for="quantity" class="form-label">Quantity</label>
                <input type="number" name="quantity" class="form-control" id="quantity">
            </div>
            <div class="mb-3">
                <label for="category" class="form-label">Category</label>
                <input type="text" name="category" class="form-control" id="category">
            </div>
            <button type="submit" class="btn btn-primary">Create</button>
        </form>
    </div>
</div>

</main>

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