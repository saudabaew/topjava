package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Model;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by 1 on 05.03.2018.
 */
public class DeleteMealServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("redirect to post-method DELETE servlet");

        Integer id = Integer.parseInt(req.getParameter("id"));
        Model model = Model.getInstance();
        model.mealMap().remove(id);
        resp.sendRedirect(req.getContextPath() + "/meals"); // Refresh page with table.
    }
}
