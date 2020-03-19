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
    try{
        logger.log(model.toString());
        // Try to get Additional Identifier
//        logger.log("OrganizationName " + model.getIdentifier_OrganizationName().getString("/properties/OrganizationName"));
/*
        model.setRepositoryAccessToken("a7314083645158d08708016e2628745efa14043b");
        model.setRepositoryDescription("Repo description");
        model.setIsPrivate(false);

        model.setOrganizationName("AWSGitTestAcc");
        model.setEnableIssues(true);
        model.setEnableWiki(true);
        model.setEnableDownloads(true);
*/
        logger.log("After set method: " + model.toString());
        // TODO : your code starts here
        return ProgressEvent.<ResourceModel, CallbackContext>builder()
                .resourceModel(model)
                .status(OperationStatus.SUCCESS)
                .build();
        }
    catch (NullPointerException e) {
            logger.log(e.getMessage());
            return ProgressEvent.<ResourceModel, CallbackContext>builder()
                    .resourceModel(model)
                    .status(OperationStatus.FAILED)
                    .message(e.getMessage())
                    .build();
        }
    }
}
