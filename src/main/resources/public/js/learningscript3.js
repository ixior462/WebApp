var words = [];

var wordsId = 0;

var wordPL = "";
var wordENG = "";
var letterID = 1;
var correct = 0;
var incorrect = 0;

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
function reset(){
    document.getElementById("check").style.visibility = "hidden";
    letterID = 1;
    var l = document.getElementsByClassName("letterButton");
    for(var i=0; i<l.length; i++){
        l[i].style.visibility = "visible";
    }
    l = document.getElementsByClassName("letter");
    for(var i=0; i<l.length; i++){
        l[i].innerHTML = "-";
    }
}
function printScore(){
    removeLetters();
    document.getElementById("wordPL").style.color = "#191919";
    document.getElementById("wordPL").innerText = "Dobre odpowiedzi: "+correct;
    document.getElementById("wordENG").innerText = "Złe odpowiedzi: "+incorrect;

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
        var letter = document.createElement("button");
        var blank = document.createElement("div");
        blank.className = "letter";
        blank.innerText = "-";
        letter.innerText = letters[i];
        letter.className = "letterButton";
        //console.log(letters[i]);
        letter.onclick = function () {
            letter.style.visibility = "hidden";
            document.getElementById("ENG").childNodes[letterID].innerHTML = letters[i];
            letterID++;
            if(letterID==letters.length+1){
                document.getElementById("check").style.visibility = "visible";
            }
            //document.getElementById("wordENG").innerText += letters[i];
        };
        document.getElementById("ENG").appendChild(blank);
        document.getElementById("lettersContainer").appendChild(letter);
    })(i);
}
function check(){
    if(wordsId<words.length - 1)
        document.getElementById("next").style.visibility = "visible";
    else
        document.getElementById("score").style.visibility = "visible";

    document.getElementById("reset").style.visibility = "hidden";
    document.getElementById("check").style.visibility = "hidden";

    var eng = document.getElementById("ENG").childNodes
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