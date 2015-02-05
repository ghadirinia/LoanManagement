package presentation;

import domain.LoanTypeVo;
import domain.LoanType;
import business.CRUD.LoanTypeCRUD;

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
public class GrantConditionsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/index.jsp";
        ArrayList<LoanType> loanTypeArrayList = new ArrayList<LoanType>();
        ArrayList<LoanTypeVo> loanTypeVoList = new ArrayList<LoanTypeVo>();
        loanTypeArrayList = LoanTypeCRUD.retrieve();
        String responseString1 = "";
        LoanTypeVo loanTypeVo;
        if(loanTypeArrayList.size()>0){
            for (LoanType loanType:loanTypeArrayList){
                loanTypeVo = new LoanTypeVo();
                loanTypeVo.setLoanTypeId(""+loanType.getId());
                loanTypeVo.setLoanTypeName(loanType.getLoanName());
                loanTypeVoList.add(loanTypeVo);
            }

            request.setAttribute("LoanTypeVOList",loanTypeVoList);

            url = "/createLoanFile.jsp";
        }
        else{
            responseString1 = "NoN";

        }
            request.setAttribute("response1", responseString1);
            ServletContext servletContext = getServletContext();
            RequestDispatcher rd = servletContext.getRequestDispatcher(url);
            rd.forward(request, response);

    }
}
