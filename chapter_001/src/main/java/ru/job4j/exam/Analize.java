package ru.job4j.exam;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(List<User> previous, List<User> current) {
        Map<Integer, User> currentMap = current.stream().collect(Collectors.toMap(User::getId, user -> user));
        int added = 0;
        int deleted = 0;
        int changed = 0;
        for (Integer key : currentMap.keySet()) {
            int prevIndex = previous.indexOf(currentMap.get(key));
            User previousUser = null;
            if (prevIndex >= 0) {
                previousUser = previous.get(prevIndex);
            }
            if (!previous.remove(currentMap.get(key))) {
                added++;
            } else if (previousUser != null && !currentMap.get(key).name.equals(previousUser.name)) {
                changed++;
            }
            deleted = previous.size();
        }
        return new Info(added, changed, deleted);
    }

    public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return id == user.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

    public static class Info {

        private final int added;
        private final int changed;
        private final int deleted;

        public int getAdded() {
            return added;
        }

        public int getChanged() {
            return changed;
        }

        public int getDeleted() {
            return deleted;
        }

        public Info(int added, int changed, int deleted) {
            this.added = added;
            this.changed = changed;
            this.deleted = deleted;
        }
    }

}
