<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Registration</title>
    <style>
        body {background-color:midnightblue}
        h1   {color:yellow}
        button    {color:black ; background-color: lightyellow; }
        h3 {color: yellow}
        div.one { border:2px solid ;border-color: yellow ;margin:20px; padding:20px; width: 93% ; float: right}
        div.two { border:2px solid ;border-color: yellow ;margin:20px; padding:20px; width: 93% ; float: right}
        p.one {color: yellow ; align-content: center}
        textarea {background-color: lightyellow ; }
    </style>
</head>
<body>
<h1>Registration</h1>
<form method="post" action="SearchHandlerRealCustomer" id="1">
<div class="one">

    <h3>Registration a "Real customer"</h3>
    <p>
    <div class="two">
        <p  class="one">
            First Name:  <input type="text" cols="50" rows="1" name="first">
        </p>

        <p class="one">
            Last Name:  <input type="text" cols="50" rows="1" name="last">
        </p>

        <p class="one">
            National Code:  <input type="text" cols="50" rows="1" name="nationalId">

        <p class="one">
            Customer Code:  <input type="text" cols="50" rows="1" name="customerId"> <br> <br><button>Search</button>
        </p>
    </div>
    </p>
</div>
</form>
</body>
</html>