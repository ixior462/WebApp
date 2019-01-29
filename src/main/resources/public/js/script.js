function openNav() {
    var x = document.getElementById("menuSidenav");
    if(x.style.width === "0%") {
        x.style.width = "25%";
        document.getElementById("learn").style.width = "75%";
        document.getElementById("learn").style.transition = "0.5s";
    } else {
        x.style.width = "0%";
        document.getElementById("learn").style.width = "100%";
    }
}

function closeNav() {
    document.getElementById("menuSidenav").style.width = "0%";
    document.getElementById("learn").style.width = "100%";
}

function open() {
    document.getElementById("menuSidenav").style.width = "25%";
    document.getElementById("learn").style.width = "75%";
    document.getElementById("learn").style.transition = "0.5s";
}
var lessons = 0;
function initializeMenu() {
    var cont = document.getElementById("sideMenu");
    console.log(lessons);
    for (var i = 1; i <= lessons; i++) {
        (function f(i) {
            {
                var link = document.createElement("a");
                link.innerText = "Lekcja " + i;
                link.onclick = function () {
                    closeNav();
                    loadHTML(i);
                };
                cont.appendChild(link);
            }
        }(i));
    }
}
function loadHTML(number) {
    document.getElementById("learn").innerHTML='<object type="text/html" data="http://localhost:8080/nauka?lesson='+number+'" ></object>';
}