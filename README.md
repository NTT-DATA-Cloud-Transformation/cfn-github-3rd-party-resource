# Resource provider Flux7::Service::Github

## Resource Providers

To use third-party resources in your infrastructure and applications, now you can develop the resource providers for use within CloudFormation. The resource provider includes resource specification and handlers control API interactions with the underlying AWS or third-party services. 

> :bulb: Complete documentation is at @ https://docs.aws.amazon.com/cloudformation-cli/latest/userguide/resource-types.html

Congratulations on starting development! Next steps:

1. Write the JSON schema describing your resource, `flux7-service-github.json`
2. The RPDK will automatically generate the correct resource model from the
   schema whenever the project is built via Maven. You can also do this manually
   with the following command: `cfn generate`
3. Implement your resource handlers


Please don't modify files under `target/generated-sources/rpdk`, as they will be
automatically overwritten.

The code use [Lombok](https://projectlombok.org/), and [you may have to install
IDE integrations](https://projectlombok.org/) to enable auto-complete for
Lombok-annotated classes.

## How to use custome resource
Sample cloudformation template -
```
{
    "AWSTemplateFormatVersion": "2010-09-09",
    "Description": "Github stack",
    "Resources": {
        "MyGithubRepo": {
            "Type": "Flux7::Service::Github",
            "Properties": {
                "RepositoryName": "<Repository name>",
                "RepositoryAccessToken": "<Your repo token>",
                "IsPrivate": true,
                "RepositoryDescription": "<Repo Description>",
                "RepositoryOwner": "<Repo Owner>",
                "OrganizationName": "<Org Name>"
            }
        }
    }
}
```
## Commands to Create Custom Resource

`$ mvn package`
`$ cfn submit -v --region <region>`
##### To set Default Version (if needed)
`$ aws cloudformation set-type-default-version --type "RESOURCE" --type-name "<resource name (sample::resource::type)>" --version-id "<version_id>" --region <region>`
##### To run the stack
`$ aws cloudformation create-stack --region <region> --template-body "file://stack.json" --stack-name "<stack_name>"`
