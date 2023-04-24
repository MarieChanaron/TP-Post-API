<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header class="mb-4 sticky-top">
    <div class="container-fluid px-0 bg-dark">

        <nav class="navbar navbar-expand-md navbar-dark px-5">


            <img src="${pageContext.request.contextPath}/images/logo.png">

            <a class="navbar-brand" href="#">The Blog</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse"
                    aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarCollapse">
                <ul class="navbar-nav me-auto mb-2 mb-md-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="posts">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="categories">Categories</a>
                    </li>
                    <c:if test="${not empty sessionScope.username}">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="add-post">Add post</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="add-category">Add category</a>
                        </li>
                    </c:if>
                </ul>
            </div>

            <c:choose>
                <c:when test="${empty sessionScope.username}">
                    <a class="nav-link active text-white" aria-current="page" href="login">Login</a>
                </c:when>
                <c:otherwise>
                    <div class="text-white me-3">Welcome ${sessionScope.username} !</div>
                    <form class="d-flex m-0" action="${pageContext.request.contextPath}/user/logout" method="get">
                        <button class="btn btn-outline-danger" type="submit">Logout</button>
                    </form>
                </c:otherwise>
            </c:choose>

        </nav>

    </div>
</header>
