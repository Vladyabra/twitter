package ru.urfu.storage;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.urfu.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Primary
@Component
public class JpaUserStorage implements UserStorage {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public User addUser(User user) {
        em.persist(user);
        em.flush();

        return user;
    }

    @Override
    public Optional<User> findUser(String username) {
        List<User> user = em.createQuery("from " + User.class.getName() + " as u where u.username = :username", User.class)
                .setParameter("username", username)
                .setMaxResults(1)
                .getResultList();

        if (user.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(user.get(0));
        }
    }
}
