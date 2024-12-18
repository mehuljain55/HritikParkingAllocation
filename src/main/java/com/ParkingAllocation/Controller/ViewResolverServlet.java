package com.ParkingAllocation.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/view/*")
public class ViewResolverServlet extends HttpServlet {  // Extend HttpServlet
    private static final String PREFIX = "/WEB-INF/jsp/";
    private static final String SUFFIX = ".jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getRequestURI();
        String viewName = path.substring(path.lastIndexOf("/") + 1);  // Extracts the view name after "/view/"
        String fullViewPath = PREFIX + viewName + SUFFIX;
        request.getRequestDispatcher(fullViewPath).forward(request, response);
    }
}
