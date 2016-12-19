package ru.urfu.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.urfu.model.Message;
import ru.urfu.model.User;
import ru.urfu.storage.MessageStorage;
import ru.urfu.storage.UserStorage;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RestApiController {

    @Autowired
    private MessageStorage messages;

    @Autowired
    private UserStorage users;

    @RequestMapping(value = "/addMessage", method = RequestMethod.POST)
    public String addMessage(@RequestParam(defaultValue = "") String text) {
        if (1 <= text.length() && text.length() <= 140) {
            User curUser = users.findUser(((UserDetails) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal()).getUsername()).get();
            Message message = new Message(curUser, new Date(), text);
            messages.addMessage(message);
            return "ok";
        } else {
            return "error";
        }
    }

    @RequestMapping(value = "/getMessages", method = RequestMethod.POST)
    public List<Message> getMessages(
            @RequestParam(defaultValue = "20") int count,
            @RequestParam(defaultValue = "" + Long.MAX_VALUE) long maxID
    ) {
        return messages.getMessages(count, maxID);
    }

    @RequestMapping(value = "/removeMessage", method = RequestMethod.POST)
    public String removeMessage(@RequestParam(defaultValue = "-1") long id) {
        Optional<Message> message = messages.getMessageById(id);
        if (message.isPresent()) {
            User curUser = users.findUser(((UserDetails) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal()).getUsername()).get();
            if (message.get().getUser().equals(curUser)) {
                messages.removeById(id);
            }
        }
        return "ok";
    }

    @RequestMapping(value = "/getMyself", method = RequestMethod.POST)
    public User getMessages() {
        return users.findUser(((UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUsername()).get();
    }
}
