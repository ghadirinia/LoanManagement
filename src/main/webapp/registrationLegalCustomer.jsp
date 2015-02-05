
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

<h1>Registration</h1>
<form method="post" action="RegistrationLegalCustomer">
<div class="one">
    <h3>Registration a "Legal customer"</h3>
    <p id="salam">
     <div class="two">
         <p class="one">
             Name:  <textarea cols="50" rows="1" name="name"></textarea>
         </p>
        <p class="one">
            Registration Date:  <textarea cols="50" rows="1" name="registerDate"></textarea>
        </p>
        <p class="one">
            Economic Code:  <textarea cols="50" rows="1" name="economicId"></textarea>
        </p>
        <p class="one">
            <button cols="50" rows="1" >Sign Up!</button>
        </p>
     </div>
    </p>
</div>
</form>
</body>
</html>