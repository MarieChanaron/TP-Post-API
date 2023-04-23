<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>The Blog | Add Post</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<body>

<c:import url="header.jsp" />

<div class="container">
    <h1>Add post</h1>

    <form method="post" action="add-post">
        <div class="input-group mb-3">
            <input type="text" class="form-control" name="title" placeholder="Title...">
        </div>

        <div class="input-group mb-3">
            <input type="text" class="form-control" name="author" placeholder="Author...">
        </div>

        <div class="input-group mb-3">
            <select>
                <option value="Catégorie 1"> Catégorie 1 </option>
                <option value="Catégorie 2"> Catégorie 2 </option>
                <option value="Catégorie 3"> Catégorie 3 </option>
            </select>
        </div>

        <div class="input-group mb-3">
            <textarea class="form-control" name="content" placeholder="Your content.."></textarea>
        </div>
        <div class="input-group">
            <button type="submit" class="btn btn-outline-dark">Submit</button>
        </div>
    </form>


</div>


</body>
</html>
