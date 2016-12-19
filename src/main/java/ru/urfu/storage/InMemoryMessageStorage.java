package ru.urfu.storage;

import org.springframework.stereotype.Component;
import ru.urfu.model.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Component
public class InMemoryMessageStorage implements MessageStorage {

    private final AtomicLong curId = new AtomicLong(0);
    private final List<Message> messages = new Vector<>();

    @Override
    public List<Message> getMessages(int count, long maxID) {
        return messages.stream()
                .sorted((o1, o2) -> 0 - Long.compare(o1.getId(), o2.getId()))
                .filter(message -> message.getId() <= maxID)
                .limit(count)
                .collect(Collectors.toList());
    }

    @Override
    public Message addMessage(Message message) {
        message.setId(curId.getAndIncrement());
        messages.add(message);

        return message;
    }

    @Override
    public List<Message> getMessages() {
        return new ArrayList<>(messages);
    }

    @Override
    public Optional<Message> getMessageById(long id) {
        return messages.parallelStream()
                .filter(message -> message.getId() == id)
                .findAny();
    }

    @Override
    public void removeById(long id) {
        for (int i = 0; i < messages.size(); i++) {
            if (messages.get(i).getId() == id) {
                synchronized (messages) {
                    if (messages.get(i).getId() == id) {
                        messages.remove(i);
                    }
                }
                return;
            }
        }
    }
}
