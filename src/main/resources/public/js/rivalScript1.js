var words = [];

var wordsId = 0;

var wordPL = "";
var wordENG = "";
var letterID = 1;
var correct = 0;
var incorrect = 0;
var additionalLetters = 0;

function randLetter() {
    var char;
    var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    char= possible.charAt(Math.floor(Math.random() * possible.length));

    return char;
}

function initialize(){

    document.getElementById("check").style.visibility = "hidden";
    document.getElementById("next").style.visibility = "hidden";
    document.getElementById("score").style.visibility = "hidden";
    words = shuffle(words);
    loadNextWord();
    loadWordPL();
    loadLetters();

}
function shuffle(a) {
    var j, x, i;
    for (i = a.length - 1; i > 0; i--) {
        j = Math.floor(Math.random() * (i + 1));
        x = a[i];
        a[i] = a[j];
        a[j] = x;
    }
    return a;
}
function reload(){
    location.reload();
}
function reset(){
    document.getElementById("check").style.visibility = "hidden";
    letterID = 1;
    var l = document.getElementsByClassName("letterButton");
    for(var i=0; i<l.length; i++){
        l[i].style.visibility = "visible";
    }
    l = document.getElementsByClassName("letter");
    for(var i=0; i<l.length; i++){
        l[i].innerHTML = "_";
    }
}
function printScore(){
    document.getElementById("points").value = correct;
    document.getElementById("pointsForm").submit();
    //$.post("/waitingResults", data, function(){ window.location.replace("waitingResult")});
    /*removeLetters();
    document.getElementById("wordPL").style.color = "#191919";
    document.getElementById("wordPL").innerText = "Dobre odpowiedzi: "+correct;
    document.getElementById("wordENG").innerText = "Złe odpowiedzi: "+incorrect;
    document.getElementById("score").style.visibility="hidden";
    var score = correct/(correct+incorrect);
    if(score >= 0.9) {
        var url = new URL(document.URL);
        var lessonNumber = url.searchParams.get("lesson");
        var data = {lesson: lessonNumber, stage: 3};
        $.post("/nauka3", data, function(){});
        document.getElementById("nextStage").style.display = "inline";
    }
    else{
        document.getElementById("reload").style.display ="inline";
    }
    document.getElementById("return").style.display="inline";*/

}
function next(){
    document.getElementById("next").style.visibility = "hidden";
    document.getElementById("reset").style.visibility = "visible";
    loadNextWord();
    document.getElementById("wordENG").innerText = "";
    loadWordPL();
    removeLetters();
    loadLetters();
    reset();
}
function loadNextWord(){
    wordPL = words[wordsId].pl.toUpperCase();
    wordENG = words[wordsId].eng.toUpperCase();
    wordsId++;
}

function loadWordPL(){
    document.getElementById("wordPL").style.color = "#191919";
    document.getElementById("wordPL").innerText = wordPL;
}
function removeLetters(){
    var l = document.getElementsByClassName("letterButton");
    for(var i=l.length - 1; i>=0; i--){
        l[i].parentNode.removeChild(l[i]);
    }
    l = document.getElementsByClassName("letter");
    for(var i=l.length - 1; i>=0; i--){
        l[i].parentNode.removeChild(l[i]);
    }
}
function loadLetters() {
    var letters = shuffle(wordENG.split(""));
    for (var i = 0; i < letters.length; i++) (function f(i) {

        var blank = document.createElement("div");
        blank.className = "letter";
        blank.innerText = "_";


        //console.log(letters[i]);

        document.getElementById("ENG").appendChild(blank);

    })(i);
    for(var i=0; i<additionalLetters; i++) {
        letters.push(randLetter());
    }
    shuffle(letters);
    for (var i = 0; i < letters.length; i++) (function f(i) {
        var letter = document.createElement("button");
        letter.innerText = letters[i];
        letter.className = "letterButton";
        letter.onclick = function () {
            if(letterID<letters.length+1-additionalLetters) {
                letter.style.visibility = "hidden";
                document.getElementById("ENG").childNodes[letterID].innerHTML = letters[i];
                letterID++;
                if (letterID == letters.length + 1 - additionalLetters) {
                    document.getElementById("check").style.visibility = "visible";
                }
                //document.getElementById("wordENG").innerText += letters[i];
            }
        };
        document.getElementById("lettersContainer").appendChild(letter);
    })(i);
}
function check(){
    if(wordsId<words.length)
        document.getElementById("next").style.visibility = "visible";
    else
        document.getElementById("score").style.visibility = "visible";

    document.getElementById("reset").style.visibility = "hidden";
    document.getElementById("check").style.visibility = "hidden";

    var eng = document.getElementById("ENG").childNodes;
    var word = "";
    for(var i=1; i<eng.length; i++){
        word += eng[i].innerText;
    }
    var result = document.getElementById("wordPL");
    if(wordENG == word){
        result.style.color = "green";
        result.innerText = "✓";
        correct++;
    } else {
        result.style.color = "red";
        result.innerText = "✗";
        incorrect++;
    }
    result.visibility = "visible";
    document.getElementById("wordENG").innerText = wordENG;
}
function nextStage() {
    var url = new URL(document.URL);
    var lessonNumber = url.searchParams.get("lesson");
    window.location.replace("/nauka4?lesson="+lessonNumber);
}
function returnToMain() {
    window.location.replace("/indexClient");
}