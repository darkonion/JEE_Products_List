window.onmouseover = e => {
    if (e.path[3] == table && e.target != document.getElementById("my_head")) {
        e.target.style.color = "#828282";
        e.target.style.cursor = "default";
    }
}

window.onmouseout = e => {
    if (e.path[3] == table && e.target != document.getElementById("my_head")) {
        e.target.style.color = "black";
        e.target.style.cursor = "default";
    }
}