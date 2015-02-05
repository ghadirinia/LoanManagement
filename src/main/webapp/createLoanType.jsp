
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Registration</title>
    <style>
        body {background-color:midnightblue}
        h1   {color:yellow}
        button    {color:black ; background-color: lightyellow}
        h3 {color: yellow}
        div.one { border:2px solid ;border-color: yellow ;margin:20px; padding:20px; width: 93%}
        p.one {color: yellow ; align-content: center}
        textarea {background-color: lightyellow ; }
    </style>
</head>
<body>

<h1>new "Loan Type"</h1>
<form method="get" action="grantConditions.jsp">
    <div class="one">
        <h3>Create a new "Loan File"</h3>
        <p id="salam">
        <div class="two">
            <p class="one">
                Name of Loan Type:  <input cols="50" rows="1" name="facilityName"/>
            </p>
            <p class="one">
                Interest Rate:  <input cols="50" rows="1" name="interestRate"/>
            </p>
            <p class="one">
                <button cols="50" rows="1" onclick="location.href='grantConditions.jsp'">Sign Up!</button>
            </p>
        </div>
        </p>
    </div>
</form>
</body>
</html>