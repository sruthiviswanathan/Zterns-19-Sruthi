
function openNav() {
  // document.getElementById("main").style.marginLeft = "250px";
  document.getElementById("mySidenav").style.width = "250px";
}

function closeNav() {
  // document.getElementById("main").style.marginLeft = "0";
  document.getElementById("mySidenav").style.width = "0";
}


function openForm() {
  document.getElementById("myForm").style.display = "block";
}

function closeForm() {
  document.getElementById("myForm").style.display = "none";
}


//////////////////////modal////////////////////////////////
function displaymodal(id) {
  var modal = document.getElementById('myModal');
  var btn = document.getElementById(id);
  var span = document.getElementsByClassName("close")[0];
  btn.onclick = function () {
    modal.style.display = "block";
  }
  span.onclick = function () {
    modal.style.display = "none";
  }
  window.onclick = function (event) {
    if (event.target == modal) {
      modal.style.display = "none";
    }
  }
}

function displayjobs(id) {
  var x = document.getElementById("rightside");
  x.style.display ="block";

}

function displaymenu(id) {
  var x = document.getElementById("rightside");
  x.style.display ="none";
}

document.getElementById("stars").innerHTML = getStars(2);

function getStars(rating) {

  // Round to nearest half
  rating = Math.round(rating * 2) / 2;
  let output = [];

  // Append all the filled whole stars
  for (var i = rating; i >= 1; i--)
    output.push('<i class="fa fa-star" aria-hidden="true" style="color: gold;"></i>&nbsp;');

  // If there is a half a star, append it
  if (i == .5) output.push('<i class="fa fa-star-half-o" aria-hidden="true" style="color: gold;"></i>&nbsp;');

  // Fill the empty stars
  for (let i = (5 - rating); i >= 1; i--)
    output.push('<i class="fa fa-star-o" aria-hidden="true" style="color: gold;"></i>&nbsp;');

  return output.join('');

}