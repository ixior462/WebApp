var bad=0;
var all=0;
var data=0;
var words=[];
$( function() {
    var parent = document.getElementById("pl");
    var parent2 = document.getElementById("eng");
    var parent3 = document.getElementById("checksymb");
    if(data==1) {
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
    if(data==1) {
        if (document.getElementsByTagName('li')[0].id == "wordeng-1" &&
            document.getElementsByTagName('li')[1].id == "wordeng-2" &&
            document.getElementsByTagName('li')[2].id == "wordeng-3" &&
            document.getElementsByTagName('li')[3].id == "wordeng-4") {
            checkBad();

        } else {

            bad=bad+checkBad();
        }
        all+=4;
    }
    else if (data==2) {
        if (document.getElementsByTagName('li')[0].id == "wordeng-1" &&
            document.getElementsByTagName('li')[1].id == "wordeng-2" &&
            document.getElementsByTagName('li')[2].id == "wordeng-3" &&
            document.getElementsByTagName('li')[3].id == "wordeng-4" &&
            document.getElementsByTagName('li')[4].id == "wordeng-5" &&
            document.getElementsByTagName('li')[5].id == "wordeng-6") {
            checkBad();

        } else {

            bad=bad+checkBad();
        }
        all+=6;
    }
    else {
        if (document.getElementsByTagName('li')[0].id == "wordeng-1" &&
            document.getElementsByTagName('li')[1].id == "wordeng-2" &&
            document.getElementsByTagName('li')[2].id == "wordeng-3" &&
            document.getElementsByTagName('li')[3].id == "wordeng-4" &&
            document.getElementsByTagName('li')[4].id == "wordeng-5" &&
            document.getElementsByTagName('li')[5].id == "wordeng-6" &&
            document.getElementsByTagName('li')[6].id == "wordeng-7" &&
            document.getElementsByTagName('li')[7].id == "wordeng-8") {
            checkBad();

        } else {
            bad=bad+checkBad();
        }
        all+=8;
    }
    document.getElementById("check").style.display="none";
    if(all<words.length)
        document.getElementById("next").style.display="inline";
    else
        document.getElementById("stat").style.display="inline";

}
function checkBad(){
    var n;
    var wrong=0;
    if (data == 1) {
        n = 4;
    } else if (data == 2) {
        n = 6;
    } else
        n = 8;
    var j=1;
    for(i=0;i<n;i++){
        if(document.getElementsByTagName('li')[i].id != "wordeng-"+j) {
            wrong++;
            document.getElementById("check-"+j).innerText="Źle"

        }
        else
            document.getElementById("check-"+j).innerText="Dobrze"
        j++;
    }
    return wrong;
}
var value=1;
function count() {
    return value++;
}
function next(data) {
    var n;

    if (data == 1) {
        n = 4;
    } else if (data == 2) {
        n = 6;
    } else
        n = 8;

    var j = 1;
    var numofclick=count();

    for (i = n * numofclick; i < n * 2 * numofclick; i++) {
        if (i >= words.length) {
            //document.getElementById("stat").style.display="inline";
            document.getElementById("next").style.display="none";
            break;
        }
        document.getElementById("wordpl-" + j).innerText = words[i].pl;
        document.getElementById("wordeng-" + j).innerText = words[i].eng;

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
    z.innerText = "Źle:    "+bad;

    var correct =all-bad;
    var y = document.getElementById("corr");
    y.textContent = "Dobrze:    "+correct;
    document.getElementById("stats").style.display="inline";

}
function clear(){
    for(z=1; z<=8;z++){
        document.getElementById("check-"+z).innerText="";
    }
}
