<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

<head>
  <title>The Blog | Categories List</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>

<body>

  <c:import url="header.jsp" />

  <div class="container">
    <div class="row">
      <div class="col-6 mx-auto my-5">
        <ul class="list-group">

          <c:forEach var="category" items="${categories}">
            <li class="list-group-item">${category.getName()}</li>
          </c:forEach>

        </ul>
      </div>
    </div>
  </div>

</body>

</html>