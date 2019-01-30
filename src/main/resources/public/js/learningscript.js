var bad=0;
var all=0;
var data=0;
var lessonLevel=0;
var words=[];
var n;
function initializeN(){
    if (lessonLevel == "A1"||lessonLevel=="A2") {
        n = 4;
    } else if (lessonLevel == "B1"||lessonLevel=="B2") {
        n = 6;

    } else if(lessonLevel=="C1"||lessonLevel=="C2")
        n = 8;

}
$( function() {
    var parent = document.getElementById("pl");
    var parent2 = document.getElementById("eng");
    var parent3 = document.getElementById("checksymb");
    if(lessonLevel=="A1"||lessonLevel=="A2") {
        document.getElementById("container").style.marginTop="10%";
        for(i=5;i<=8;i++){
            parent.removeChild(document.getElementById("wordpl-"+i));
            parent2.removeChild(document.getElementById("wordeng-"+i));
            parent3.removeChild(document.getElementById("check-"+i));
        }
    }
    if(lessonLevel=="B1"||lessonLevel=="B2") {
        document.getElementById("container").style.marginTop="5%";
        for(i=7;i<=8;i++){
            parent.removeChild(document.getElementById("wordpl-"+i));
            parent2.removeChild(document.getElementById("wordeng-"+i));
            parent3.removeChild(document.getElementById("check-"+i));
        }
    }
    if(lessonLevel=="C1"||lessonLevel=="C2"){
        document.getElementById("container").style.marginTop="3%";
    }
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

    var z = document.getElementById("bad");
    z.innerText = "✗    "+bad;

    var correct =all-bad;
    var y = document.getElementById("corr");
    y.textContent = "✓    "+correct;
    document.getElementById("stats").style.display="inline";
    document.getElementById("stat").style.display="none";
    if(correct>=0.9*all) {
        var url = new URL(document.URL);
        var lessonNumber = url.searchParams.get("lesson")[0];
        var data = {lesson: lessonNumber, stage: 3};
        $.post("/nauka4", data, function(){});
        document.getElementById("nextStage").style.display = "inline";
    }
    else
        document.getElementById("repeat").style.display="inline";
    document.getElementById("return").style.display="inline";

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
    window.location.reload();
}
function returnToMain(){
    window.location.replace("/indexClient");
}