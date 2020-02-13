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
        // TODO : code starts here

        try {

            GitHub github = new GitHubBuilder().withOAuthToken(model.getRepositoryAccessToken()).build();

            GHMyself ghm = github.getMyself();
            String username = ghm.getLogin();

            if (model.getOrganizationName() == null) {
                GHRepository repo = github.getRepository(username + "/" + model.getRepositoryName());
                repo.setDescription(model.getRepositoryDescription());
                if (model.getIsPrivate()) {
                    repo.setPrivate(true);
                } else {
                    repo.setPrivate(false);
                }
            } else {
                GHRepository repo = github.getRepository(model.getOrganizationName() + "/" + model.getRepositoryName());
                repo.setDescription(model.getRepositoryDescription());
                if (model.getIsPrivate()) {
                    repo.setPrivate(true);
                } else {
                    repo.setPrivate(false);
                }
            }

        } catch (NullPointerException | IOException | IllegalStateException e) {

            e.printStackTrace();

        }

        // TODO : code ends here

        return ProgressEvent.<ResourceModel, CallbackContext>builder().resourceModel(model)
                .status(OperationStatus.SUCCESS).build();
    }
}
