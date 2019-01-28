var i=0;
var correct=0;
var bad=0;
function count() {
    return i++;
}
function getCount(){
    return i;
}
function setBad(){
    return bad++;
}
function setCorrect(){
    return correct++;
}
function getBad(){
    return bad;
}
function getCorrect(){
    return correct;
}
function setWord(){
    var value = Math.floor(Math.random()*10);
    if(value<7)
        document.getElementById("wordToTranslate").innerText=words[i].pl;
    else
        document.getElementById("wordToTranslate").innerText=words[i].eng;

}
function check(){
    var el = document.getElementById("checkResult");
    if(document.getElementById("wordToTranslate").innerText.toUpperCase()==words[i].pl.toUpperCase()) {
        if (document.getElementById("wordToCheck").value.toUpperCase() == words[i].eng.toUpperCase()) {
            el.innerText = '✓';
            setCorrect();
        }
        else {
            el.innerText = "✗";
            setBad();
        }
    }
    else if(document.getElementById("wordToTranslate").innerText.toUpperCase()==words[i].eng.toUpperCase()) {
        if (document.getElementById("wordToCheck").value.toUpperCase() == words[i].pl.toUpperCase()) {
            el.innerText = '✓';
            setCorrect();
        }
        else {
            el.innerText = "✗";
            setBad();
        }
    }
    document.getElementById("check").style.display="none";
    if(getCount()!=words.length-1)
        document.getElementById("next").style.display = "inline";
    else
        document.getElementById("stat").style.display = "inline";

}
function next(){
    document.getElementById("next").style.display = "none";
    var el = document.getElementById("checkResult");
    var el2 = document.getElementById("wordToCheck");
    el.innerText ="";
    el2.value="";
    count();
    if(getCount()==words.length) {
        document.getElementById("check").style.display="none";
        document.getElementById("next").style.display = "none";
        document.getElementById("stat").style.display = "inline";
    }
    if(getCount()<words.length){
        setWord();
        document.getElementById("check").style.display="inline";}
}
function stats(){
    document.getElementById("checkResult").innerText='✗ '+getBad();
    document.getElementById("checkResult2").innerText='✓ '+getCorrect();
    if(getCorrect()>=words.length*0.9) {
        document.getElementById("Result").innerText = "Brawo, udało Ci się zaliczyć lekcję!"
        document.getElementById("NextLesson").style.display="inline";
        document.getElementById("return").style.display = "inline";
    }
    else {
        document.getElementById("Result").innerText = "Spróbuj jeszcze raz."
        document.getElementById("repeat").style.display="inline";
        document.getElementById("return").style.display = "inline";
    }
}
function returnToMain() {
    window.location.replace("/indexClient");
}
function repeat(){
    var url = new URL(document.URL);
    var lessonNumber = url.searchParams.get("lesson");
    window.location.replace("/nauka4?lesson="+lessonNumber);
}
function nextLesson(){
    var url = new URL(document.URL);
    var lessonNumber = url.searchParams.get("lesson");
    var nextlesson = parseInt(lessonNumber) +1;
    window.location.replace("/nauka?lesson="+nextlesson);
}
function initializeEnter() {
    var input = document.getElementById("wordToCheck");
    // Execute a function when the user releases a key on the keyboard
    input.addEventListener("keyup", function (event) {
        // Cancel the default action, if needed
        event.preventDefault();
        // Number 13 is the "Enter" key on the keyboard
        if (event.keyCode === 13) {
            // Trigger the button element with a click
            var btn = document.getElementById("check");
            var btn2 = document.getElementById("next");

            if(btn.style.display!="none")
                btn.click();
            else if(btn2.style.display!="none")
                btn2.click();
        }
    });
}