package com.myorg.stacks;
import software.amazon.awscdk.*;
import software.amazon.awscdk.services.events.Rule;
import software.amazon.awscdk.services.events.Schedule;
import software.amazon.awscdk.services.events.targets.LambdaFunction;
import software.amazon.awscdk.services.lambda.Function;
import software.amazon.awscdk.services.lambda.InlineCode;
import software.amazon.awscdk.services.lambda.Runtime;
import software.amazon.awscdk.services.sns.Topic;
import software.amazon.awscdk.services.sns.subscriptions.EmailSubscription;
import software.constructs.Construct;

public class WebScraperStack extends Stack {
    public WebScraperStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public WebScraperStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        final Topic topic = Topic.Builder.create(this, "SNSTopic")
                .displayName("In Stock Scraper Topic")
                .topicName("WebScraperTopic")
                .build();

        topic.addSubscription(EmailSubscription.Builder.create("mzamotembe7@gmail.com")
                .build());

        final Function lambdaFunction = Function.Builder.create(this, "LambdaFunction")
                .runtime(Runtime.NODEJS_LATEST)
                .handler("index.handler")
                .code(new InlineCode("exports.handler = _ => 'Hello, CDK';"))
                .build();

        lambdaFunction.addEnvironment("TopicArn", topic.getTopicArn());

        final Rule eventBridgeRule = Rule.Builder.create(this, "EventBridgeScheduleRule")
                .schedule(Schedule.rate(Duration.days(7)))
                .build();

        eventBridgeRule.addTarget(new LambdaFunction(lambdaFunction));
    }
}