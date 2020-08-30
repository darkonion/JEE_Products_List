<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html lang="pl">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.7/css/all.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

    <title>ProductsApp</title>

    <style>
        .fa { display:inline; }
    </style>
</head>

<body>

<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
        integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
        crossorigin="anonymous"></script>

<!-- Main Body -->
<div class="container">

    <!-- site header -->
    <div class="jumbotron">
        <h1 class="display-4">Zadanie rekrutacyjne E-POINT</h1>
        <p class="lead">Tabela przechowująca podstawowe informacje o produktach RTV</p>
    </div>
    <!-- /site header -->

    <!-- Info Section -->
    <c:if test="${not empty success}">
        <div class="alert alert-success alert-dismissible fade show" role="alert">
            <i class="fa fa-check-circle mr-2"></i><i>${success}</i>
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    </c:if>
    <c:if test="${not empty fail}">
        <div class="alert alert-warning alert-dismissible fade show" role="alert">
            <i class="fa fa-exclamation-triangle mr-2"></i><i>${fail}</i>
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    </c:if>
    <!-- /Info Section -->

    <!-- Main Table -->
    <table id="my_table" class="table">
        <thead class="thead-dark">
        <tr>
            <th id="my_head">
                <a type="button" onclick="sortTable()" class="fas fa-sort"></a> Nazwa
            </th>
            <th></th>
        </tr>
        </thead>
        <c:forEach var="product" items="${products}">
        <tr>
            <td>${product.label} ${product.details}</td>
            <td class="text-right">

                <!-- Buttons -->
                <a href="/seq?id=${product.id}&dir=top" class="btn btn-light">Do samej góry</a>
                <a href="/seq?id=${product.id}&dir=up" class="btn btn-light">Do góry</a>
                <a href="/seq?id=${product.id}&dir=down" class="btn btn-light">Do dołu</a>
                <a href="/seq?id=${product.id}&dir=bottom" class="btn btn-light">Do samego dołu</a>
                <a href="/delete/${product.id}" class="btn btn-outline-danger" onclick="return confirm('Czy napewno chcesz usunąć ten produkt');">x</a>
                <!-- /Buttons -->

            </td>
        </tr>
        </c:forEach>
    </table>
    <!-- /Main Table -->

    <c:if test="${empty products}">
        <div class="alert alert-warning">
            <i class="fa fa-exclamation-triangle mr-2"></i><i>List jest pusta, dodaj produkty!</i>
        </div>
    </c:if>


    <a href="/addForm" class="btn btn-outline-dark">
        &nbsp; Dodaj produkt &nbsp;
    </a>

    <footer class="text-center mt-4">
        <small class="text-muted">Paweł Kapliński</small>
    </footer>

</div>
<!-- /Main Body -->

<!-- my JS -->
<script src="../js/sort.js"></script>
<script src="../js/mouseHover.js"></script>
<!-- /my JS -->

</body>
</html>