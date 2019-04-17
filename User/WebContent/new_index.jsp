<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Subscribed NGO Events</title>
<style>
    		body{
        		margin: 0;
        		padding: 0;
        		font-family: sans-serif;
        		background-image: url("http://www.marbellaislesnaples.org/marbellaisles/images/container_bg.jpg");
        		background-size: cover;
    		}
    		.Events{
        		width: 90px;
        		position: absolute;
        		top: 4%;
        		left: 95%;
        		transform: translate(-50%,-50%);
        		color: #5DADE2;
    		}
    		.Events h1{
        		float: left;
        		font-size: 40px;
        		border-bottom: 6px solid #5DADE2;
        		margin-bottom: 10px;
        		padding: 2px 0
    		}
    		.btn{
        		width:100%;
        		background: none;
        		border: 2px solid #5DADE2;
        		color: #5DADE2;
        		padding: 5px;
        		font-size: 18px;
        		cursor: pointer;
        		margin: 5px 0;
        		position: absolute;
        		top: 5%;
    		}
    		.btn1{
    			width:10%;
        		background: none;
        		border: 2px solid #5DADE2;
        		color: #5DADE2;
        		padding: 5px;
        		font-size: 18px;
        		cursor: pointer;
        		margin: 5px 0;
        		position: absolute;
        		top: 5%;
        		left: 5%;
        		
    		}
    		table, th{
        		border-collapse: collapse;
        		padding: 15px;
        		text-align: left;
        		color: #5DADE2;
        		font-size: 18px;
        		font-family: sans-serif;
        		width: 93%;
        		position: absolute;
        		top: 10%;
        		left: 10%;
    		}
     		.dropdown {
        		width: 8%;
        		background: black;
        		border: 2px solid #5DADE2;
        		color: #5DADE2;
        		padding: 5px;
        		font-size: 18px;
        		position: absolute;
        		top:10%;
        		left:1%;
    		} 
    		
    		.details{
    			border-collapse: collapse;
        		padding: 15px;
        		text-align: left;
        		color: #5DADE2;
        		font-size: 18px;
        		font-family: sans-serif;
        		width: 80%;
        		position: absolute;
        		left: 20%;
    			
    		}
		</style>
		
		<script>
			function Find() {
				if (navigator.geolocation) {
    				navigator.geolocation.getCurrentPosition(function(position, html5error) {
    					geo_loc = showPosition(position);
    				});
    			}
			}

    		function showPosition(position) {
    			document.getElementById("lat").value = position.coords.latitude;
    			document.getElementById("lon").value = position.coords.longitude;	
    			document.getElementById("filter").value = 'None';	
    			document.getElementById("form").submit();	
			}
		</script>
</head>

<body>
	<%
    	if (request.getSession().getAttribute("username") == null) {
           	response.sendRedirect("Login");
           	return;
       	}
    %>
   		<table>

			<%
				ArrayList<String[]> events = (ArrayList<String[]>)request.getSession().getAttribute("result");
				if (events.size() > 0) {
					for (int i = 0; i < events.size(); i++) {
						String[] read = events.get(i);
			%>
						<tr>
        					<td>
        						<div style="font-size:35px"><%=read[0] %></div>
                				<div style="font-size:20px">Description: <%=read[1] %></div>
                				<div style="font-size:20px">Location: <%=read[2] %></div>
                				<div style="font-size:20px">Start Date: <%=read[3] %></div>
                				<div style="font-size:20px">End date: <%=read[4] %></div>
                				<div style="font-size:20px">Start Time: <%=read[5] %></div>
                				<div style="font-size:20px">End Time: <%=read[6] %></div><br>
        					</td>
        					
        				</tr>					
			<%			
					}
				} else {
					response.getWriter().append("<script>alert('No Subscribed Events');</script>");
				}
			%>
		</table>
		
		<div class="Events">
			<form action="hello" method="POST">
    			<input class="btn" type="submit" name="" value="Logout">
    		</form>
		</div>
		<form id="form" action="Subscribed" method="post">
    			<input id="lat" name="lat" type="hidden" readonly>
    			<input id="lon" name="lon" type="hidden" readonly>	
    			<input id="filter" name="filter" type="hidden" readonly>
    			<input class="btn1" type="button" value="Find NearBy Ngo" onclick="Find()">
    	</form>
	
</body>
</html>