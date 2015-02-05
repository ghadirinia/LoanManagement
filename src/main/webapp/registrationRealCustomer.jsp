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
<form method="post" action="RegistrationRealCustomerServlet">
<div class="one" id="salam">
    <h3>Registration a "Real customer"</h3>
    <p>
    <div class="two">
        <p class="one">
            First Name:  <input cols="50" rows="1" name="first"/>
        </p>
        <p class="one">
            Last Name:  <input cols="50" rows="1" name="last"/>
        </p>
        <p class="one">
            Father Name:  <input cols="50" rows="1" name="fatherName"/>
        </p>
        <p class="one">
            Birth Date:  <input cols="50" rows="1" name="birthDate"/>
        </p>
        <p class="one">
            National Code:  <input calss="text-input" type="text" cols="50" rows="1" name="nationalId"/>
        </p>
        <p class="one">
            <button cols="50" rows="1" onclick="myFunction()">Sign Up!</button>
        </p>
    <script>
        function myFunction() {
            var check="FALSE";
            var nationalId_filed = document.getElementsByName("nationalId")[0];
            var nationalId = nationalId_filed.value;
            if(nationalId.length===10){
                var sum = 0;
                var i ;
                for(i=0;i<9;i++){
                    sum += parseInt(nationalId.charAt(i))*(i+1);
                }
                if(sum > 0)
                    check=(sum%11===parseInt(nationalId.charAt(9)))?"TRUE":((sum%11)===(11-(parseInt(nationalId.charAt(9)))))?"TRUE":"FALSE";
            }
            if(check==="FALSE")
                document.getElementById('salam').innerHTML="<p class='one' color='yellow'>NationalId is incorrect.</p>";

        }

    </script>
    </div>
    </p>
</div>
</form>
</body>
</html>