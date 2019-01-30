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
var numbOfLessons = [];
function initializeMenu() {
    var cont = document.getElementById("sideMenu");
    console.log(lessons);
    for (var i = 1; i <= lessons; i++) {
        (function f(i) {
            {
                if(i==1){
                    var level = document.createElement("p");
                    level.innerText="A1";
                    cont.appendChild(level);
                }
                else if(i-1==numbOfLessons[0]){
                    var level = document.createElement("p");
                    level.innerText="A2";
                    cont.appendChild(level);
                }
                else if(i-1==numbOfLessons[1]){
                    var level = document.createElement("p");
                    level.innerText="B1";
                    cont.appendChild(level);
                }
                else if(i-1==numbOfLessons[2]){
                    var level = document.createElement("p");
                    level.innerText="B2";
                    cont.appendChild(level);
                }
                else if(i-1==numbOfLessons[3]){
                    var level = document.createElement("p");
                    level.innerText="C1";
                    cont.appendChild(level);
                }
                else if(i-1==numbOfLessons[4]){
                    var level = document.createElement("p");
                    level.innerText="C2";
                    cont.appendChild(level);
                }
                var link = document.createElement("pre");
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
    document.getElementById("learn").innerHTML='<object type="text/html" data="http://localhost:8080/lessonMenu?lesson='+number+'" ></object>';
}