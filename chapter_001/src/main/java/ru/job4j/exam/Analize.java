package ru.job4j.exam;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(List<User> previous, List<User> current) {
        Map<Integer, User> currentMap = current.stream().collect(Collectors.toMap(User::getId, user -> user));
        int deleted = 0;
        int changed = 0;
        for (User previousUser : previous) {
            if (!currentMap.containsKey(previousUser.id)) {
                deleted++;
            }
            var currentUser = currentMap.remove(previousUser.id);
            if (currentUser != null && !previousUser.name.equals(currentUser.getName())) {
                changed++;
            }
        }
        return new Info(currentMap.size(), changed, deleted);
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
