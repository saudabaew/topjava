package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Model;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

import static org.slf4j.LoggerFactory.getLogger;

public class AddMealServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);
    private static volatile int count = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("redirect to get-method ADD servlet");

        req.getRequestDispatcher("/addMeal.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        log.debug("redirect to post-method ADD servlet");

        String description = req.getParameter("description");
        int calories = Integer.parseInt(req.getParameter("calories"));

        SimpleDateFormat availDate = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm",
                Locale.getDefault());
        String[] availableDate = req.getParameterValues("date");
        Date date = null;
        try {
            date = availDate.parse(availableDate[0]);
            Instant instant = date.toInstant();
            LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
            Meal meal = new Meal(localDateTime, description, calories, count++);
            Model model = Model.getInstance();
            model.add(meal);
            resp.sendRedirect(req.getContextPath() + "/meals");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
