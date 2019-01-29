function openNav() {
    var x = document.getElementById("menuSidenav");
    if(x.style.width === "0%") {
        x.style.width = "25%";
        document.getElementById("learn").style.width="75%";
    } else {
        x.style.width = "0%";
        document.getElementById("learn").style.width="100%";
    }
}

function closeNav() {
    document.getElementById("menuSidenav").style.width = "0%";
    document.getElementById("learn").style.width="100%";
}

function open() {
    document.getElementById("menuSidenav").style.width = "25%";
    document.getElementById("learn").style.width = "75%";
}
function loadHTML(number) {
    document.getElementById("learn").innerHTML='<object type="text/html" data="http://localhost:8080/nauka?lesson='+number+'" ></object>';
}