package com.flux7.service.github;

import org.kohsuke.github.GHCreateRepositoryBuilder;
import org.kohsuke.github.GHMyself;
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
            GHMyself ghm = github.getMyself();
            String username = ghm.getLogin();

            logger.log("User name: " + username);
            logger.log("Model Organization name: " + model.getOrganizationName());

            if (model.getOrganizationName().equals(username)) {
                builder = github.createRepository(model.getRepositoryName());
            } else {
                builder = github.getOrganization(model.getOrganizationName())
                        .createRepository(model.getRepositoryName());
            }

            boolean isPrivate = true;
            if (! (model.getIsPrivate() == null)) {isPrivate = model.getIsPrivate();}
            boolean enableIssues = true;
            if (! (model.getEnableIssues() == null)) {enableIssues = model.getEnableIssues();}
            boolean enableDownloads = true;
            if (! (model.getEnableDownloads() == null)) {enableDownloads = model.getEnableDownloads();}
            boolean enableWiki = true;
            if (! (model.getEnableWiki() == null)) {enableWiki = model.getEnableWiki();}
            String repositoryDescription = " ";
            if (! (model.getRepositoryDescription() == null)) {repositoryDescription = model.getRepositoryDescription();}

            builder.private_(isPrivate)
                    .issues(enableIssues)
                    .downloads(enableDownloads)
                    .wiki(enableWiki)
                    .description(repositoryDescription)
                    .create();

            return ProgressEvent.<ResourceModel, CallbackContext>builder()
                    .resourceModel(model)
                    .status(OperationStatus.SUCCESS)
                    .build();

        } catch (IllegalStateException | IOException | NullPointerException e) {
            logger.log(e.getMessage());
            return ProgressEvent.<ResourceModel, CallbackContext>builder()
                    .resourceModel(model)
                    .status(OperationStatus.FAILED)
                    .message(e.getMessage())
                    .build();
        }
        // TODO : code ends here
    }
}
