package com.flux7.github.repository;

import org.junit.jupiter.api.condition.DisabledIf;
import software.amazon.cloudformation.proxy.AmazonWebServicesClientProxy;
import software.amazon.cloudformation.proxy.Logger;
import software.amazon.cloudformation.proxy.OperationStatus;
import software.amazon.cloudformation.proxy.ProgressEvent;
import software.amazon.cloudformation.proxy.ResourceHandlerRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class UpdateHandlerTest {

    @Mock
    private AmazonWebServicesClientProxy proxy;

    @Mock
    private Logger logger;

    @BeforeEach
    public void setup() {
        proxy = mock(AmazonWebServicesClientProxy.class);
        logger = mock(Logger.class);
    }

    @Test
    @DisabledIf("systemProperty.get('RepositoryName') == null || systemProperty.get('AccessToken') == null || systemProperty.get('OrganizationOrUserName') == null")
    public void handleRequest_SimpleSuccess() {
        final UpdateHandler handler = new UpdateHandler();

        final ResourceModel model = ResourceModel.builder().build();
        model.setRepositoryName(System.getProperty("RepositoryName"));
        model.setPersonalAccessToken(System.getProperty("AccessToken"));
        model.setOrganizationOrUserName(System.getProperty("OrganizationOrUserName"));
        model.setRepositoryDescription("Repository description");
        model.setIsPrivate(true);
        model.setEnableIssues(true);
        model.setEnableWiki(true);
        model.setEnableDownloads(true);



        final ResourceHandlerRequest<ResourceModel> request = ResourceHandlerRequest.<ResourceModel>builder()
                .desiredResourceState(model)
                .build();

        final ProgressEvent<ResourceModel, CallbackContext> createResponse
                = new CreateHandler().handleRequest(proxy, request, null, logger);

        final ProgressEvent<ResourceModel, CallbackContext> response
                = handler.handleRequest(proxy, request, null, logger);

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(OperationStatus.SUCCESS);
        assertThat(response.getCallbackContext()).isNull();
        assertThat(response.getCallbackDelaySeconds()).isEqualTo(0);
        assertThat(response.getResourceModel()).isEqualTo(request.getDesiredResourceState());
        assertThat(response.getResourceModels()).isNull();
        assertThat(response.getMessage()).isNull();
        assertThat(response.getErrorCode()).isNull();

        final ProgressEvent<ResourceModel, CallbackContext> response1
                = new DeleteHandler().handleRequest(proxy, request, null, logger);
    }
}
