<!DOCTYPE html>
<html>
<head lang="en">
    <script>
        function init(){
            document.getElementsByName("economicId")[0].value = "<%=request.getParameter("nationalId")%>";}
    </script>
    <meta charset="UTF-8">
    <title>Modification</title>
    <style>
        body {background-color:midnightblue}
        h1   {color:yellow}
        button    {color:black  background-color: lightyellow}
        h3 {color: yellow}
        div.one { border:2px solid border-color: yellow margin:20px padding:20px width: 93%}
        div.two { border:2px solid border-color: yellow margin:20px padding:20px width: 93%}
        p.one {color: yellow  align-content: center}
        textarea {background-color: lightyellow  }
    </style>
</head>

<body onload="init()">

<h1>Modification</h1>
<form method="post" action="ModificationRealCustomer">
    <input type="hidden" name="nationalId"/>
    <div class="one">
        <h3>Modification a Real customer </h3>
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
                Birth Date:  <input cols="50"rows="1"name="birthDate"/>
            </p>
            <p class="one">
                Customer Code:  <input cols="50"rows="1"name="customerId"/>
            </p>
            <p class="one">
                <button cols="50" rows="1" onclick="change()">Sign Up!</button>
            </p>
        </div>
        </p>
    </div>

</form>
<script>
    function change(){

    }
</script>
</body>
</html>