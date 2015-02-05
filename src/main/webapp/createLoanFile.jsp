<%@ page import="domain.LoanTypeVo" %>
<%@ page import="domain.LoanType" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <script>

  <%   ArrayList<LoanTypeVo> loanTypeVoArrayList = new ArrayList<LoanTypeVo>();%>
        function getIndex() {


            var select = document.getElementsByName("select")[0];
            var optionArray = [];
            optionArray = select.getElementsByTagName("option");
            var i;
            for (i = 0; i < optionArray.length; i++) {
                if (optionArray[i].selected == true) {
                    document.getElementsByName("selectedIndex")[0].value = optionArray[i].value;
                }
            }
        }
        function init() {

            document.getElementsByName("selectedCustomerId")[0].value = "<%=(String)request.getAttribute("customerCode")%>";
            document.getElementsByName("selectedIndex")[0].value = document.getElementsByTagName("option")[0].value;

            <%
                if(request.getAttribute("response1").equals("NoN") ) {
            %>
                 document.forms[0].submit();
            <%
                }
            %>
        }
        function changeActionForm() {
            document.forms[0].action = "RetrieveRealCustomerServlet";
            document.forms[0].submit();

        }
        function passToSaveLoanFile(){
            document.forms[0].action = "SaveLoanFileServlet";
            document.forms[0].submit();
        }
    </script>
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

        table {
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

        textarea {
            background-color: lightyellow;
        }
    </style>
</head>
<body onload="init()">
<h1>new "Loan File"</h1>
<%
    String firstName = "not Found";
    String lastName = "not Found";
    if ("NoN".equals((request.getAttribute("response"))) == false) {
        firstName = (String) request.getAttribute("firstName");
        lastName = (String) request.getAttribute("lastName");

    }
%>
<form method="post" action="GrantConditionsServlet">
    <h3>Create a new "Loan File"</h3>

    <div>
        <div class="one">
            <p class="one">
                Customer Number: <input style="width: 200px;height: 30px" name="customerId"/>

            </p>

            <p class="one">
                <input type="submit" style="width: 100px;height: 30px" onclick="changeActionForm()" value="submit!"/>

            </p>
        </div>
        <div class="one">
            <table id="table">
                <tr>
                    <th><p>First Name</p></th>
                    <th><p>Last Name</p></th>
                </tr>
                <tr>
                    <td name="firstName"><%=firstName%>
                    </td>
                    <td name="lastName"><%=lastName%>
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <div class="one">
        <p class="one">
            Loan Type: <select name="select" onchange="getIndex()">
           <%
             loanTypeVoArrayList = (ArrayList<LoanTypeVo>) request.getAttribute("LoanTypeVOList");
             for (LoanTypeVo loanTypeVo : loanTypeVoArrayList) {
            %>

            <option value="<%=loanTypeVo.getLoanTypeId()%>"
                    name="option"><%=loanTypeVo.getLoanTypeName()%>
            </option>
            <%
                }
            %>
        </select>
        </p>
        <p class="one">
            Contact Duration: <input style="width: 200px;height: 30px" name="contractDuration"/>
        </p>

        <p class="one">
            Contract Value: <input style="width: 200px;height: 30px" name="contractValue"/>
        </p>

        <p class="one">
            <input type="button" name="SaveLoanFileServlet" style="width: 100px;height: 30px" value="Select!"
                   onclick="passToSaveLoanFile()"/>
        </p>
        <input type="hidden" name="one"/>
        <input type="hidden" name="two"/>
        <input type="hidden" name="selectedIndex"/>
        <input type="hidden" name="selectedCustomerId" id="selectedCustomerId"/>
    </div>
</form>
</body>
</html>