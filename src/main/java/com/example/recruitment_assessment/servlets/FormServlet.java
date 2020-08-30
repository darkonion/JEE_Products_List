package com.example.recruitment_assessment.servlets;

import com.example.recruitment_assessment.entity.Product;
import com.example.recruitment_assessment.service.ProductService;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static com.example.recruitment_assessment.other.MessagesStore.FAIL;
import static com.example.recruitment_assessment.other.MessagesStore.SUCCESS;

@WebServlet("/addForm")
public class FormServlet extends HttpServlet {

    private final ProductService productService;

    @Inject
    public FormServlet(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("product", Product.builder().build());
        RequestDispatcher dispatcher = req.getRequestDispatcher("templates/form.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Product product = Product.builder()
                .label(req.getParameter("label"))
                .details(req.getParameter("details"))
                .build();

        Optional<Product> productOpt = productService.addNewProduct(product);

        if (productOpt.isPresent()) {
            req.getSession()
                    .setAttribute("result", new String[]{SUCCESS.getMessage(), "Product o id: "
                                    + productOpt.get().getId() + " został pomyślnie dodany"});
        } else {
            req.getSession()
                    .setAttribute("result", new String[]{FAIL.getMessage(),
                                    "Coś poszło nie tak, produkt nie został dodany"});
        }
        resp.sendRedirect("/");
    }
}
