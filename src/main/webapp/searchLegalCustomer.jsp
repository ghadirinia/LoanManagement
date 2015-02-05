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
<form method="post" action="SearchHandlerLegalCustomer" id="1">
<div class="one">
    <h3>Registration a "Legal customer"</h3>
    <p>
    <div class="two">
        <p  class="one">
            Name:  <input type="text" cols="50" rows="1" name="name"> <br> <br>
        </p>


        <p class="one">
            Economic Code:  <input type="text" cols="50" rows="1" name="economicId"> <br> <br>
        </p>

        <p class="one">
            Customer Number: <label>
            <input type="text" cols="50" rows="1" name="customerId">
        </label> <br> <br><button>Search</button>
        </p>
    </div>
    </p>
</div>
</form>
</body>
</html>