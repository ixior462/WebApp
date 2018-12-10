var words = ["ERROR"]
var wordIndex = 0;
var active = false;

function readPL(){
  var msg = new SpeechSynthesisUtterance(document.getElementById('textPL').innerHTML);
  msg.pitch=0.7;
  window.speechSynthesis.speak(msg);
}

function readENG(){
  var msg = new SpeechSynthesisUtterance(document.getElementById('textENG').innerHTML);
  msg.lang='en-US';
  msg.pitch=0.7;
  window.speechSynthesis.speak(msg);
}

function loadFiche(){
  var fichePL = document.getElementById("textPL");
  var ficheENG = document.getElementById("textENG");
  fichePL.innerHTML = words[wordIndex].pl;
  ficheENG.innerHTML = words[wordIndex].eng;
}

function initializeCounter(){
    document.getElementById("numberOfWords").innerHTML = "/"+words.length;
    document.getElementById("ficheNumber").innerHTML = wordIndex+1;
}

function updateCounter(){
  var ficheNumber = document.getElementById("ficheNumber")
  ficheNumber.innerHTML = wordIndex+1;
  if(ficheNumber.innerHTML == words.length){
      document.getElementById("nextStage").style.display = "block";
  }
}

function nextFiche(){
  if(!active){
    active = true;
    $.when($('#fiszkaX').animate({bottom:"500px"},400, function(){
      wordIndex = (wordIndex+1)%words.length;
      loadFiche();
      updateCounter();
      $('#fiszkaX').animate({bottom:"0px"},400)
    })).then(function() {active = false})
  }
   /* $('#fiszkaX').animate({bottom:"500px"},400, function(){
        wordIndex = (wordIndex+1)%words.length;
        loadFiche();
        updateCounter();
        $('#fiszkaX').animate({bottom:"0px"},400)
    })*/
  //flip(false);
  //var x = document.getElementsByClassName('fiszka');
  //x[0].style.display = "none";
}
