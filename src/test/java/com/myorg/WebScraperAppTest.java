package com.myorg;
import static org.junit.jupiter.api.Assertions.*;
import com.myorg.pipeline.PipelineStack;
import org.junit.jupiter.api.Test;
import software.amazon.awscdk.App;
import software.amazon.awscdk.Environment;
import software.amazon.awscdk.StackProps;

public class WebScraperAppTest {
    @Test
    public void testAppCreation() {
        App app = new App();

        PipelineStack pipelineStack = new PipelineStack(app, "PipelineStack", StackProps.builder()
                .env(Environment.builder()
                        .account(System.getenv("DEFAULT"))
                        .region(System.getenv("DEFAULT"))
                        .build())
                .build());

        assertNotNull(pipelineStack, "PipelineStack object should not be null.");

        app.synth();
    }
}