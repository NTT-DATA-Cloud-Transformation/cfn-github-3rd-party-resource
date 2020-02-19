package com.flux7.service.github;

import org.kohsuke.github.GHCreateRepositoryBuilder;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import software.amazon.cloudformation.proxy.*;

import java.io.IOException;

public class CreateHandler extends BaseHandler<CallbackContext> {

    @Override
    public ProgressEvent<ResourceModel, CallbackContext> handleRequest(final AmazonWebServicesClientProxy proxy,
            final ResourceHandlerRequest<ResourceModel> request, final CallbackContext callbackContext,
            final Logger logger) {

        final ResourceModel model = request.getDesiredResourceState();
        // TODO : code starts here
        try {
            GHCreateRepositoryBuilder builder;

            GitHub github = new GitHubBuilder().withOAuthToken(model.getRepositoryAccessToken()).build();

            if (model.getOrganization   Name() == null) {
                builder = github.createRepository(model.getRepositoryName());
            } else {
                builder = github.getOrganization(model.getOrganizationName())
                        .createRepository(model.getRepositoryName());
            }

            if (model.getIsPrivate() == null) {
                builder.private_(true).description(model.getRepositoryDescription()).create();
            } else {
                builder.private_(model.getIsPrivate()).description(model.getRepositoryDescription()).create();
            }

            return ProgressEvent.<ResourceModel, CallbackContext>builder().resourceModel(model)
                    .status(OperationStatus.SUCCESS).build();

        } catch (IOException | NullPointerException e) {
            logger.log(e.getMessage());
            return ProgressEvent.<ResourceModel, CallbackContext>builder().resourceModel(model)
                    .status(OperationStatus.FAILED).message(e.getMessage()).build();
        }
        // TODO : code ends here
    }
}
