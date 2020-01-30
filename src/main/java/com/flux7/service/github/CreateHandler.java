package com.flux7.service.github;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import software.amazon.cloudformation.proxy.*;

import java.io.IOException;
import java.net.MalformedURLException;

public class CreateHandler extends BaseHandler<CallbackContext> {

    @Override
    public ProgressEvent<ResourceModel, CallbackContext> handleRequest(final AmazonWebServicesClientProxy proxy,
                                                                       final ResourceHandlerRequest<ResourceModel> request, final CallbackContext callbackContext,
                                                                       final Logger logger) {

        final ResourceModel model = request.getDesiredResourceState();

        try {
            GitHub github = new GitHubBuilder().withOAuthToken("e4b2de4fb741288ae1de3f2b7cffde62c7c1e36b").build();

            GHRepository repo = github.createRepository("new-repository", "this is my new repository",
                    "https://www.flux7.com/", true/* public */);
            repo.addCollaborators(github.getUser("deepsvc"));

            //    github = new GitHubBuilder().withOAuthToken("e4b2de4fb741288ae1de3f2b7cffde62c7c1e36b").build();

            // String userCredentials = "e4b2de4fb741288ae1de3f2b7cffde62c7c1e36b";
            // String basicAuth = "e4b2de4fb741288ae1de3f2b7cffde62c7c1e36b" + new
            // String(Base64.getEncoder().encode(userCredentials.getBytes()));

            // URL url = new URL("https://api.github.com/user/<user name>/repos");
            // HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // conn.setRequestProperty ("Authorization", basicAuth);
            // //conn.setDoOutput(true);
            // conn.setRequestMethod("POST");
            // conn.setRequestProperty("Content-Type", "application/json");

            // String input = "{\"qty\":100,\"name\":\"iPad 4\"}";
            // String input = "{\n" +
            // "\"name\": \"Hello-World\",\n" +
            // "\"description\": \"This is your first repository\",\n" +
            // "\"homepage\": \"https://github.com\",\n" +
            // "\"private\": true,\n" +
            // "}";
            // OutputStream os = conn.getOutputStream();
            // os.write(input.getBytes());
            // os.flush();
            //
            // if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
            // throw new RuntimeException("Failed : HTTP error code : "
            // + conn.getResponseCode());
            // }
            //
            // BufferedReader br = new BufferedReader(new InputStreamReader(
            // (conn.getInputStream())));
            //
            // String output;
            // System.out.println("Output from GitHub .... \n");
            // while ((output = br.readLine()) != null) {
            // System.out.println(output);
            // }
            //
            // conn.disconnect();
            //
        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

        // your code ends here

        return ProgressEvent.<ResourceModel, CallbackContext>builder().resourceModel(model)
                .status(OperationStatus.SUCCESS).build();
    }
}
