package com.flux7.service.github;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
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

//model.getAdditionalIdentifiers()
        try {
            JSONObject jsondata = model.getPrimaryIdentifier();
            String reponame = jsondata.getString("/properties/RepositoryName");
            String gittoken = jsondata.getString("/properties/RepositoryAccessToken");
            String gitowner = jsondata.getString("/properties/RepositoryOwner");

            GitHub github = new GitHubBuilder().withOAuthToken(gittoken).build();

            GHRepository repo = github.createRepository(reponame, "this is my new repository",
                    "https://www.flux7.com/", true/* public */);
            repo.addCollaborators(github.getUser(gitowner));

        } catch (NullPointerException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

        // your code ends here

        return ProgressEvent.<ResourceModel, CallbackContext>builder().resourceModel(model)
                .status(OperationStatus.SUCCESS).build();
    }
}
