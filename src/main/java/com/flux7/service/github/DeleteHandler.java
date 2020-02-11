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

import static java.lang.Thread.sleep;

public class DeleteHandler extends BaseHandler<CallbackContext> {

    @Override
    public ProgressEvent<ResourceModel, CallbackContext> handleRequest(final AmazonWebServicesClientProxy proxy,
            final ResourceHandlerRequest<ResourceModel> request, final CallbackContext callbackContext,
            final Logger logger) {

        final ResourceModel model = request.getDesiredResourceState();
        final ResourceModel model1 = request.getPreviousResourceState();

        // TODO : code starts here

        try {            

            GitHub github = new GitHubBuilder().withOAuthToken(model1.getRepositoryAccessToken()).build();

                GHRepository repo = github.getRepository(model1.getRepositoryName());
//                sleep(10000);
                repo.delete();

        } catch (NullPointerException | IllegalStateException | IOException e) {

            e.printStackTrace();

        }

        // TODO : code ends here

        return ProgressEvent.<ResourceModel, CallbackContext>builder().resourceModel(model)
                .status(OperationStatus.SUCCESS).build();
    }
}
