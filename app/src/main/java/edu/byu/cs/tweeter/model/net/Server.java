package edu.byu.cs.tweeter.model.net;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import edu.byu.cs.tweeter.model.service.request.FeedRequest;
import edu.byu.cs.tweeter.model.service.request.FollowersRequest;
import edu.byu.cs.tweeter.model.service.request.FollowingRequest;
import edu.byu.cs.tweeter.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.model.service.request.RegisterRequest;
import edu.byu.cs.tweeter.model.service.response.FeedResponse;
import edu.byu.cs.tweeter.model.service.response.FollowersResponse;
import edu.byu.cs.tweeter.model.service.response.FollowingResponse;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;
import edu.byu.cs.tweeter.model.service.response.RegisterResponse;

public class Server {
    private final String baseAPI = "https://x5404fmdia.execute-api.us-west-2.amazonaws.com/Beta";

    public LoginResponse login(LoginRequest request) throws TweeterRemoteException {
        String urlString = baseAPI + "/login";

        String stringResponse = makeAPICall(urlString, "POST", request);

        Gson gson = new Gson();
        LoginResponse response = gson.fromJson(stringResponse, LoginResponse.class);
        return response;

    }

    public RegisterResponse register(RegisterRequest request) throws TweeterRemoteException {
        String urlString = baseAPI + "/register";

        String stringResponse = makeAPICall(urlString, "POST", request);
        Gson gson = new Gson();
        RegisterResponse response = gson.fromJson(stringResponse, RegisterResponse.class);
        return response;
    }

    public FollowingResponse getFollowing(FollowingRequest request) throws TweeterRemoteException {
        String urlString = baseAPI + "/getfollowing";

        String stringResponse = makeAPICall(urlString, "POST", request);

        Gson gson = new Gson();
        FollowingResponse response = gson.fromJson(stringResponse, FollowingResponse.class);
        return response;
    }

    public FollowersResponse getFollowers(FollowersRequest request) throws TweeterRemoteException {
        String urlString = baseAPI + "/getfollowers";

        String stringResponse = makeAPICall(urlString, "POST", request);

        Gson gson = new Gson();
        FollowersResponse response = gson.fromJson(stringResponse, FollowersResponse.class);
        return response;
    }

    public FeedResponse getFeed(FeedRequest request) throws TweeterRemoteException {
        String urlString = baseAPI + "/getfeed";

        String stringResponse = makeAPICall(urlString, "POST", request);

        Gson gson = new Gson();
        FeedResponse response = gson.fromJson(stringResponse, FeedResponse.class);
        return response;
    }

    private String makeAPICall(String apiURL, String method, Object request) throws TweeterRemoteException {
        try {
            URL url = new URL(apiURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            Gson gson = new Gson();
            String json = gson.toJson(request);
            System.out.println(json);

            OutputStreamWriter os = new OutputStreamWriter(connection.getOutputStream());
            os.write(json);
            os.close();

            BufferedReader is = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line = null;
            while ((line = is.readLine()) != null) {
                response.append(line);
            }
            return response.toString();

        } catch (IOException e) {
            throw new TweeterRemoteException(e.getMessage(), null, null);
        }
    }

}
