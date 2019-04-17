<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.util.*" %>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
	<style>
    body{
      	margin: 0;
        padding: 0;
        font-family: sans-serif;
        background-image: url("http://www.marbellaislesnaples.org/marbellaisles/images/container_bg.jpg");
        background-size: cover;
    }

    .NGO{
        width: 125px;
        position: absolute;
        top: 40%;
        left: 80%;
        transform: translate(-50%,-50%);
        color: #5DADE2;
    }
    .NGO h1{
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
        margin: 12px 0;
    }
    table, th{
        border-collapse: collapse;
        padding: 15px;
        text-align: left;
        color: #5DADE2;
        font-size: 18px;
        font-family: sans-serif;
        position: absolute;
        top: 15%;
        left: 20%;
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
            			url : "NgoHome",
            			data : "btn=" + name,
            			success : function(response) {
                			alert(response);
            			}
        			});
        			e.preventDefault();
    			});
			});
		</script>

		<script type="text/javascript">
			function Donate() {
				window.open("https://74262355.ngrok.io/senproject/index.php");
			}
		</script>
	</head>

	<body>
	<div class="NGO">
	<form class="clk" name="Subscribe" action="NgoHome" method="POST">
    	<input class="btn" type="submit"  name="" value="Subscribe">
    </form>
    <form class="clk" name="Unsubscribe" action="NgoHome" method="POST">
    	<input class="btn" type="submit"  name="" value="Unsubscribe">
    </form>
    
    <input class="btn" type="button" onclick="Donate()" name="" value="Donate">
    
	</div>
	
		
		<br>
		<br>
		<br>
		<br>

		<table>

			<%
				ArrayList<String[]> events = (ArrayList<String[]>)request.getSession().getAttribute("events-list");
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
					response.getWriter().append("<script>alert('No Events');</script>");
				}
			%>
		</table>

	</body>
	
</html>