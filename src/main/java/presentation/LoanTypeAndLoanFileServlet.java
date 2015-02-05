package presentation;

import business.CRUD.LoanFileCRUD;
import business.CRUD.LoanTypeCRUD;
import business.TypeChecker;
import domain.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dotin school 2 on 1/21/2015.
 */
public class LoanTypeAndLoanFileServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().endsWith("SaveLoanTypeServlet")) {
            String loanTypeName = request.getParameter("hidden");
            String interestRate = request.getParameter("two");
            String[] grantConditions = request.getParameter("one").split("_");
            String[] grantConditionsAttributes;
            LoanType loanType = new LoanType();
            Set<GrantCondition> grantConditionSet = new HashSet<GrantCondition>();
            for (String grantCondition : grantConditions) {

                grantConditionsAttributes = grantCondition.split(" ");

                loanType.setLoanName(loanTypeName);
                loanType.setInterestRate(Integer.parseInt(interestRate));

                GrantCondition grantConditionsObject;


                String grantName = grantConditionsAttributes[0];
                BigDecimal minimumDuration = new BigDecimal(grantConditionsAttributes[1]);
                BigDecimal maximumDuration = new BigDecimal(grantConditionsAttributes[2]);
                BigDecimal minimumAmount = new BigDecimal(grantConditionsAttributes[3]);
                BigDecimal maximumAmount = new BigDecimal(grantConditionsAttributes[4]);

                grantConditionsObject = new GrantCondition(grantName, minimumDuration, maximumDuration, minimumAmount, maximumAmount);
                grantConditionsObject.setLoanType(loanType);

                grantConditionSet.add(grantConditionsObject);
            }

            loanType.setGrantConditions(grantConditionSet);

            LoanTypeCRUD.save(loanType, (HashSet<GrantCondition>) grantConditionSet);

            PrintWriter writer = response.getWriter();
            String html = "";
            html = "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head lang=\"en\">\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Registration</title>\n" +
                    "    <style>\n" +
                    "        body {background-color:midnightblue}\n" +
                    "        h1   {color:yellow}\n" +
                    "        h4   {color:yellow}\n" +
                    "        button    {color:black ; background-color: lightyellow}\n" +
                    "        h3 {color: yellow}\n" +
                    "        div.one { border:2px solid ;border-color: yellow ;margin:20px; padding:20px; width: 93%}\n" +
                    "        p.one {color: yellow ; align-content: center}\n" +
                    "        textarea {background-color: lightyellow ; }\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "\n" +
                    "<h1>Registration</h1>\n" +
                    "<form method=\"post\" action=\"index.jsp\">\n" +
                    "<div class=\"one\">\n" +
                    "    <h3>Registration Loan Type</h3>\n" +
                    "<h4 color=\"yellow\">The loan type and its grand conditions have saved to database.</h4>" +
                    "        <p class=\"one\">\n" +
                    "            <button  cols=\"50\" rows=\"1\" onclick=\"location.href='index.jsp'\" >Back to home!</button>\n" +
                    "        </p>\n" +
                    "     </div>\n" +
                    "    </p>\n" +
                    "</div>\n" +
                    "</form>\n" +
                    "</body>\n" +
                    "</html>";
            writer.println(html);
        } else if (request.getRequestURI().endsWith("SaveLoanFileServlet")) {
            String contractDuration = request.getParameter("contractDuration");
            String contractValue = request.getParameter("contractValue");
            String loanTypeId = request.getParameter("selectedIndex");
            String customerId = request.getParameter("selectedCustomerId");
            String customerFirstName = request.getParameter("firstName");
            String customerLastName = request.getParameter("lastName");

            String htmlRespons = "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head lang=\"en\">\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Registration</title>\n" +
                    "    <style>\n" +
                    "        body {background-color:midnightblue}\n" +
                    "        h1   {color:yellow}\n" +
                    "        h4   {color:yellow}\n" +
                    "        button    {color:black ; background-color: lightyellow}\n" +
                    "        h3 {color: yellow}\n" +
                    "        div.one { border:2px solid ;border-color: yellow ;margin:20px; padding:20px; width: 93%}\n" +
                    "        p.one {color: yellow ; align-content: center}\n" +
                    "        textarea {background-color: lightyellow ; }\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "\n" +
                    "<h1>Registration Loan File</h1>\n" +
                    "<form method=\"post\" action=\"index.jsp\">\n" +
                    "<div class=\"one\">\n";
            TypeChecker typeChecker = new TypeChecker();
            if (typeChecker.checkInValueAndDuration(contractValue, loanTypeId, contractDuration)) {

                LoanFile loanFile = new LoanFile();
                loanFile.setGrantConditionId(typeChecker.getGrantId());
                loanFile.setLoanTypeId(Integer.parseInt(loanTypeId));
                loanFile.setContractDuration(Integer.parseInt(contractDuration));
                loanFile.setContractValue(Integer.parseInt(contractValue));
                loanFile.setCustomerId(Integer.parseInt(customerId));
                LoanFileCRUD.save(loanFile);
                htmlRespons += "<h4>Saved to database</h4>";
            } else {
                htmlRespons += "<h4 >This conditions of Loan File is incorrect.Value or Duration of contract are not in valid range.</h4>";
            }
            htmlRespons += "</div>\n" +
                    "</form>\n" +
                    "</body>\n" +
                    "</html>";
            PrintWriter writer = response.getWriter();
            writer.println(htmlRespons);
        }
    }
}
