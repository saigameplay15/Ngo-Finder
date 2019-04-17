<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.util.*" %>
    
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Nearby NGOs</title>
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
        		left: 3%;
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

		<script type="text/javascript" >
			$(document).ready(function() {
				$(".click").click(function() {
					document.getElementById("NgoName").value = $(this).attr("id");
					document.getElementById("form").submit();
				});
			});
		</script>

		<script>
    		function applyFilter(object) {
    			var name = object.value;
    			window.location.replace("hello?filter=" + name);
    		}
		</script>

	<body>
    	<%
        	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        	response.setHeader("Pragma","no-cache");
        	if (request.getSession().getAttribute("username") == null) {
            	response.sendRedirect("Login");
            	return;
        	}
    	%>
    
    	<br>
    	<br>
    	<br>
    	
    	<table>
    		<tr>
    			<th>
    				<br>
    				<br>
    				<select id="Category" class=dropdown onchange="applyFilter(this)">
        			<option value="None">Select</option>
        			<option value="Food">Food</option>
        			<option value="Clothes">Clothes</option>
        			<option value="Blood">Blood</option>
        			<option value="None">None</option>
    				</select>
    				<p id="filter" style="font-size:23px" onkeyup='saveValue(this);'></p>
    				
    			</th>
    		</tr>
    				
    	</table>
    	
    	
                        
		<br> 
		<br>   
		<br>
		<br>
		<br>
		<br>
		<br>
		<table class="details">

			<%
				ArrayList<String[]> list = (ArrayList<String[]>)request.getSession().getAttribute("results");
				if (list != null && list.size() != 0) {
					for (int i = 0; i < list.size(); i++) {
						String[] read = list.get(i);
			%>			
						<tr>     
        					<td>
        						<div  style="font-size:40px"><%=read[0] %></div>
          						<div  style="font-size:20px">Distance: <%=read[1] %> KM</div>
          						<div  style="font-size:20px">Type: <%=read[4] %></div>
          						<div  style="font-size:20px"><%=read[3] %></div>
          						<div  style="font-size:20px"><%=read[2] %></div>
          						<div>
          						<form action="NgoHome" method="get" id="form">
            						<input type="hidden" name="NgoName" value=<%=read[2] %> readonly>
            						<input type="submit" class="btn1" value="View Events">
    							</form>
        						</div> 
          						<br>
          					</td>    
    					</tr>  	
    				
			<%		
					}
				} else {
					response.getWriter().append("<script>alert('No nearby NGOs with required Filter');</script>");
				}
			%>

		</table>

		<div class="Events">
			<form action="hello" method="POST">
    			<input class="btn" type="submit" name="" value="Logout">
    		</form>
		</div>

	</body>
</html>



