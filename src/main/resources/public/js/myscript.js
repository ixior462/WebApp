var words = ["ERROR"]
var wordIndex = 0;

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

function nextFiche(){
  $('#fiszkaX').animate({bottom:"500px"},400, function(){
    wordIndex = (wordIndex+1)%words.length;
    loadFiche();
    $('#fiszkaX').animate({bottom:"0px"},400)
  })
  //flip(false);
  //var x = document.getElementsByClassName('fiszka');
  //x[0].style.display = "none";
}
