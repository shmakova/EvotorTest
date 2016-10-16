package ru.shmakova.evotortest.data.content.models;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * Created by shmakova on 19.09.16.
 */

@AutoValue
public abstract class Content {
    public static Builder builder() {
        return new AutoValue_Content.Builder();
    }

    public static TypeAdapter<Content> typeAdapter(Gson gson) {
        return new AutoValue_Content.GsonTypeAdapter(gson);
    }

    @SerializedName("pic")
    public abstract String pic();

    @SerializedName("title")
    public abstract String title();

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder pic(String pic);

        public abstract Builder title(String title);

        public abstract Content build();
    }
}
