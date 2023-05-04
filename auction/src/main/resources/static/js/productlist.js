	window.addEventListener("DOMContentLoaded", function() {
var all = document.getElementById('all');
var lastbutton = document.getElementById('lastbutton');
var newbutton = document.getElementById('newbutton');
var endbutton = document.getElementById('endbutton');
var barobutton = document.getElementById('barobutton');

console.log(all);

var allpr = document.getElementById("allpr");
var BaroPrice = document.getElementById("BaroPrice");
var listEndPrice = document.getElementById("listEndPrice");
var listLastTime = document.getElementById("listLastTime");
var listNewPr = document.getElementById("listNewPr");


all.addEventListener('click', function () {
 allpr.classList.remove('hidden_tag');
 BaroPrice.classList.add('hidden_tag');
 listEndPrice.classList.add('hidden_tag');
 listLastTime.classList.add('hidden_tag');
 listNewPr.classList.add('hidden_tag');

  });

lastbutton.addEventListener('click', function(){

    allpr.classList.add('hidden_tag');
    BaroPrice.classList.add('hidden_tag');
    listEndPrice.classList.add('hidden_tag');
    listLastTime.classList.remove('hidden_tag');
    listNewPr.classList.add('hidden_tag');
});

newbutton.addEventListener('click', function(){

    allpr.classList.add('hidden_tag');
    BaroPrice.classList.add('hidden_tag');
    listEndPrice.classList.add('hidden_tag');
    listLastTime.classList.add('hidden_tag');
    listNewPr.classList.remove('hidden_tag');
});

endbutton.addEventListener('click', function(){

    allpr.classList.add('hidden_tag');
    BaroPrice.classList.add('hidden_tag');
    listEndPrice.classList.remove('hidden_tag');
    listLastTime.classList.add('hidden_tag');
    listNewPr.classList.add('hidden_tag');
});

barobutton.addEventListener('click', function(){

    allpr.classList.add('hidden_tag');
    BaroPrice.classList.remove('hidden_tag');
    listEndPrice.classList.add('hidden_tag');
    listLastTime.classList.add('hidden_tag');
    listNewPr.classList.add('hidden_tag');
});










  }, false);