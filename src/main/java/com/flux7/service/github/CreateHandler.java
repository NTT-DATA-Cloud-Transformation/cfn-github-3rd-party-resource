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

        try {
            GHCreateRepositoryBuilder builder;

            GitHub github = new GitHubBuilder().withOAuthToken(model.getRepositoryAccessToken()).build();

            if (model.getOrganizationName() == null) {
                builder = github.createRepository(model.getRepositoryName());
            } else {
                builder = github.getOrganization(model.getOrganizationName()).createRepository(model.getRepositoryName());
            }

            builder.private_(model.getIsPrivate()).homepage("www.flux7.com").issues(false).downloads(false).wiki(false)
                    .description(model.getRepositoryDescription()).create();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return ProgressEvent.<ResourceModel, CallbackContext>builder().resourceModel(model)
                .status(OperationStatus.SUCCESS).build();

    }
}
