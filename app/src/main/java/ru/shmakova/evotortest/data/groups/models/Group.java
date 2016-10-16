package ru.shmakova.evotortest.data.groups.models;

import com.google.auto.value.AutoValue;

/**
 * Created by shmakova on 16.10.16.
 */

@AutoValue
public abstract class Group {
    public static Group.Builder builder() {
        return new AutoValue_Group.Builder();
    }

    public abstract String filename();

    public abstract String title();

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Group.Builder title(String title);

        public abstract Group.Builder filename(String filename);

        public abstract Group build();
    }
}
