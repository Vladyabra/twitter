package ru.urfu.storage;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.urfu.model.Message;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Primary
@Component
public class JpaMessageStorage implements MessageStorage {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Message addMessage(Message message) {
        em.persist(message);
        em.flush(); // actually set's id

        return message;
    }

    @Override
    public List<Message> getMessages() {
        return em.createQuery("from " + Message.class.getName(), Message.class)
                .getResultList();
    }

    @Override
    public List<Message> getMessages(int count, long maxID) {
        return em.createQuery("from " + Message.class.getName() + " as m where m.id <= :maxID order by m.date desc", Message.class)
                .setParameter("maxID", maxID)
                .setMaxResults(count)
                .getResultList();
    }

    @Override
    @Transactional
    public void removeById(long id) {
        Message message = em.find(Message.class, id);
        if (message != null) {
            em.remove(message);
        }
    }

    @Override
    public Optional<Message> getMessageById(long id) {
        return Optional.ofNullable(em.find(Message.class, id));
    }
}
