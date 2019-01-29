var bad=0;
var all=0;
var data=0;
var words=[];
var n;
function initializeN(){
   n=5;
}
$( function() {
    var parent = document.getElementById("pl");
    var parent2 = document.getElementById("eng");
    var parent3 = document.getElementById("checksymb");
    /*if(data==1) {
        for(i=5;i<=8;i++){
            parent.removeChild(document.getElementById("wordpl-"+i));
            parent2.removeChild(document.getElementById("wordeng-"+i));
            parent3.removeChild(document.getElementById("check-"+i));
        }
    }
    if(data==2) {
        for(i=7;i<=8;i++){
            parent.removeChild(document.getElementById("wordpl-"+i));
            parent2.removeChild(document.getElementById("wordeng-"+i));
            parent3.removeChild(document.getElementById("check-"+i));
        }
    }*/
    var ul = document.querySelector('ul');
    for (var i = ul.children.length; i >= 0; i--) {
        ul.appendChild(ul.children[Math.random() * i | 0]);
    }
    $( ".eng" ).sortable();
    $( ".eng" ).disableSelection();



} )
function check( )
{
    var badAns = false;
    for(var i=1; i<=n; i++){
        if(document.getElementsByTagName('li')[i-1].id != ("wordeng-"+i)){
            badAns = true;
            break;
        }
    }
    if(badAns){
        bad=bad+checkBad();
    } else {
        checkBad();
    }

    all+=n;

    document.getElementById("check").style.display="none";
    if(all<words.length)
        document.getElementById("next").style.display="inline";
    else
        document.getElementById("stat").style.display="inline";

}
function checkBad(){

    var wrong=0;

    var j=1;
    for(var i=0;i<n;i++){
        if(document.getElementsByTagName('li')[i].id != "wordeng-"+j) {
            wrong++;
            document.getElementById("check-"+j).innerText="✗"

        }
        else
            document.getElementById("check-"+j).innerText="✓"
        j++;
    }
    return wrong;
}
var value=1;
function count() {
    return value++;
}
function next() {
    var j = 1;
    var numofclick=count();
    var num = n;
    for (i = num * numofclick; i < num * (numofclick+1); i++) {
        if (i >= words.length) {
            var eng = document.getElementById("eng");
            var pl = document.getElementById("pl");
            var checksymb = document.getElementById("checksymb");
            pl.removeChild(document.getElementById("wordpl-" + j));
            eng.removeChild(document.getElementById("wordeng-" + j));
            checksymb.removeChild(document.getElementById("check-" + j));
            n--;
        }
        else {
            document.getElementById("wordpl-" + j).innerText = words[i].pl;
            document.getElementById("wordeng-" + j).innerText = words[i].eng;
        }
        j++;

    }

    var ul = document.querySelector('ul');
    for (var i = ul.children.length; i >= 0; i--) {
        ul.appendChild(ul.children[Math.random() * i | 0]);
    }

    document.getElementById("check").style.display="inline";
    document.getElementById("next").style.display="none";
    clear();
}
function stats(){
    document.getElementById("points").value = getCorrect();
    document.getElementById("pointsForm").submit();
    /*var z = document.getElementById("bad");
    z.innerText = "✗    "+bad;

    var correct =all-bad;
    var y = document.getElementById("corr");
    y.textContent = "✓    "+correct;
    document.getElementById("stats").style.display="inline";
    document.getElementById("stat").style.display="none";
    if(correct>=0.9*all) {
        document.getElementById("nextStage").style.display = "inline";
    }
    else
        document.getElementById("repeat").style.display="inline";
    document.getElementById("return").style.display="inline";*/

}

function clear(){
    for(var z=1; z<=n;z++){
        document.getElementById("check-"+z).innerText="";
    }
}
function nextStage() {
    var url = new URL(document.URL);
    var lessonNumber = url.searchParams.get("lesson");
    window.location.replace("/nauka3?lesson="+lessonNumber);
}
function repeat(){
    var url = new URL(document.URL);
    var lessonNumber = url.searchParams.get("lesson");
    window.location.replace("/nauka2?lesson="+lessonNumber+"&lesson=1");
}
function returnToMain() {
    window.location.replace("/indexClient");
}