package com.example.recruitment_assessment.servlets;

import com.example.recruitment_assessment.service.SequenceService;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

import static com.example.recruitment_assessment.other.MessagesStore.*;
import static java.lang.Long.parseLong;
import static java.util.Optional.ofNullable;

@WebServlet("/seq")
public class MovementServlet extends HttpServlet {

    private final SequenceService sequenceService;

    @Inject
    public MovementServlet(SequenceService sequenceService) {
        this.sequenceService = sequenceService;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String dir = ofNullable(req.getParameter("dir")).orElse("none");
        Long id = parseLong(ofNullable(req.getParameter("id")).orElse("0"));

        String[] result = performMovement(dir, id);
        req.getSession().setAttribute("result", result);
        resp.sendRedirect("/");
    }

    private String[] performMovement(String dir, Long id) {
        String[] message = null;
        switch(dir) {
            case "up":
                message = doUp(id);
                break;
            case "down":
                message = doDown(id);
                break;
            case "top":
                message = doTop(id);
                break;
            case "bottom":
                message = doBottom(id);
                break;
            default:
                message = new String[]{FAIL.getMessage(), UNKNOWN_ERROR.getMessage()};
                break;
        }
        return message;
    }

    private String[] doUp(Long id) {
        if (sequenceService.up(id)) {
            return new String[]{SUCCESS.getMessage(), UP_SUCCESS.getMessage()};
        } else {
            return new String[]{FAIL.getMessage(), UP_FAIL.getMessage()};
        }
    }

    private String[] doDown(Long id) {
        if (sequenceService.down(id)) {
            return new String[]{SUCCESS.getMessage(), DOWN_SUCCESS.getMessage()};
        } else {
            return new String[]{FAIL.getMessage(), DOWN_FAIL.getMessage()};
        }
    }

    private String[] doTop(Long id) {
        if (sequenceService.top(id)) {
            return new String[]{SUCCESS.getMessage(), UP_SUCCESS.getMessage()};
        } else {
            return new String[]{FAIL.getMessage(), UP_FAIL.getMessage()};
        }
    }

    private String[] doBottom(Long id) {
        if (sequenceService.bottom(id)) {
            return new String[]{SUCCESS.getMessage(), DOWN_SUCCESS.getMessage()};
        } else {
            return new String[]{FAIL.getMessage(), DOWN_FAIL.getMessage()};
        }
    }
}
