package ru.job4j.concurrent.jcip;

import java.util.HashMap;
import java.util.Map;

public class UserStore {
    private Map<Integer, User> userStorage = new HashMap<Integer, User>();

    public synchronized boolean add(User user) {
        boolean result = false;
        if (user != null) {
            result = userStorage.putIfAbsent(user.getId(), user) != null;
        }
        return result;
    }

    public synchronized boolean update(User user) {
        return userStorage.replace(user.getId(), user) != null;
    }

    public synchronized boolean delete(User user) {
        return userStorage.remove(user.getId(), user);
    }

    public synchronized void transfer(int fromId, int toId, int amount) {
        User userFrom = userStorage.get(fromId);
        User userTo = userStorage.get(toId);
        if (userFrom != null
          && userTo != null
          && userFrom.getAmount() >= amount) {
            userFrom.setAmount(userFrom.getAmount() - amount);
            userTo.setAmount(userTo.getAmount() - amount);
        }
    }
}
