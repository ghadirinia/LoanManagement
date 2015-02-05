<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Home</title>
    <style>
        body {background-color:midnightblue}
        h1   {color:yellow}
        button    {color:black ; background-color: lightyellow}
        h3 {color: yellow}
        div.one { border:2px solid ;border-color: yellow ;margin:20px; padding:20px; width: 40% ; float: left }
    </style>
</head>
<body>
<h1>Home</h1>
<div class="wrap">
    <div class="one">
        <h3>Registration a "customer"</h3>
        <p>
            <br>
            <br>
            <button type="button" onclick="location.href='registrationLegalCustomer.jsp'">Add a "Legal Customer" </button>
            <br>
            <br>
            <br>
            <button type="button" onclick="location.href='registrationRealCustomer.jsp'">Add a "Real Customer"    </button>
        </p>
    </div>
    <div class="one">
        <h3>Search a "Customer"</h3>
        <p>
            <br>
            <br>
            <button type="button" onclick="location.href='searchLegalCustomer.jsp'">Search a "Legal Customer" </button>
            <br>
            <br>
            <br>
            <button type="button" onclick="location.href='searchRealCustomer.jsp'">Search a "Real Customer"    </button>
        </p>

    </div>

</div>
<div>
<div class="one">
    <h3>Create a new "Loan Type"</h3>
    <p>
        <br>
        <br>
        <button type="button" onclick="location.href='createLoanType.jsp'">Create New Loan Type </button>
        <br>
        <br>
    </p>
</div>

<div class="one">
    <h3>Create a new "Loan File"</h3>
    <p>
        <br>
        <br>
    <form method="post" action="GrantConditionsServlet">
      <button  onclick="location.href='GrantConditionsServlet'">Create A Loan File for a Real Customer </button>
    </form>
    </p>
</div>
</div>
</body>
</html>