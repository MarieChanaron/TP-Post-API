<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>The Blog | Post List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<style>
    .capitalize {
        text-transform: capitalize;
    }
</style>
<body>

<c:import url="header.jsp" />

<%-- Search bar --%>
<div class="container">
    <div class="row">
        <div class="col">
            <nav class="navbar navbar-expand-lg navbar-light bg-white">
                <div class="container-fluid">
                    <form class="d-flex ms-auto my-2" method="get" action="search">
                        <input name="keywords" class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                        <button class="btn btn-outline-success" type="submit">Search</button>
                    </form>
                </div>
            </nav>
        </div>
    </div>
</div>

<div class="container">
    <h1>Posts</h1>
    <div class="row row-cols-1 row-cols-md-2 row-cols-lg-4 g-3 align-content-center">
        <c:forEach var="p" items="${posts}">
            <div class="col">
                <div class="card">
                    <img src="${p.pictureUrl}" class="card-img-top" alt="${p.title}">
                    <div class="card-body">
                        <h5 class="card-title">${p.title}</h5>
                        <p class="card-text m-0">By ${p.author}</p>
                        <p class="card-text m-0"><span class="capitalize">${p.category.getName()}</span> category</p>
                        <p class="card-text m-0">
                            <fmt:parseDate value="${p.createdAt}" pattern="yyyy-MM-dd'T'HH:mm" var="postDate" type="date"/>
                            <fmt:formatDate pattern="dd MMMM yyyy HH:mm" value="${postDate}"/>
                        </p>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>


</body>
</html>
