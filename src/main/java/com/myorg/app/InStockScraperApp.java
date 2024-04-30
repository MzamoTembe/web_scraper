package com.myorg.app;
import com.myorg.pipeline.PipelineStack;
import software.amazon.awscdk.App;
import software.amazon.awscdk.Environment;
import software.amazon.awscdk.StackProps;

public class InStockScraperApp {
    public static void main(final String[] args) {
        App app = new App();

        new PipelineStack(app, "WebScraperPipelineStack", StackProps.builder()
                .env(Environment.builder()
                        .account(System.getenv("DEFAULT"))
                        .region(System.getenv("DEFAULT"))
                        .build())
                .build());

        app.synth();
    }
}

