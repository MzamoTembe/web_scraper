package com.myorg.pipeline;
import com.myorg.stacks.WebScraperStack;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.Stage;
import software.amazon.awscdk.StageProps;
import software.constructs.Construct;

public class PipelineDeploymentStage extends Stage {
    public PipelineDeploymentStage(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public PipelineDeploymentStage(final Construct scope, final String id, final StageProps props) {
        super(scope, id, props);

        final Stack scraperStack = new WebScraperStack(this, "WebScraperStack");
    }

}
