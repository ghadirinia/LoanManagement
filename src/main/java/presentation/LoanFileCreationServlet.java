package presentation;

import domain.LoanType;
import business.CRUD.LoanTypeCRUD;
import domain.LoanTypeVo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Dotin school 2 on 1/25/2015.
 */
public class LoanFileCreationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String responseString1 = "";
        String customerId = request.getParameter("customerId");
        request.setAttribute("customerId",customerId);
        ArrayList<LoanType> loanTypeArrayList = new ArrayList<LoanType>();
        ArrayList<LoanTypeVo> loanTypeVoList = new ArrayList<LoanTypeVo>();
        LoanTypeVo loanTypeVo = new LoanTypeVo();
        loanTypeArrayList = LoanTypeCRUD.retrieve();
        if(loanTypeArrayList.size()>0){
            for (LoanType loanType:loanTypeArrayList){
                loanTypeVo.setLoanTypeId(""+loanType.getId());
                loanTypeVo.setLoanTypeName(loanType.getLoanName());
                loanTypeVoList.add(loanTypeVo);
            }

            request.setAttribute("loanTypeLists",loanTypeVoList);
        }
        else{
            responseString1 = "NoN";
        }
        request.setAttribute("response1", responseString1);
        String url = "/createLoanFile.jsp";
        ServletContext servletContext = getServletContext();
        RequestDispatcher rd = servletContext.getRequestDispatcher(url);
        rd.forward(request, response);
    }
}
