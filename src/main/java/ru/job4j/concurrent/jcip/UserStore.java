package ru.job4j.concurrent.jcip;

import java.util.HashMap;
import java.util.Map;

public class UserStore {
    private Map<Integer, User> userStorage = new HashMap<Integer, User>();

    public boolean add(User user) {
       return userStorage.put(user.getId(), user) != null;
    }

    public boolean update(User user) {
        boolean result;
        if (userStorage.containsKey(user.getId())) {
            result = userStorage.put(user.getId(), user) != null;
        } else {
            result = false;
        }
        return result;
    }

    public boolean delete(User user) {
        return userStorage.remove(user.getId()) != null;
    }

    public synchronized void transfer(int fromId, int toId, int amount) {
        userStorage.get(fromId).changeAmount(-amount);
        userStorage.get(toId).changeAmount(+amount);
    }
}
