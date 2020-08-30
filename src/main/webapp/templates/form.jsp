<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

    <title>ProductsApp</title>
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

<div class="container">

    <!-- site header -->
    <div class="jumbotron">
        <h1 class="display-4">Zadanie rekrutacyjne E-POINT</h1>
        <p class="lead">Tabela przechowująca podstawowe informacje o produktach RTV</p>
    </div>
    <!-- /site header -->

    <!-- Main Body -->
    <div class="modal-body">
        <h3 class="mb-4">
            <small class="text-muted">Formularz dodawania produktu do bazy</small>
        </h3>

        <!-- product form -->
        <form action="/addForm" method="post" id="main_form">
            <div class="form-group">

                <label for="label" class="font-weight-bold">Nazwa produktu:</label>
                <input type="text" id="label" name="label" class="form-control mb-3 col-7"
                          rows="1" placeholder="np. GeForce GTX1060Ti">
                <div style='display:none' path="details" class="alert alert-danger mb-3 col-4" id="label_valid">
                    Błąd! Nazwa powinna zawierać od 3 do 25 znaków!
                </div>

                <label for="details" class="font-weight-bold">Detale:</label>
                <input type="text" id="details" name="details" class="form-control mb-3 col-7"
                          rows="1" placeholder="np. 8GB GDDR5">
                <div style='display:none' path="details" class="alert alert-danger mb-3 col-4" id="details_valid">
                    Błąd! Opis powinien zawierać od 3 do 40 znaków!
                </div>
            </div>
        </form>
        <!-- /product form -->

        <!-- js form submit -->
        <div class="text-left mt-4">
            <button onclick="submitForm()" class="btn btn-outline-success">Dodaj produkt</button>
            <a href="/" class="btn btn-outline-dark">Powrót</a>
        </div>
        <!-- /js form submit -->

    </div>
    <!-- /Main Body -->

</div>

<!-- my JS -->
<script src="../js/submitForm.js"></script>
<!-- /my JS -->

</body>
</html>