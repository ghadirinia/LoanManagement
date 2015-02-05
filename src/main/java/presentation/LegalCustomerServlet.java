package presentation;

import business.CustomerChecker;
import business.ParentException;
import business.TypeChecker;
import domain.LegalCustomer;
import business.CRUD.LegalCustomerCRUD;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dotin school 2 on 1/17/2015.
 */
public class LegalCustomerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        if(request.getRequestURI().endsWith("RegistrationLegalCustomer")) {
            String name = request.getParameter("name");
            String registerDate = request.getParameter("registerDate");
            String economicId = request.getParameter("economicId");
            TypeChecker typeChecker = new TypeChecker();
            //boolean checkFormat = false;
            //boolean checkDuplicate = false;
            CustomerChecker customerChecker = new CustomerChecker();
            try {
                customerChecker.checkLegalCustomer(registerDate, economicId);
                //checkFormat = customerChecker.check;
                //checkDuplicate = customerChecker.checkDuplicate;
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
                    "        textarea {background-color: lightyellow ; }\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "\n" +
                    "<h1>Registration</h1>\n" +
                    "<form method=\"post\" >\n" +
                    "<div class=\"one\">\n" +
                    "    <h3>Registration a \"Legal customer\"</h3>";
            if (customerChecker.check)
                if (!customerChecker.checkDuplicate) {
                    LegalCustomer legalCustomer = new LegalCustomer(registerDate, name, Integer.parseInt(economicId));
                    LegalCustomerCRUD.save(legalCustomer);
                    // ArrayList<LegalCustomer> legalCustomers = LegalCustomerCRUD.retrieve(economicId,"","");
                    html += "  <p color='yellow'>Legal customer with customer Id equals \"" + legalCustomer.getCustomerId() + "\" has registered.</p>";
                } else {
                    html += "<p color='yellow'>This economic code is duplicate:" + economicId + "Please inter a correct customer Id</p>";
                }
            else {
                html += "<p color='yellow'>Input Format of Register date or economic code is incorrect.</p>";
            }
            html += "</div>\n" +
                    "</form>\n" +
                    "</body>\n" +
                    "</html>";
            writer.println(html);
        }else if(request.getRequestURI().endsWith("SearchHandlerLegalCustomer")){
            String name = request.getParameter("name");
            String customerId = request.getParameter("customerId");
            String economicId = request.getParameter("economicId");
            List<LegalCustomer> legalCustomersList= new ArrayList<LegalCustomer>();

            PrintWriter writer = response.getWriter();
            ResultSet rs ;
            legalCustomersList = LegalCustomerCRUD.retrieve(economicId,customerId,name);//.searchLegalCustomer(name, Integer.parseInt(economicId), Integer.parseInt(customerId));
            String nameExtract;
            int economicIdExtract;
            String registerDateExtract;
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

            if(legalCustomersList.size()>0){
                htmlResponse +="<table style=\"width:90%\">";
                htmlResponse +="<caption>Legal Customer</caption>";
                htmlResponse +="<tr>";
                htmlResponse +="<th>Name</th>";
                htmlResponse +="<th>Economic Code</th>";
                htmlResponse +="<th>Register Date</th>";
                htmlResponse +="<th>Customer Number</th>";
                htmlResponse +="<th>Delete</th>";
                htmlResponse +="<th>Modification</th>";
                htmlResponse +="</tr>";
                try {
                    for(int i = 0 ; i < legalCustomersList.size();i++){
                        //Retrieve by column name
                        nameExtract  = legalCustomersList.get(i).getName();
                        economicIdExtract = legalCustomersList.get(i).getEconomicId();
                        registerDateExtract = legalCustomersList.get(i).getRegisterDate();
                        customerIdExtract = legalCustomersList.get(i).getCustomerId();

                        htmlResponse +="<tr name=\"i\">";
                        htmlResponse +="<th>" + nameExtract + "</th>";
                        htmlResponse +="<th>" + economicIdExtract + "</th>";
                        htmlResponse +="<th>" + registerDateExtract + "</th>";
                        htmlResponse +="<th>" + customerIdExtract + "</th>";
                        htmlResponse +="<th><form method=\"post\" action=\"DeleteLegalCustomer\"><button>Delete</button><input type=\"hidden\" name=\"economicId\" value=\""+ economicIdExtract +"\"/></form></th>";
                        htmlResponse +="<th><form method=\"post\"action=\"ModificationLegalCustomer.jsp\" ><button>Modification</button><input type=\"hidden\" name=\"economicId\" value=\""+ economicIdExtract +"\"/></form></th>";
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
        } else if(request.getRequestURI().endsWith("DeleteLegalCustomer")){
            String economicId = request.getParameter("economicId");

            PrintWriter writer = response.getWriter();
            try{
                LegalCustomerCRUD.remove(economicId);
                //dataBaseDropper.deleteRecordFromLegalCustomer(economicId);
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
            htmlResponse +="<p><h3>This customer with economicId "+economicId+" has been deleted.</h3></p>";
            htmlResponse +="</div>";
            htmlResponse +="</body>";
            htmlResponse +="</html>";
            writer.println(htmlResponse);
        }else if(request.getRequestURI().endsWith("ModificationLegalCustomer")){
            String economicId = request.getParameter("economicId");
            String name = request.getParameter("name");
            String registerDate = request.getParameter("registerDate");
            String customerId = request.getParameter("customerId");
            LegalCustomerCRUD.modify(economicId,customerId,name,registerDate);
            PrintWriter writer = response.getWriter();
            String htmlResponse = "";
            htmlResponse += "<!DOCTYPE html>" +
                    "<html>" +
                    "<head lang=\"en\">" +
                    "    <meta charset=\"UTF-8\">" +
                    "    <title>Registration</title>" +
                    "    <style>" +
                    "        body {background-color:midnightblue}" +
                    "        h1   {color:yellow}" +
                    "        button    {color:black ; background-color: lightyellow}" +
                    "        h3 {color: yellow}" +
                    "        div.one { border:2px solid ;border-color: yellow ;margin:20px; padding:20px; width: 93%}" +
                    "        p.one {color: yellow ; align-content: center}" +
                    "        textarea {background-color: lightyellow ; }" +
                    "    </style>" +
                    "</head>";
            htmlResponse += "" +
                    "<body>" +
                    "<h1>Modification</h1>" +
                    "<form>" +
                    "    <div class=\"one\">" +
                    "        <p>" +
                    "        <div class=\"two\">" +
                    "            <p class=\"one\">" +
                    "The Legal customer's informations has modified"+
                    "            </p>" +

                    "            </p>" +
                    "        </div>" +
                    "        </p>" +
                    "    </div>" +
                    "</form>" +
                    "</body>" +
                    "</html>";
            writer.println(htmlResponse);
        }
    }
}
