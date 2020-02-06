package com.flux7.service.github;

import org.kohsuke.github.GHMyself;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import software.amazon.cloudformation.proxy.*;

import java.io.IOException;

public class UpdateHandler extends BaseHandler<CallbackContext> {

    @Override
    public ProgressEvent<ResourceModel, CallbackContext> handleRequest(final AmazonWebServicesClientProxy proxy,
            final ResourceHandlerRequest<ResourceModel> request, final CallbackContext callbackContext,
            final Logger logger) {

        final ResourceModel model = request.getDesiredResourceState();
        try {

            // TODO : put your code here

            GitHub github = new GitHubBuilder().withOAuthToken(model.getRepositoryAccessToken()).build();

            GHMyself ghm = github.getMyself();
            String username = ghm.getLogin();

            if (model.getOrganizationName() == null) {
                GHRepository repo = github.getRepository(username + "/" + model.getRepositoryName());
                repo.setDescription(model.getRepositoryDescription());
                if (model.getIsPrivate() == true) {
                    repo.setPrivate(true);
                } else {
                    repo.setPrivate(false);
                }
            } else {
                GHRepository repo = github.getRepository(model.getOrganizationName() + "/" + model.getRepositoryName());
                repo.setDescription(model.getRepositoryDescription());
                if (model.getIsPrivate() == true) {
                    repo.setPrivate(true);
                } else {
                    repo.setPrivate(false);
                }
            }

        } catch (NullPointerException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

        // TODO : your code end here

        return ProgressEvent.<ResourceModel, CallbackContext>builder().resourceModel(model)
                .status(OperationStatus.SUCCESS).build();
    }
}
