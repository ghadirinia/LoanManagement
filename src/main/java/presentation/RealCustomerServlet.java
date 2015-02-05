package presentation;

import business.CRUD.LoanTypeCRUD;
import business.CRUD.RealCustomerCRUD;
import business.CustomerChecker;
import business.ParentException;
import business.TypeChecker;
import domain.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dotin school 2 on 1/21/2015.
 */
public class RealCustomerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().endsWith("RetrieveRealCustomerServlet")) {
            String customerId = request.getParameter("customerId");
            request.setAttribute("customerCode", customerId);
            ArrayList<LoanType> loanTypeArrayList;
            ArrayList<LoanTypeVo> loanTypeVoList = new ArrayList<LoanTypeVo>();
            LoanTypeVo loanTypeVo;
            loanTypeArrayList = LoanTypeCRUD.retrieve();
            String responseString1 = "";
            if (loanTypeArrayList.size() > 0) {
                for (LoanType loanType : loanTypeArrayList) {
                    loanTypeVo = new LoanTypeVo();
                    loanTypeVo.setLoanTypeId("" + loanType.getId());
                    loanTypeVo.setLoanTypeName(loanType.getLoanName());
                    loanTypeVoList.add(loanTypeVo);
                }

                request.setAttribute("LoanTypeVOList", loanTypeVoList);
            } else {
                responseString1 = "NoN";
            }
            request.setAttribute("response1", responseString1);
            String responseString = "";
            ArrayList<RealCustomer> realCustomerArrayList = RealCustomerCRUD.retrieve("", customerId, "", "");

