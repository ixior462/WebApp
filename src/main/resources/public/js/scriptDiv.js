var slideNumber = 1;

function nextSlide() {
    if (slideNumber == 5) {
        slideNumber = 1;
    } else {
        slideNumber += 1;
    }
    goToNewSlide(slideNumber);
}

function previousSlide() {
    if (slideNumber == 1) {
        slideNumber = 5;
    } else {
        slideNumber -= 1;
    }
    goToNewSlide(slideNumber);
}

function goToNewSlide(x) {
    switch (x) {
        case 1:
            document.getElementById("one").style.display = "block";
            document.getElementById("two").style.display = "none";
            document.getElementById("three").style.display = "none";
            document.getElementById("four").style.display = "none";
            document.getElementById("five").style.display = "none";
            break;
        case 2:
            document.getElementById("one").style.display = "none";
            document.getElementById("two").style.display = "block";
            document.getElementById("three").style.display = "none";
            document.getElementById("four").style.display = "none";
            document.getElementById("five").style.display = "none";
            break;
        case 3:
            document.getElementById("one").style.display = "none";
            document.getElementById("two").style.display = "none";
            document.getElementById("three").style.display = "block";
            document.getElementById("four").style.display = "none";
            document.getElementById("five").style.display = "none";
            break;
        case 4:
            document.getElementById("one").style.display = "none";
            document.getElementById("two").style.display = "none";
            document.getElementById("three").style.display = "none";
            document.getElementById("four").style.display = "block";
            document.getElementById("five").style.display = "none";
            break;
        case 5:
            document.getElementById("one").style.display = "none";
            document.getElementById("two").style.display = "none";
            document.getElementById("three").style.display = "none";
            document.getElementById("four").style.display = "none";
            document.getElementById("five").style.display = "block";
            break;
    }
}