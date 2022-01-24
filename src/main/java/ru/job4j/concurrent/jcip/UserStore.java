package ru.job4j.concurrent.jcip;

import java.util.HashMap;
import java.util.Map;

public class UserStore {
    private Map<Integer, User> userStorage = new HashMap<Integer, User>();

    public boolean add(User user) {
       return userStorage.putIfAbsent(user.getId(), user) != null;
    }

    public boolean update(User user) {
        return userStorage.replace(user.getId(), user) != null;
    }

    public boolean delete(User user) {
        return userStorage.remove(user.getId(), user);
    }

    public synchronized void transfer(int fromId, int toId, int amount) {
        if (userStorage.get(fromId) != null
          && userStorage.get(toId) != null
          && userStorage.get(fromId).getAmount() >= amount) {
            userStorage.get(fromId).setAmount(userStorage.get(fromId).getAmount() - amount);
            userStorage.get(toId).setAmount(userStorage.get(toId).getAmount() - amount);
        }
    }
}
