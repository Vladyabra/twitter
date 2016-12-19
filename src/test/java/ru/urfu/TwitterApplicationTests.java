package ru.urfu;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.urfu.model.Message;
import ru.urfu.model.User;
import ru.urfu.storage.MessageStorage;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TwitterApplicationTests {

    @Autowired
    private MessageStorage storage;

    @Test
    public void checkAutowiring() {
        assertNotNull(storage);
    }

    @Test
    public void inMemoryMessageAdd() {
        User someUser = new User("tester", "ololo@naumen.ru", "123456");
        Message unsaved = new Message(someUser, new Date(), "Hi there, guys!");
        assertNull(unsaved.getId());
        Message saved = storage.addMessage(unsaved);
        assertNotNull(saved.getId());
        assertTrue(storage.getMessages().contains(saved));
    }
}
