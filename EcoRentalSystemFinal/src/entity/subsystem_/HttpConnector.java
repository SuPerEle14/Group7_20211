package entity.subsystem_;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import utils.Utils;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@Slf4j
public class HttpConnector {
	
	private static final Logger LOGGER = Utils.getLogger(HttpConnector.class.getName());
    private final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(10000, TimeUnit.MILLISECONDS)
            .readTimeout(10000,TimeUnit.MILLISECONDS)
            .retryOnConnectionFailure(true)
            .build() ;

    public String sendPatch(String url , String body) {
        try {
            RequestBody requestBody = RequestBody.create(MediaType.parse(org.springframework.http.MediaType.APPLICATION_JSON_VALUE), body);
            Request request = new Request.Builder().url(url)
                    .patch(requestBody).build();
            Response response = client.newCall(request).execute();
            assert response.body() != null;
            return response.body().string();
        } catch (Exception e) {
            LOGGER.info("Something went wrong with sendPath!");
        }
        return null;
    }
}
