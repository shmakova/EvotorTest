package ru.shmakova.evotortest.data.content;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.shmakova.evotortest.data.content.models.Content;
import rx.Observable;

/**
 * Created by shmakova on 16.10.16.
 */

public interface NixanService {
    String BASE_URL = "http://nixan.org/";

    @GET("{filename}.json")
    Observable<List<Content>> getContent(@Path("filename") String filename);
}
