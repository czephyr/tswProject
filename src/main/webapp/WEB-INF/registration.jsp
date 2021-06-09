<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                        <a href="${pageContext.request.contextPath}/accessRegistration" class="btn btn-warning">Sign-up</a>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</header>


<div class="album py-5 bg-light">

    <div class="container position-relative">
        <h1 class="fw-light">Register</h1>
        <div class="mb-3">
            <label for="name" class="form-label">Name</label>
            <input type="text" class="form-control" name="name" id="name" required>
            <div class="invalid-feedback" id="nameInvalid" hidden>
                Name must be longer than 3 characters
            </div>
        </div>
        <div class="mb-3">
            <label for="surname" class="form-label">Surname</label>
            <input type="text" class="form-control" name="surname" id="surname" required>
            <div class="invalid-feedback" id="surnameInvalid" hidden>
                Surname must be longer than 3 characters
            </div>
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email address</label>
            <input type="email" class="form-control" name="email" id="regEmail" required>
            <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
            <div class="invalid-feedback" id="emailInvalid" hidden>
                Please insert a valid email.
            </div>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input type="password" class="form-control" id="regPassword" name="password" required>
            <div class="invalid-feedback" id="passwordInvalid" hidden>
                Your password must be 6 characters long and have at least 1 upper case, 1 lower case, 1 number.
            </div>
        </div>

        <button onclick="registration()" class="btn btn-primary mt-5 position-absolute top-35 start-50 translate-middle">Register</button>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
        crossorigin="anonymous"></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/js/registration.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/js/login.js'></script>
</body>
</html>