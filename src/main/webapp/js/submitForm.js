function submitForm() {
    let label = document.getElementById("label").value;
    let details = document.getElementById("details").value;

    if (label.length == 0 || label.length < 3 || label.length > 25) {
        document.getElementById("label_valid").style.display = 'block';
        setTimeout(function(){document.getElementById('label_valid').style.display = 'none'}, 3000);
        return;
    }

    if (details.length == 0 || details.length < 3 || details.length > 40) {
        document.getElementById("details_valid").style.display = 'block';
        setTimeout(function(){document.getElementById('details_valid').style.display = 'none'}, 3000);
        return;
    }
    document.getElementById("main_form").submit();
}