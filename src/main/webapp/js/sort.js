let table = document.getElementById("my_table");
let descending = true;

function sortTable() {

    descending = descending ? false : true;

    let i, j, rows;
    rows = table.rows;

    //just a simple bubble sort
    for (i = 1; i < rows.length -1; i++) {
        for (j = 1; j < rows.length -1; j++) {
            if (!descending) {
                if (getName(j) > getName(j + 1)) {
                    rows[i].parentNode.insertBefore(rows[j + 1], rows[j]);
                }
            } else {
                if (getName(j) < getName(j + 1)) {
                    rows[i].parentNode.insertBefore(rows[j + 1], rows[j]);
                }
            }
        }
    }
}

function getName(nr) {
    return table.getElementsByTagName("tr")[nr].getElementsByTagName("td")[0].innerHTML;
}
