<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Past Events</title>
		<style>
	    	body{
        		margin: 0;
        		padding: 0;
        		font-family: sans-serif;
        		background-image: url("https://image.freepik.com/free-photo/abstract-blank-chalkboard-black-background-texture-text-drawing_52494-312.jpg");
        		background-size: cover;
    		}
    		.Events1{
        		width: 90px;
        		position: absolute;
        		top: 8%;
        		left: 95%;
        		transform: translate(-50%,-50%);
        		color: white;
    		}
    		.Events1 h1{
        		float: left;
        		font-size: 40px;
        		border-bottom: 6px solid #95A5A6;
       	 		margin-bottom: 50px;
       	 		padding: 13px 0
    		}
    		.Events{
        		width: 120px;
        		position: absolute;
        		top: 15%;
        		left: 10%;
        		transform: translate(-50%,-50%);
        		color: white;
    		}
    		.Events h1{
        		float: left;
        		font-size: 40px;
        		border-bottom: 6px solid #95A5A6;
        		margin-bottom: 50px;
        		padding: 13px 0;
    		}
    		.btn{
        		width:13%;
        		background: none;
        		border: 2px solid #95A5A6;
        		color: white;
        		padding: 5px;
        		font-size: 18px;
        		cursor: pointer;
        		margin: 12px 0;
        		position: absolute;
        		top: 7%;
        		left: 4%;
    		}
    		table, th{
            	border-collapse: collapse;
            	width: 80%;
            	position: absolute;
            	top: 20%;
            	left: 20%;
            	padding: 15px;
            	text-align: left;
            	color: white;
            	font-size: 18px;
            	font-family: sans-serif;
    		}
    		.click{
    			background: none;
    			width: 25%;
    			border: 2px solid #95A5A6;
    			color: white;
    			padding: 3px;
    			font-size: 18px;
    			cursor: pointer;
    			margin: 10px 0;
    		}

		</style>
		
		<script>
			function relocate() {
				window.location.replace("Dashboard");
			}
		</script>
		
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function() {
				$(".clk").submit(function(e) {
        			e.preventDefault();
        			var name = $(this).attr('name');
        			$.ajax({
            			type : "POST",
            			url : "PastEvent",
            			data : "EventName=" + name,
            			success : function(response) {
                			alert(response);
                			window.location.reload();
            			}
        			});
        			e.preventDefault();
    			});
			});
		</script>
		
</head>
<body>

	<input type="button" value="Back To Dashboard" class="btn" onclick="relocate()">
	<table>
    		<%
        		ArrayList<String[]> list = (ArrayList<String[]>)request.getSession().getAttribute("pastevents");
        		if (list.size() == 0) {
        			response.getWriter().append("<script>alert('No Saved Past Events');</script>");
        			%><script>window.location.replace("Dashboard")</script><%
        			return;
        		}
        		for (int i = 0; i < list.size(); i++) {
            		String[] event = list.get(i);
    		%>
        			<tr>
            			<td>
            				<div style="font-size:35px"><%=event[0] %></div>
            				<div style="font-size:20px">Description: <%=event[1] %></div>
            				<div style="font-size:20px">Location: <%=event[2] %></div>
            				<div style="font-size:20px">Start Date: <%=event[3] %></div>
            				<div style="font-size:20px">End date: <%=event[4] %></div>
            				<div style="font-size:20px">Start Time: <%=event[5] %></div>
            				<div style="font-size:20px">End Time: <%=event[6] %></div><br><br>
            			</td>
            			<td>
            				<form action="PastEvent" method="POST" class="clk" name=<%=event[0] %>>
                				<input class="click" type="submit" value="Remove">
							</form>
            			</td>
            		</tr>
            <%
        		}
            %>
</body>
</html>