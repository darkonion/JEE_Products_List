package com.example.recruitment_assessment.servlets;

import com.example.recruitment_assessment.service.ProductService;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.recruitment_assessment.other.MessagesStore.DELETE_CONF;
import static com.example.recruitment_assessment.other.MessagesStore.SUCCESS;
import static java.lang.Long.parseLong;
import static java.util.Optional.ofNullable;

@WebServlet("/delete/*")
public class DeleteServlet extends HttpServlet {

    private final ProductService productService;

    @Inject
    public DeleteServlet(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = parseLong(ofNullable(req.getPathInfo()).orElse("delete/0").split("/")[1]);
        productService.deleteProductById(id);
        req.getSession().setAttribute("result", new String[]{SUCCESS.getMessage(), DELETE_CONF.getMessage()});
        resp.sendRedirect("/");
    }
}
