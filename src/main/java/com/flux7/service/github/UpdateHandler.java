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
            boolean IsPrivate = model.getIsPrivate();

            if (model.getOrganizationName() == null) {
                GHRepository repo = github.getRepository(username + "/" + model.getRepositoryName());
                repo.setDescription(model.getRepositoryDescription());
                repo.setPrivate(IsPrivate);
                repo.enableDownloads(model.getEnableDownloads());
                repo.enableWiki(model.getEnableWiki());
            } else {
                GHRepository repo = github.getRepository(model.getOrganizationName() + "/" + model.getRepositoryName());
                repo.setDescription(model.getRepositoryDescription());
                repo.setPrivate(IsPrivate);
                repo.enableDownloads(model.getEnableDownloads());
                repo.enableWiki(model.getEnableWiki());
            }
            return ProgressEvent.<ResourceModel, CallbackContext>builder().resourceModel(model)
                    .status(OperationStatus.SUCCESS).build();
        } catch (NullPointerException | IOException | IllegalStateException e) {
            logger.log(e.getMessage());
            return ProgressEvent.<ResourceModel, CallbackContext>builder().resourceModel(model)
                    .status(OperationStatus.FAILED).message(e.getMessage()).build();
        }

        // TODO : code ends here
    }
}
