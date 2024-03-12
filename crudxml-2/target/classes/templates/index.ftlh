<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Information Form</title>
</head>
<body>
    <h1>User Information Form</h1>
    <form action="/process" method="post">
    	
    	<label for="id">customerId:</label><br>
        <input type="text" id="customerId" name="customerId"><br>
    
    
        <label for="name">customerName:</label><br>
        <input type="text" id="customerName" name="customerName"><br>
        
        <label for="department">customerAddress:</label><br>
        <input type="text" id="customerAddress" name="customerAddress"><br>
        
        
        <input type="submit" value="Submit">
    </form>
</body>
</html>
