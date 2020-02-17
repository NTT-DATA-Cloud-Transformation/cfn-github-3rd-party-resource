# Create GitHub repository
You can create personal github repository or organizational repository using this  `Flux7::Service::GitHub` AWS resource.

## Syntax
To declare this entity in your AWS CloudFormation template, use the following syntax: 

### JSON
```
{
 "Type": "Flux7::Service::Github",
 "Properties": {
   "RepositoryName": String,
   "RepositoryDescription": String,
   "RepositoryAccessToken": String,
   "IsPrivate": Boolean,
   "RepositoryOwner": String,
   "OrganizationName": String
 }
}
```

### YAML

```
Type: Flux7::Service::Github
Properties:
  RepositoryName: String
  RepositoryDescription: String
  RepositoryAccessToken: String
  IsPrivate: Boolean
  RepositoryOwner: String
  OrganizationName: String
```

### Command
Following command creates CloudFormation stack that creates GitHub repository with above details.
```
aws cloudformation create-stack --region us-east-1 --template-body "file://stack.json" --stack-name "GitHub-Repo-CF-Stack"
```