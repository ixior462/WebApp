function openNav() {
    var x = document.getElementById("menuSidenav");
    if(x.style.width === "0%") {
        x.style.width = "25%";
        document.getElementById("learn").style.marginLeft = "10%";
    } else {
        x.style.width = "0%";
        document.getElementById("learn").style.marginLeft= "0%";
        console.log("1");
    }
}

function closeNav() {
    document.getElementById("menuSidenav").style.width = "0%";
    document.getElementById("learn").style.marginLeft= "0%";
}

function open() {
    document.getElementById("menuSidenav").style.width = "25%";
    document.getElementById("learn").style.marginLeft = "10%";
}
