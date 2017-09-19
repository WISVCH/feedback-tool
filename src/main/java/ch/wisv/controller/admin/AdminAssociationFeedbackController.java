package ch.wisv.controller.admin;

import ch.wisv.service.AssociationFeedbackService;
import ch.wisv.domain.feedback.AssociationFeedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller for admin association feedback.
 *
 * Created by Tom on 30/04/2017.
 */
@Controller
@RequestMapping("/admin/association")
public class AdminAssociationFeedbackController {
    /** JPA service for association feedback. */
    private AssociationFeedbackService associationFeedbackService;

    /**
     * Autowired constructor.
     */
    @Autowired
    public AdminAssociationFeedbackController(AssociationFeedbackService associationFeedbackService) {
        this.associationFeedbackService = associationFeedbackService;
    }

    /**
     * List inbox association feedback items.
     */
    @RequestMapping("/inbox")
    public String listInbox(Model model) {
        model.addAttribute("posts", associationFeedbackService.listInbox());
        return "admin/association/listInbox";
    }

    /**
     * List archive association feedback items.
     */
    @RequestMapping("/archive")
    public String listArchive(Model model) {
        model.addAttribute("posts", associationFeedbackService.listArchive());
        return "admin/association/listArchive";
    }

    /**
     * View single association feedback item by it's ID.
     */
    @RequestMapping("/{id}")
    public String getAssociationFeedback(@PathVariable long id, Model model) {
        model.addAttribute("feedback", associationFeedbackService.get(id));
        return "admin/association/view";
    }

    /**
     * Change handled state of an association feedback item by it's ID.
     */
    @RequestMapping("handle/{id}")
    public String handle(@PathVariable long id, Model model, HttpServletRequest request) {
        AssociationFeedback associationFeedback = associationFeedbackService.get(id);
        boolean handle = !associationFeedback.isHandled();
        associationFeedback.setHandled(handle);
        associationFeedbackService.save(associationFeedback);

        model.addAttribute("posts", associationFeedbackService.listInbox());
        return "redirect:" + request.getHeader("Referer");
    }
}
