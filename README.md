# Web Stock Scraper

This is a very exaggerated and unnecessary way of implementing a simple web scraper that will periodically check if an item is in stock. But, I have no social life so might as well over-engineer something for vibes. After all, don't we all want to be 10x engineers 🙂

[View on Eraser![](https://app.eraser.io/workspace/xctPSM2xJoGBNcMhFvjG/preview?elements=qDFSNUQ-ivBFCRq-5anJRQ&type=embed)](https://app.eraser.io/workspace/xctPSM2xJoGBNcMhFvjG?elements=qDFSNUQ-ivBFCRq-5anJRQ)
## How It Works

1. **AWS EventBridge Trigger**: An EventBridge event triggers the Lambda function every 5 minutes.
2. **Lambda Function**: The Lambda function makes a request to check if the item is in stock.
3. **Check Stock**: If the item is in stock, the Lambda function publishes a message to an AWS SNS topic.
4. **SNS Subscription**: There is a subscription (Pub/Sub) to the SNS topic that sends out an email informing about the item's availability.

## CI/CD Components

- **CloudWatch Logs**: Logs are generated for Lambda function invocations and events.
- **GitHub**: The source code is stored in GitHub for version control.
- **CDK and CloudFormation**: Infrastructure is defined as code using CloudFormation or CDK.
- **CodeBuild**: CodeBuild is used for building and testing the code.
- **CodePipeline**: CodePipeline automates the CI/CD process.

## Additional Information

#### Project Structure

This is a CDK Java project with the following structure:

- `cdk.json`: Configuration file for the CDK Toolkit.
- `pom.xml`: Maven configuration for building and managing dependencies.
- `src/`: Source code directory containing your CDK stack implementations.
- `README.md`: Project documentation.

#### Useful Commands

- `mvn package`: Compile and run tests.
- `cdk ls`: List all stacks in the app.
- `cdk synth`: Emit the synthesized CloudFormation template.
- `cdk deploy`: Deploy this stack to your default AWS account/region.
- `cdk diff`: Compare deployed stack with the current state.
- `cdk docs`: Open CDK documentation.