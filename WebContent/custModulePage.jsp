<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DBS Asia's Best</title>

<style>
@import url(https://fonts.googleapis.com/css?family=Roboto:300);

body{
    background-image: url("http://imgview.info/download/20150430/city-background-1920x1200.jpg");
    background-repeat:repeat;
}
header img {
    width:70%;
    display:block;
    margin-left:auto;
        margin-right:auto;
    
}

.topbar{
width:70%;
margin:auto;

}
/* Menu Link Styles */
.toplevel a{
  	font-family: "Roboto";
    font-size:16px;
    color: #FFFFFF;
    text-decoration: none;
    line-height: 50px;
    display: block; /*mouse over on block, not just links*/
    
}
.toplevel a:hover { color: #FFFFFF; }

.toplevel
{
    list-style: none;
    padding: 0;
    margin: 0;
    clear:left;
    
}


.toplevel > li
{
    position: relative;
    float: left;/*puts list items in a horizontal line*/
    height: 50px;
    width: 25%;
    background: linear-gradient(  red, #FF4500 );           /* A gradient on 45deg axis starting blue and finishing red */;
    text-align:center;    
    line-height:50px;
    z-index:1000;
    
    
}

.toplevel > li:hover { background: #F31417; }

.toplevel li:hover > ul
{
    /* On hover, display the next level's menu */
    display: inline;
}

.secondlevel
{
    position: absolute;
    top: 50px;
    left: 0;
    width: 100%;
    list-style: none;
    padding: 0;
    margin: 0;
    display: none;
}

.secondlevel > li
{
    position: relative;
    height: 50px;
    background: linear-gradient( 45deg,#FF4500, red );  ;
}
.secondlevel > li:hover { background: #F31417; }

.thirdlevel
{
    position: absolute;
    top: 0;
    right: -100%;
    width: 100%;
    list-style: none;
    padding: 0;
    margin: 0;
    display: none;
}

.thirdlevel > li
{
    height: 50px;
background: linear-gradient( 45deg,#FF4500, red );  
}

.thirdlevel > li:hover { background: #F31417; }

nav {
 font-family: "Roboto", sans-serif;
 float:left;
 clear:left;
 width: 10%;
 height:995px;
 margin-left:15%;
 background:black;           /* A gradient on 45deg axis starting blue and finishing red */
 padding-left:20px;
 padding-top:10px;
}

nav>iframe{
 background:black;           /* A gradient on 45deg axis starting blue and finishing red */

}

section{
width:58.95%;
float:left;
height:1005px;

}
iframe{

height:100%;
width:100%;
    background-image: url("https://www.dbs.com.sg/iwov-resources/images/backgrounds/page-background.png");
    background-repeat: repeat;
}

footer{

  font-family: Verdana;
font-size:12px;
color:white;
background:  #F31417 ;  
clear:left;
text-align:center;
z-index: 10;
height: 35px;
width:70%;
line-height:35px;
margin-left:auto;
margin-right:auto;
}

a:link {
    vertical-align: middle;
    text-decoration: none;
    color: white;
}

a:visited {

    text-decoration: none;
        color: white;
}

a:hover {

    text-decoration: none;
        color: white ;
}

a:active {

    text-decoration: none;
}

.grow { transition: all .2s ease-in-out; }
.grow:hover { transform: scale(1.2); }

</style>

</head>
<body>
<header>
  <img src="http://i.imgur.com/O2byxTz.jpg" alt="logo" />
</header>
<div class = "topbar">
<ul class = "toplevel">
	<li>
	<a  class = "grow" href = "custModulePage.jsp">Home</a>
	</li>
	
	<li>
	<a  class = "grow" href = "#">Products</a>
		<ul class = "secondlevel">
			<li>
				<a  class = "grow" href = "#">Product 1</a>
				<ul class= "thirdlevel">
					<li><a  class = "grow" href = "#">Product 1A</a></li>
					<li><a  class = "grow" href = "#">Product 1B</a></li>
					<li><a  class = "grow" href = "#">Product 1C</a></li>			
				</ul> <!--close third level of homepage1-->
			</li><!--close Homepage1-->
		
			<li>
				<a  class = "grow" href = "#">Product 2</a>
				<ul class= "thirdlevel">
					<li><a  class = "grow" href = "#">Product 2A</a></li>
					<li><a  class = "grow" href = "#">Product 2B</a></li>
					<li><a  class = "grow" href = "#">Product 2C</a></li>			
				</ul><!--close third level of homepage2-->
			</li><!--close Homepage2-->
			
			<li>
				<a  class = "grow" href = "#">Product 3</a>
				<ul class= "thirdlevel">
					<li><a  class = "grow" href = "#">Product 3A</a></li>
					<li><a  class = "grow" href = "#">Product 3B</a></li>
					<li><a  class = "grow" href = "#">Product 3C</a></li>			
				</ul><!--close third level of homepage3-->
			</li><!--close Homepage3-->
		</ul><!--close second levels list(Homepage 1,2,3)-->
	</li>

	<li>	
	<a  class = "grow" href = "homepage.html" onclick = "return confirm('Confirm Logout?')">Logout</a>
	</li>
	
	<li>	
	<a  class = "grow" href = "contactus.html" target="iframe">Contact</a>
	</li>
</ul>	
</div>
<nav>	
<!--     <a class = "grow" href="https://www.youtube.com/embed/aqsbYFN8QCY?autoplay=1" target = "iframe">Corporate Video </a></br>
    <a class = "grow" href="aboutus.html" target="iframe">About Us</a></br>
    <a class = "grow" href="#">Links</a></br> -->
    <iframe src = "custSideBar.jsp" frameborder="0" name = "sideiframe"></iframe>
</nav>

<section>
<iframe src="custOverview.jsp"   name = "iframe" frameBorder="0"></iframe> 
</section>

<footer>
© 2001-2016 Copyright DBS
</footer>
</body>
</html>