            if (realCustomerArrayList.size() > 0) {
                for (int i = 0; i < realCustomerArrayList.size(); i++) {
                    responseString += realCustomerArrayList.get(i).getFirst() + " " + realCustomerArrayList.get(i).getLast() + "_";

                    String firstName = realCustomerArrayList.get(0).getFirst();
                    String lastName = realCustomerArrayList.get(0).getLast();
                    request.setAttribute("firstName", firstName);
                    request.setAttribute("lastName", lastName);
                }
            } else {
                responseString = "NoN";
            }
            request.setAttribute("response", responseString);
            String url = "/createLoanFile.jsp";
            ServletContext servletContext = getServletContext();
            RequestDispatcher rd = servletContext.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if (request.getRequestURI().endsWith("RegistrationRealCustomerServlet")) {
            String firstName = request.getParameter("first");
            String lastName = request.getParameter("last");
            String fatherName = request.getParameter("fatherName");
            String birthDate = request.getParameter("birthDate");
            String nationalId = request.getParameter("nationalId");
            TypeChecker typeChecker = new TypeChecker();
            CustomerChecker customerChecker = new CustomerChecker();
            try {
                customerChecker.checkRealCustomer(birthDate, nationalId);
            } catch (ParentException e) {
                e.printStackTrace();
            }
            PrintWriter writer = response.getWriter();
            String html = "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head lang=\"en\">\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Registration</title>\n" +
                    "    <style>\n" +
                    "        body {background-color:midnightblue}\n" +
                    "        h1   {color:yellow}\n" +
                    "        button    {color:black ; background-color: lightyellow}\n" +
                    "        h3 {color: yellow}\n" +
                    "        div.one { border:2px solid ;border-color: yellow ;margin:20px; padding:20px; width: 93%}\n" +
                    "        p.one {color: yellow ; align-content: center}\n" +
                    "        p.yellow {color: yellow ; align-content: center}\n" +
                    "        textarea {background-color: lightyellow ; }\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "\n" +
                    "<h1>Registration</h1>\n" +
                    "<form method=\"post\" >\n" +
                    "<div class=\"one\">\n" +
                    "    <h3>Registration a \"Real customer\"</h3>";
            if (customerChecker.check)
                if (!customerChecker.checkDuplicate) {
                    if (customerChecker.validNationalId) {
                        RealCustomer realCustomer = new RealCustomer(Integer.parseInt(nationalId), birthDate, fatherName, lastName, firstName);
                        RealCustomerCRUD.save(realCustomer);
                        html += "  <p id ='yellow'>Real customer with customer Id equals \"" + realCustomer.getCustomerId() + "\" has registered.</p>";
                    } else {
                        html += "<p id='yellow'>The nationalId is invalid.</p>";
                    }
                } else {
                    html += "<p id='yellow'>This nationalId code is duplicate: " + nationalId + " Please inter a correct national Id</p>";
                }
            else {
                html += "<p id='yellow'>Input Format of Register date or economic code is incorrect.</p>";
            }
            html += "</div>\n" +
                    "</form>\n" +
                    "</body>\n" +
                    "</html>";
            writer.println(html);
        }else if(request.getRequestURI().endsWith("SearchHandlerRealCustomer")){
            String firstName = request.getParameter("first");
            String lastName = request.getParameter("last");
            String fatherName = request.getParameter("fatherName");
            String customerId = request.getParameter("customerId");
            String nationalId = request.getParameter("nationalId");
            List<RealCustomer> realCustomersList = new ArrayList<RealCustomer>();


            // do some processing here...
            // checking input validation type in logic layer
            //make response.

            // get response writer
            PrintWriter writer = response.getWriter();
            //String nationalId,String customerId, String first,String last)
            realCustomersList = RealCustomerCRUD.retrieve(nationalId,customerId,firstName, lastName);
            String firstExtract;
            String lastExtract;
            String fatherNameExtract;
            int nationalIdExtract;
            String birthDateExtract;
            long customerIdExtract;
            String htmlResponse = "<!DOCTYPE html>";
            htmlResponse +="<html>";
            htmlResponse +="<head lang=\"en\">";
            htmlResponse +="<meta charset=\"UTF-8\">";
            htmlResponse +="<title></title>";
            htmlResponse +="<style>";
            htmlResponse +="table, th, td { border: 1px solid ; border-collapse: collapse; border-color: yellow;}";
            htmlResponse +="th, td { padding: 5px; color : yellow}";
            htmlResponse +="caption {color:yellow}";
            htmlResponse +="th { text-align: left; }";
            htmlResponse +="body {background-color:midnightblue}";
            htmlResponse +="div { border:2px solid ;border-color: yellow ;margin:20px; padding:20px; width: 93%}";
            htmlResponse += "h3 {color: yellow}";
            htmlResponse +="</style>";
            htmlResponse +="</head>";
            htmlResponse +="<body>";
            htmlResponse +="<div>";
            if(realCustomersList.size()>0){
                //realCustomersList = dataBaseExplorer.realCustomersList;
                htmlResponse +="<table style=\"width:90%\">";
                htmlResponse +="<caption color=yellow>Real Customer</caption>";
                htmlResponse +="<tr>";
                htmlResponse +="<th>firstName</th>";
                htmlResponse +="<th>lastName</th>";
                htmlResponse +="<th>fatherName</th>";
                htmlResponse +="<th>birth Date</th>";
                htmlResponse +="<th>National Code</th>";
                htmlResponse +="<th>Customer Number</th>";
                htmlResponse +="<th>Delete</th>";
                htmlResponse +="<th>Modification</th>";
                htmlResponse +="</tr>";
                try {
                    for(int i = 0 ; i < realCustomersList.size(); i++){
                        //Retrieve by column name
                        firstExtract  = realCustomersList.get(i).getFirst();
                        lastExtract  = realCustomersList.get(i).getLast();
                        fatherNameExtract  = realCustomersList.get(i).getFatherName();
                        birthDateExtract = realCustomersList.get(i).getBirthDate();
                        nationalIdExtract = realCustomersList.get(i).getNationalId();
                        customerIdExtract = realCustomersList.get(i).getCustomerId();

                        htmlResponse +="<tr>";
                        htmlResponse +="<th>" + firstExtract + "</th>";
                        htmlResponse +="<th>" + lastExtract+ "</th>";
                        htmlResponse +="<th>" + fatherNameExtract + "</th>";
                        htmlResponse +="<th>" + birthDateExtract + "</th>";
                        htmlResponse +="<th >" + nationalIdExtract + "</th>";
                        htmlResponse +="<th>" + customerIdExtract + "</th>";
                        htmlResponse +="<th><form method=\"post\" action=\"DeleteRealCustomer\"><button>Delete</button><input type=\"hidden\" name=\"nationalId\" value=\""+ nationalIdExtract +"\"/></form></th>";
                        htmlResponse +="<th><form method=\"post\" action=\"ModificationRealCustomer.jsp\" ><input type=\"hidden\" name=\"nationalId\" value=\""+ nationalIdExtract +"\"/><button>Modification</button></form></th>";
                        htmlResponse +="</tr>";

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            else
            {
                htmlResponse +="<p><h3>There is no such a customer</h3></p>";
            }
            htmlResponse +="</table>";
            htmlResponse +="</div>";
            htmlResponse +="</body>";
            htmlResponse +="</html>";
            writer.println(htmlResponse);

        }else if(request.getRequestURI().endsWith("DeleteRealCustomer")){
            String nationalId = request.getParameter("nationalId");
            PrintWriter writer = response.getWriter();
            try{
                RealCustomerCRUD.remove(nationalId);
//                dataBaseDropper.deleteRecordFromRealCustomer(nationalId);
            }catch(Exception e){
                e.printStackTrace();
            }
            String htmlResponse = "<!DOCTYPE html>";
            htmlResponse +="<html>";
            htmlResponse +="<head lang=\"en\">";
            htmlResponse +="<meta charset=\"UTF-8\">";
            htmlResponse +="<title></title>";
            htmlResponse +="<style>";
            htmlResponse +="table, th, td { border: 1px solid ; border-collapse: collapse; border-color: yellow;}";
            htmlResponse +="th, td { padding: 5px; color : yellow}";
            htmlResponse +="caption {color:yellow}";
            htmlResponse +="th { text-align: left; }";
            htmlResponse +="body {background-color:midnightblue}";
            htmlResponse +="div { border:2px solid ;border-color: yellow ;margin:20px; padding:20px; width: 93%}";
            htmlResponse += "h3 {color: yellow}";
            htmlResponse +="</style>";
            htmlResponse +="</head>";
            htmlResponse +="<body>";
            htmlResponse +="<div>";
            htmlResponse +="<p><h3>This customer with nationalId "+nationalId+" deleted has been deleted.</h3></p>";
            htmlResponse +="</div>";
            htmlResponse +="</body>";
            htmlResponse +="</html>";
            writer.println(htmlResponse);
        }else if(request.getRequestURI().endsWith("ModificationRealCustomer")){
            String nationalId = request.getParameter("nationalId");
            String first = request.getParameter("first");
            String last = request.getParameter("last");
            String fatherName = request.getParameter("fatherName");
            String birthDate = request.getParameter("birthDate");
            String customerId = request.getParameter("customerId");
            //String nationalId,String customerId, String first, String last,String fatherName,String birthDate
            RealCustomerCRUD.modify(nationalId,customerId,first,last,fatherName,birthDate);
            PrintWriter writer = response.getWriter();
            String htmlResponse = "";
            htmlResponse += "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head lang=\"en\">\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Registration</title>\n" +
                    "    <style>\n" +
                    "        body {background-color:midnightblue}\n" +
                    "        h1   {color:yellow}\n" +
                    "        button    {color:black ; background-color: lightyellow}\n" +
                    "        h3 {color: yellow}\n" +
                    "        div.one { border:2px solid ;border-color: yellow ;margin:20px; padding:20px; width: 93%}\n" +
                    "        p.one {color: yellow ; align-content: center}\n" +
                    "        textarea {background-color: lightyellow ; }\n" +
                    "    </style>\n" +
                    "</head>";
            htmlResponse += "\n" +
                    "<body>\n" +
                    "<h1>Modification</h1>\n" +
                    "<form>\n" +
                    "    <div class=\"one\">\n" +
                    "        <p>\n" +
                    "        <div class=\"two\">\n" +
                    "            <p class=\"one\">\n" +
                    "The Real customer's informations has modified"+
                    "            </p>\n" +

                    "            </p>\n" +
                    "        </div>\n" +
                    "        </p>\n" +
                    "    </div>\n" +
                    "</form>\n" +
                    "</body>\n" +
                    "</html>";
            writer.println(htmlResponse);
        }
    }
}
