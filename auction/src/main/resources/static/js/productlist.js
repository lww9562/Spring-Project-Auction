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

 all.style.fontWeight="bold";
 lastbutton.style.fontWeight="normal";
 newbutton.style.fontWeight="normal";
 endbutton.style.fontWeight="normal";
 barobutton.style.fontWeight="normal";

 allpr.classList.remove('hidden_tag');
 BaroPrice.classList.add('hidden_tag');
 listEndPrice.classList.add('hidden_tag');
 listLastTime.classList.add('hidden_tag');
 listNewPr.classList.add('hidden_tag');

  });

lastbutton.addEventListener('click', function(){

 all.style.fontWeight="normal";
 lastbutton.style.fontWeight="bold";
 newbutton.style.fontWeight="normal";
 endbutton.style.fontWeight="normal";
 barobutton.style.fontWeight="normal";


    allpr.classList.add('hidden_tag');
    BaroPrice.classList.add('hidden_tag');
    listEndPrice.classList.add('hidden_tag');
    listLastTime.classList.remove('hidden_tag');
    listNewPr.classList.add('hidden_tag');
});



newbutton.addEventListener('click', function(){

 all.style.fontWeight="normal";
 lastbutton.style.fontWeight="normal";
 newbutton.style.fontWeight="bold";
 endbutton.style.fontWeight="normal";
 barobutton.style.fontWeight="normal";

    allpr.classList.add('hidden_tag');
    BaroPrice.classList.add('hidden_tag');
    listEndPrice.classList.add('hidden_tag');
    listLastTime.classList.add('hidden_tag');
    listNewPr.classList.remove('hidden_tag');
});




endbutton.addEventListener('click', function(){

 all.style.fontWeight="normal";
 lastbutton.style.fontWeight="normal";
 newbutton.style.fontWeight="normal";
 endbutton.style.fontWeight="bold";
 barobutton.style.fontWeight="normal";

    allpr.classList.add('hidden_tag');
    BaroPrice.classList.add('hidden_tag');
    listEndPrice.classList.remove('hidden_tag');
    listLastTime.classList.add('hidden_tag');
    listNewPr.classList.add('hidden_tag');
});



barobutton.addEventListener('click', function(){

 all.style.fontWeight="normal";
 lastbutton.style.fontWeight="normal";
 newbutton.style.fontWeight="normal";
 endbutton.style.fontWeight="normal";
 barobutton.style.fontWeight="bold";
    allpr.classList.add('hidden_tag');
    BaroPrice.classList.remove('hidden_tag');
    listEndPrice.classList.add('hidden_tag');
    listLastTime.classList.add('hidden_tag');
    listNewPr.classList.add('hidden_tag');
});


  }, false);