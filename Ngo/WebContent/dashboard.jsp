<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<style>
	
			.resp-sharing-button__link,
			.resp-sharing-button__icon {
  				display: inline-block
			}

			.resp-sharing-button__link {
  				text-decoration: none;
  				color: #fff;
  				margin: 0.5em
			}

			.resp-sharing-button {
  				border-radius: 5px;
  				transition: 25ms ease-out;
  				padding: 0.5em 0.75em;
  				font-family: Helvetica Neue,Helvetica,Arial,sans-serif
			}

			.resp-sharing-button__icon svg {
  				width: 1em;
  				height: 1em;
  				margin-right: 0.4em;
  				vertical-align: top
			}

			.resp-sharing-button--small svg {
  				margin: 0;
  				vertical-align: middle
			}

			/* Non solid icons get a stroke */
			.resp-sharing-button__icon {
  				stroke: #fff;
  				fill: none
			}

			/* Solid icons get a fill */
			.resp-sharing-button__icon--solid,
			.resp-sharing-button__icon--solidcircle {
  				fill: #fff;
  				stroke: none
			}

			.resp-sharing-button--google {
  				background-color: #dd4b39
			}

			.resp-sharing-button--google:hover {
  				background-color: #c23321
			}

			.resp-sharing-button--email {
  				background-color: #777
			}

			.resp-sharing-button--email:hover {
  				background-color: #5e5e5e
			}

			.resp-sharing-button--email {
  				background-color: #777777;
  				border-color: #777777;
			}

			.resp-sharing-button--email:hover,
			.resp-sharing-button--email:active {
 	 			background-color: #5e5e5e;
  				border-color: #5e5e5e;
			}

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
        		width:100%;
        		background: none;
        		border: 2px solid #95A5A6;
        		color: white;
        		padding: 5px;
        		font-size: 18px;
        		cursor: pointer;
        		margin: 12px 0;
    		}
    		table, th{
            	border-collapse: collapse;
            	width: 80%;
            	position: absolute;
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

		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function() {
				$(".clk").submit(function(e) {
        			e.preventDefault();
        			var name = $(this).attr('name');
        			$.ajax({
            			type : "POST",
            			url : "DeleteEvent",
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
		<script>
			$(document).ready(function() {
				$(".clk2").submit(function(e) {
					e.preventDefault();
					var name = $(this).attr('name');
					$.ajax({
						type : "GET",
						url : "DeleteEvent",
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

		<div class="Events1">
			<form action="Dashboard" method="POST" id="logout">
       			<input class="btn" type="submit" value="Logout">
    		</form>
		</div>

		<div class="Events">
			<form action="EditEvent" method="GET" id="form">
				<input class="btn" type="submit" id="Event_Name" value="Add events">
			</form>
			<form action="PastEvent" method="get">
				<input class="btn" type="submit" value="Past Events">
			</form>
		</div>

		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<table>
    		<%
        		ArrayList<String[]> list = (ArrayList<String[]>)request.getSession().getAttribute("Events");
        		if (list.size() == 0) {
        			response.getWriter().append("<script>alert('No Existing Events');</script>");
        		}
        		for (int i = 0; i < list.size(); i++) {
            		String[] event = list.get(i);
    		%>
        			<tr>
            			<td><br>
            				<div style="font-size:35px"><%=event[0] %></div>
            				<div style="font-size:20px">Description: <%=event[1] %></div>
            				<div style="font-size:20px">Location: <%=event[2] %></div>
            				<div style="font-size:20px">Start Date: <%=event[3] %></div>
            				<div style="font-size:20px">End date: <%=event[4] %></div>
            				<div style="font-size:20px">Start Time: <%=event[5] %></div>
            				<div style="font-size:20px">End Time: <%=event[6] %></div><br><br>
            			</td>
            			<td>
            				<form action="EditEvent" method="GET">
    							<input type="hidden" name="event_name" value=<%=event[0] %> readonly>
    							<input type="hidden" name="description" value=<%=event[1] %> readonly>
    							<input type="hidden" name="location" value=<%=event[2] %> readonly>
    							<input type="hidden" name="sd" value=<%=event[3] %> readonly>
    							<input type="hidden" name="ed" value=<%=event[4] %> readonly>
    							<input type="hidden" name="st" value=<%=event[5] %> readonly>
    							<input type="hidden" name="et" value=<%=event[6] %> readonly>
    							<button class="click" type="submit">Edit</button>
							</form>	
							<form action="DeleteEvent" method="POST" class="clk" name=<%=event[0] %>>
                				<input class="click" type="submit" value="Remove">
							</form>
							<form action="DeleteEvent" method="get" class="clk2" name=<%=event[0] %>>
								<input class="click" type="submit" value="Finish">
							</form>
							<form target="_blank"
							action="mailto:dummy1@chamisplace.com?subject= Event details"
							method="POST"
							href="www.gmail.com"
							enctype="multipart/form-data"
  							name="EmailTestForm">
    							<input type="hidden" name="Event_Name" value=<%=event[0] %> readonly>
    							<input type="hidden" name="Description" value=<%=event[1] %> readonly>
    							<input type="hidden" name="Location" value=<%=event[2] %> readonly>
    							<input type="hidden" name="Start_Date" value=<%=event[3] %> readonly>
    							<input type="hidden" name="End_Date" value=<%=event[4] %> readonly>
    							<input type="hidden" name="Start_Time" value=<%=event[5] %> readonly>
    							<input type="hidden" name="End_Time" value=<%=event[6] %> readonly>
    							<button class="click" type="submit">Share</button>
							</form>	
            			</td>       
        			</tr>
    				<%
        				}
    				%>
		</table>	
	</body>
</html>