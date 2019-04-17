<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
	<head>
		<title>Add New Event</title>
		<style>
    		body{
        		margin: 0;
        		padding: 0;
        		font-family: sans-serif;
        		background-image: url("https://image.freepik.com/free-photo/abstract-blank-chalkboard-black-background-texture-text-drawing_52494-312.jpg");
        		background-size: cover;
    		}
    		.event-box{
        		width: 280px;
        		position: absolute;
        		top: 50%;
        		left: 50%;
        		transform: translate(-50%,-50%);
        		color: white;
    		}
    		.event-box h1{
        		float: left;
        		font-size: 40px;
        		border-bottom: 6px solid #95A5A6;
        		margin-bottom: 50px;
        		padding: 13px 0;
    		}
    		.textbox{
        		width: 100%;
        		overflow: hidden;
        		font-sie: 20px;
        		padding: 8px 0;
        		margin: 8px 0;
        		border-bottom: 1px solid #95A5A6;
    		}
    		.textbox input{
    		    border: none;
    		    outline: none;
    		    background: none;
    		    color: white;
    		    font-size: 18px;
    		    width: 170px;
    		    float: left;
    		    margin: 0 10px;
    		}
    		.textbox i{
        		width: 26px;
        		float: left;
        		text-align: center;
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
		</style>

		<script type="text/javascript">
			function loadDoc() {
	  			var xhttp = new XMLHttpRequest();
	  			xhttp.onreadystatechange = function() {
	    			if (this.readyState == 4 && this.status == 200) {
	    				alert("Successful");
	    				window.location.replace("Dashboard");
	    			}
	  			};
	  			xhttp.open("POST", "EditEvent", true);
	  			xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	  			var name = document.getElementById('EventName').value;
	  			var desc = document.getElementById('Description').value;
	  			var loc = document.getElementById('Location').value;
	  			var sd = document.getElementById('StartDate').value;
	  			var ed = document.getElementById('EndDate').value;
	  			var st = document.getElementById('StartTime').value;
	  			var et = document.getElementById('EndTime').value;
	  			var res = "EventName=" + name + "&&Description=" + desc + "&&Location=" + 
	  				loc + "&&StartDate=" + sd + "&&EndDate=" + ed + "&&StartTime=" + st + "&&EndTime=" + et;
	  			xhttp.send(res);
			}
		</script>
		
		<script>
   function validatedate() {
                  var dateText = document.getElementById("StartDate");
                  var datetext = document.getElementById("EndDate");
                  var e1,e2;
                  if (dateText) {
                      try {
                          var errorMessage = "";                         
                          var splitComponents = dateText.value.split('/');
                          if (splitComponents.length > 0) {
                              var day = parseInt(splitComponents[0]);
                              var month = parseInt(splitComponents[1]);
                              var year = parseInt(splitComponents[2]);

                              if (isNaN(day) || isNaN(month) || isNaN(year)) {
                                  errorMessage = "The day, month and year need to be numbers";
                                  alert(errorMessage);
                                  return false;
                              }

                              if (day <= 0 || month <= 0 || year <= 0) {
                                  errorMessage = "The day, month and year need to be positive values greater than 0";
                              }

                              if (month > 12) {
                                  errorMessage = "The month cannot be greater than 12.";
                              }

                              if (errorMessage == "") {
                                  // assuming no leap year by default
                                  var daysPerMonth = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
                                  if (year % 4 == 0) {
                                      // current year is a leap year
                                      daysPerMonth[1] = 29;
                                  }

                                  if (day > daysPerMonth[month - 1]) {
                                      errorMessage = "Number of days are more than those allowed for the month";
                                  }
                              }
                          } else {
                              errorMessage = "Please enter the date in dd/mm/yyyy format";
                          }

                          if (errorMessage) {
                              alert(errorMessage);
                              return false;
                          }
                      } catch (e) {
                          alert(e);
                          return false;
                      }
                  
                }
                  if (datetext) {
                      try {
                          var errorMessage = "";                         
                          var splitComponents = datetext.value.split('/');
                          if (splitComponents.length > 0) {
                              var day = parseInt(splitComponents[0]);
                              var month = parseInt(splitComponents[1]);
                              var year = parseInt(splitComponents[2]);

                              if (isNaN(day) || isNaN(month) || isNaN(year)) {
                                  errorMessage = "The day, month and year need to be numbers";
                                  alert(errorMessage);
                                  return false;
                              }

                              if (day <= 0 || month <= 0 || year <= 0) {
                                  errorMessage = "The day, month and year need to be positive values greater than 0";
                              }

                              if (month > 12) {
                                  errorMessage = "The month cannot be greater than 12.";
                              }

                              if (errorMessage == "") {
                                  // assuming no leap year by default
                                  var daysPerMonth = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
                                  if (year % 4 == 0) {
                                      // current year is a leap year
                                      daysPerMonth[1] = 29;
                                  }

                                  if (day > daysPerMonth[month - 1]) {
                                      errorMessage = "Number of days are more than those allowed for the month";
                                  }
                              }
                          } else {
                              errorMessage = "Please enter the date in dd/mm/yyyy format";
                          }

                          if (errorMessage) {
                              alert(errorMessage);
                              return false;
                          }
                      } catch (e) {
                          alert(e);
                          return false;
                      }
                  }
                loadDoc();
                  return true;
                  
}
    
</script>
		
		<script>
			function Cancel() {
				window.location.replace("Dashboard");
			}
		</script>
	</head>

	<body>
		<div class="event-box">
        	<h2>Create Event</h2>
			<form action="EditEvent" method="POST" id="form">
        		<div class="textbox">
            		<input "type="text" placeholder="Event Name" id="EventName" required>
        		</div>

        		<div class="textbox">
            		<input type="text" placeholder="Description" id="Description" required>
        		</div>

        		<div class="textbox">
            		<input type="text" placeholder="Location" id="Location" required>
        		</div>

        		<div class="textbox">
            		<input type="text" placeholder="Start Date" id="StartDate" required>
        		</div>

        		<div class="textbox">
            		<input type="text" placeholder="End Date" id="EndDate" required>
        		</div>

        		<div class="textbox">
            		<input type="text" placeholder="Start Time" id="StartTime" required>
        		</div>

        		<div class="textbox">
            		<input type="text" placeholder="End Time" id="EndTime" required>
        		</div>

        		<input class="btn" type="button" onclick="return validatedate()" value="Submit">
    		</form>    
    		
    		<input type="button" class="btn" value="Cancel" onclick="Cancel()">
    	</div>
	</body>
</html>