package ru.urfu.storage;

import ru.urfu.model.Message;

import java.util.List;
import java.util.Optional;

public interface MessageStorage {

    Message addMessage(Message message);

    List<Message> getMessages();

    List<Message> getMessages(int count, long maxID);

    void removeById(long id);

    Optional<Message> getMessageById(long id);
}
