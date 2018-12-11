var words = ["ERROR"]
var unlearnedWords = [];
var wordIndex = 0;
var active = false;
var front = true;

function readPL1(){
  var msg = new SpeechSynthesisUtterance(document.getElementById('textPL1').innerHTML);
  msg.pitch=0.7;
  window.speechSynthesis.speak(msg);
}

function readENG1(){
  var msg = new SpeechSynthesisUtterance(document.getElementById('textENG1').innerHTML);
  msg.lang='en-US';
  msg.pitch=0.7;
  window.speechSynthesis.speak(msg);
}

function readPL2(){
    var msg = new SpeechSynthesisUtterance(document.getElementById('textPL2').innerHTML);
    msg.pitch=0.7;
    window.speechSynthesis.speak(msg);
}

function readENG2(){
    var msg = new SpeechSynthesisUtterance(document.getElementById('textENG2').innerHTML);
    msg.lang='en-US';
    msg.pitch=0.7;
    window.speechSynthesis.speak(msg);
}

function loadFiche(){
  if(front) {
      var fichePL = document.getElementById("textPL1");
      var ficheENG = document.getElementById("textENG1");
      var buttPL =  document.getElementById("PLbutton1");
  } else {
      var fichePL = document.getElementById("textPL2");
      var ficheENG = document.getElementById("textENG2");
      var buttPL =  document.getElementById("PLbutton2");
  }
  fichePL.innerHTML = words[wordIndex].pl;
  ficheENG.innerHTML = words[wordIndex].eng;
  fichePL.style.visibility = "hidden";
  buttPL.style.visibility = "hidden";
  document.getElementById("unlearned").style.display = "none";
  document.getElementById("learned").style.display = "none";
  document.getElementById("showTranslation").style.display = "block";
  front=!front;
}

function initializeCounter(){
    document.getElementById("numberOfWords").innerHTML = "/"+words.length;
    document.getElementById("ficheNumber").innerHTML = wordIndex+1;
}

function updateCounter(){
  var ficheNumber = document.getElementById("ficheNumber")
  ficheNumber.innerHTML = wordIndex+1;

}


function showPL(){
    if(!front) {
        var fichePL = document.getElementById("textPL1");
        var buttPL =  document.getElementById("PLbutton1");
    } else{
        var fichePL = document.getElementById("textPL2");
        var buttPL =  document.getElementById("PLbutton2");
    }
    fichePL.style.visibility = "visible";
    buttPL.style.visibility = "visible";
    document.getElementById("showTranslation").style.display = "none";
    document.getElementById("unlearned").style.display = "inline";
    document.getElementById("learned").style.display = "inline";
}
function nextFiche(){
  wordIndex = (wordIndex+1)%words.length;
  if(wordIndex==0 && unlearnedWords.length > 0){
      words = unlearnedWords;
      unlearnedWords = [];
      initializeCounter();
  }
  else if(wordIndex == 0&& unlearnedWords.length == 0){
        document.getElementById("nextStage").style.display = "block";
        document.getElementById("info").style.display = "block";
        document.getElementById("frontFiche").style.display = "none";
        document.getElementById("backFiche").style.display = "none";
        document.getElementById("counter").style.display = "none";
        document.getElementById("unlearned").style.display = "none";
        document.getElementById("learned").style.display = "none";
        document.getElementById("showTranslation").style.display = "none";
        return;
  }
  loadFiche();
  $("#ficheContainer").flip('toggle');
  updateCounter();

  /*if(!active){
    active = true;
    $.when($('#ficheContainer').animate({bottom:"500px"},400, function(){
      wordIndex = (wordIndex+1)%words.length;
      loadFiche();
      updateCounter();
      $("#ficheContainer").flip(false);
      $('#ficheContainer').animate({bottom:"0px"},400)
    })).then(function() {active = false})
  }*/
   /* $('#ficheContainer').animate({bottom:"500px"},400, function(){
        wordIndex = (wordIndex+1)%words.length;
        loadFiche();
        updateCounter();
        $('#ficheContainer').animate({bottom:"0px"},400)
    })*/
  //flip(false);
  //var x = document.getElementsByClassName('fische');
  //x[0].style.display = "none";
}

function notLearned(){
    unlearnedWords.push(words[wordIndex]);
    nextFiche();
}

