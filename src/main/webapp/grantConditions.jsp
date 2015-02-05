<%@ page import="domain.RealCustomer" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="domain.GrantCondition" %>

<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Registration</title>
    <style>
        body {
            background-color: midnightblue
        }

        h1 {
            color: yellow
        }

        button {
            color: black;
            background-color: lightyellow
        }

        h3 {
            color: yellow
        }

        div.one {
            border: 2px solid;
            border-color: yellow;
            margin: 20px;
            padding: 20px;
            width: 93%
        }

        p.one {
            color: yellow;
            align-content: center
        }

        p {
            color: yellow;
            align-content: center
        }

        textarea {
            background-color: lightyellow;
        }

        table.table {
            border-color: yellow;
            border: 2px solid;
            margin: 20px;
            padding: 20px;
            width: 90%;
            color: yellow;
            border-collapse: collapse;
        }

        th, td {
            border-color: yellow;
            border: 2px solid;
            color: yellow;
            border-collapse: collapse;
            align-content: center;
            align-items: center;
            text-align: center;
        }
    </style>
</head>
<body>

<h1>Registration Grant Conditions</h1>

<form method="post" action="SaveLoanTypeServlet">
    <div class="one" id="salam">
        <h3>Registration "Grant Conditions" for New Loan Type</h3>

        <p>

        <div class="two">
            <p class="one">
                Grant Conditions' Name: <input cols="50" rows="1" name="grantName"/>
            </p>

            <p class="one">
                Minimum Duration: <input cols="50" rows="1" name="minimumDuration"/>
            </p>

            <p class="one">
                Maximum Duration: <input cols="50" rows="1" name="maximumDuration"/>
            </p>

            <p class="one">
                Minimum Amount of Contract: <input cols="50" rows="1" name="minimumAmount"/>
            </p>

            <p class="one">
                Maximum Amount of Contract: <input calss="text-input" type="text" cols="50" rows="1" name="maximumAmount"/>
            </p>

            <p class="one">
                <input type="button" style="width: 100px;height: 30px" onclick="updateList()" value="Add To List!"/>
            </p>

            <p class="one">
                <button type="submit" style="width: 100px;" onclick="Register()">Register!</button>
            </p>
            <input type="hidden" id="hidden" name="hidden"/>

            <input type="hidden" id="one" name="one" />
            <input type="hidden" id="two" name="two" />
        </div>
        </p>
        <div>
            <table id="table" class="table" name="table">
                <tr>
                    <td><p>Grant Name</p></td>
                    <td><p>Minimum Duration</p></td>
                    <td><p>Maximum Duration</p></td>
                    <td><p>Minimum Amount</p></td>
                    <td><p>Maximum Amount</p></td>
                </tr>
            </table>
        </div>
    </div>
</form>

<script>

    document.getElementById("hidden").setAttribute("value","<%=request.getParameter("facilityName")%>");
    document.getElementById("two").setAttribute("value","<%=request.getParameter("interestRate")%>");
    function updateList() {
        var list = "";
        var htmlResponse = "";
        var grantName = document.getElementsByName("grantName")[0].value;
        var minimumDuration = document.getElementsByName("minimumDuration")[0].value;
        var maximumDuration = document.getElementsByName("maximumDuration")[0].value;
        var minimumAmount = document.getElementsByName("minimumAmount")[0].value;
        var maximumAmount = document.getElementsByName("maximumAmount")[0].value;
        var list = "<td>";
        list += grantName;
        list += "</td><td>";
        list += minimumDuration;
        list += "</td><td>";

        list += maximumDuration;
        list += "</td><td>";

        list += minimumAmount;
        list += "</td><td>";
        list += maximumAmount;
        list += "</td>";


        htmlResponse += "<tr name='row'>";

        htmlResponse += list;


        htmlResponse += "</tr>";


        document.getElementById("table").innerHTML +=htmlResponse;
    }


    function Register() {
      var i ;
      var j ;
      var list = "";
      var row = new Array();
      row = document.getElementsByName("row");
      var cols = new Array();
      for(i =0;i<row.length;i++){
        cols = document.getElementsByName("row")[i].getElementsByTagName("td");
        for(j=0;j<cols.length;j++){
            list +=cols[j].innerHTML+" ";
        }
        list += "_";
      }
        document.getElementById("one").setAttribute("value",list);
    }
</script>
</body>
</html>