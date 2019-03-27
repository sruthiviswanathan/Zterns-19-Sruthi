<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.zilker.onlinejobsearch.config.Config"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet" href="${Config.BASE_PATH}Pages/css/index.css"/>
<link rel="stylesheet" href="${Config.BASE_PATH}Pages/css/carousel.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="https://fonts.googleapis.com/css?family=Lato"
	rel="stylesheet">
<title>LOGIN/SIGNUP</title>
</head>

<body>

	<div class=container>
		<div class="container__bgimage bgimage--mod ">
			<div class="bgimage__text text--mod">
				<h3>HIRING AND GETTING HIRED JUST GOT EASIER WITH US!!!SIGN IN
					TO CONTINUE</h3>
				<div class="bgimage__text__nav nav--mod">
					<a href="${Config.BASE_PATH}companies">
						<button class="nav__button button--mod" type="submit">SIGN
							IN</button>
					</a>
				</div>
				
					<div class="slideshow-container">

					<div class="mySlides fade">
					  <div class="numbertext">1 / 3</div>
					  <div class="bgimage__overlay text">
						
					<div class="testimonials">
					   <h3>TESTIMONIALS</h3>
					    <p>"We've hired several qualified applicants who report first seeing the position on JOBHUNT, and we'll definitely utilize your free services again in future recruiting efforts. It's been great working with you!"																																															
						-Tata Communications</p>
					</div>
								
					 </div>	
					</div>
					
					<div class="mySlides fade">
					  <div class="numbertext">2 / 3</div>
					   <div class="bgimage__overlay text">
						
						<div class="testimonials">
						<h3>TESTIMONIALS</h3>
					   <p>"We are growing fast and we regularly post new jobs to JOBHUNT—it’s great to have so many quality applicants to consider for our available roles. We look forward to hiring more!"
					 	-Museum Hack-</p>
					 	</div>
								
					 </div>	
					</div>
					
					<div class="mySlides fade">
					  <div class="numbertext">3 / 3</div>
					 <div class="bgimage__overlay text">
					
					
					<div class="testimonials">
						<h3>TESTIMONIALS</h3>
					<p>"Having tried the competition with disappointing results, we enlisted the help of JOBHUNT to help us find a contracted developer. In just a couple of days, we had dozens of applications! This is compared to less than 10 elsewhere!"
					-Bodis</p>	
					</div>
								
					 </div>	
					</div>
					<a class="prev" onclick="plusSlides(-1)">&#10094;</a>
					<a class="next" onclick="plusSlides(1)">&#10095;</a>
					</div>
					
					<div style="text-align:center">
					  <span class="dot"  onclick="currentSlide(1)"></span> 
					  <span class="dot"  onclick="currentSlide(2)"></span> 
					  <span class="dot"  onclick="currentSlide(3)"></span> 
					</div>
			</div>
			
				
		</div>
	</div>

</body>
<script src="${Config.BASE_PATH}Pages/js/carousel.js"></script>
</html>