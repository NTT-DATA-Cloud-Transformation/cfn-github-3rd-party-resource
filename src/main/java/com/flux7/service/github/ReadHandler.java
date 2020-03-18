package com.flux7.service.github;

import org.kohsuke.github.GHMyself;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import software.amazon.cloudformation.proxy.AmazonWebServicesClientProxy;
import software.amazon.cloudformation.proxy.Logger;
import software.amazon.cloudformation.proxy.ProgressEvent;
import software.amazon.cloudformation.proxy.OperationStatus;
import software.amazon.cloudformation.proxy.ResourceHandlerRequest;

import java.io.IOException;

public class ReadHandler extends BaseHandler<CallbackContext> {

    @Override
    public ProgressEvent<ResourceModel, CallbackContext> handleRequest(final AmazonWebServicesClientProxy proxy,
            final ResourceHandlerRequest<ResourceModel> request, final CallbackContext callbackContext,
            final Logger logger) {

        final ResourceModel model = request.getDesiredResourceState();

        // TODO : your code starts here
        try {
            GitHub github = new GitHubBuilder().withOAuthToken(model.getRepositoryAccessToken()).build();

            GHMyself ghm = github.getMyself();
            String username = ghm.getLogin();

            if (model.getOrganizationName() == null) {
                GHRepository repo = github.getRepository(username + "/" + model.getRepositoryName());
                String description = repo.getDescription();
                String name = repo.getName();

                model.setRepositoryDescription(description);
                model.setRepositoryName(name);

            } else {
                GHRepository repo = github.getRepository(model.getOrganizationName() + "/" + model.getRepositoryName());
                String description = repo.getDescription();
                String name = repo.getName();

                model.setRepositoryDescription(description);
                model.setRepositoryName(name);
            }

            return ProgressEvent.<ResourceModel, CallbackContext>builder().resourceModel(model)
                    .status(OperationStatus.SUCCESS).build();

        } catch (IOException | IllegalStateException e) {
            logger.log(e.getMessage());
            return ProgressEvent.<ResourceModel, CallbackContext>builder().resourceModel(model)
                    .status(OperationStatus.FAILED).message(e.getMessage()).build();
            // TODO : code ends here
        }
    }
}
