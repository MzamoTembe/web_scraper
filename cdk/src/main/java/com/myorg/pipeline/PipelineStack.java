package com.myorg.pipeline;
import software.amazon.awscdk.Environment;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.pipelines.*;
import software.constructs.Construct;
import software.amazon.awscdk.StageProps;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;

public class PipelineStack extends Stack {

    public PipelineStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public PipelineStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        final String pipelineName = "WebScraperPipeline";

        final CodePipelineSource sourceAction = CodePipelineSource.gitHub("MzamoTembe/web_scraper", "main");

        final List<String> buildCommands = Arrays.asList(
                "cd cdk",
                "npm install -g aws-cdk",
                "mvn clean package",
                "npx cdk synth"
        );

        final CodeBuildStep synthStep = CodeBuildStep.Builder.create("SynthStep")
                .input(sourceAction)
                .installCommands(Collections.singletonList("npm install -g aws-cdk"))
                .primaryOutputDirectory("cdk/cdk.out")
                .commands(buildCommands)
                .build();

        final CodePipeline pipeline = CodePipeline.Builder.create(this, "Pipeline")
                .pipelineName(pipelineName)
                .synth(synthStep)
                .build();

        final StageDeployment deploymentStage = pipeline.addStage(new PipelineDeploymentStage(this, "Deployment", StageProps.builder()
                .env(Environment.builder()
                        .account(System.getenv("DEFAULT"))
                        .region(System.getenv("DEFAULT"))
                        .build())
                .build()));

        deploymentStage.addPost(new ManualApprovalStep("approval"));
    }
}
