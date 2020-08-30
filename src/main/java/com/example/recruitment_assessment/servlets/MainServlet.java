package com.example.recruitment_assessment.servlets;

import com.example.recruitment_assessment.service.ProductService;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Objects.nonNull;

@WebServlet("")
public class MainServlet extends HttpServlet {

    private final ProductService productService;

    @Inject
    public MainServlet(ProductService productService) {
        this.productService = productService;
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        checkMessages(request);
        request.setAttribute("products", productService.getAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher("templates/main.jsp");
        dispatcher.forward(request, response);
    }

    private void checkMessages(HttpServletRequest request) {
        String[] result = (String[]) request.getSession().getAttribute("result");
        if (nonNull(result)) {
            request.setAttribute(result[0], result[1]);
            request.getSession().removeAttribute("result");
        }
    }
}
