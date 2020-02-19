# Update GitHub repository
Update properties of GitHub repository created using `Flux7::Service::GitHub` AWS resource.

## Syntax
Change values of RepositoryDescription and IsPrivate in your AWS CloudFormation template to update github repository. Use the following syntax -


### JSON
File `stack.json` -
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
File `stack.yaml` -
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

### Properties


_RepositoryName_

	The name of the repository you want to create in GitHub with AWS CloudFormation stack creation.
	Required: Yes
	Type: String
	Update requires: No
	Pattern: ^[A-Za-z0-9_-]*\\.*[A-Za-z0-9_-]+$

_RepositoryDescription_

	A comment or description about the new repository. This description is displayed in GitHub after the repository is created.
	Required: No
	Type: String
	Update requires: Update with No Interruption
 
_RepositoryAccessToken_

	The GitHub user's personal access token for the GitHub repository.
	Required: Yes
	Type: String
	Update requires: Update with No Interruption
 
_IsPrivate_

	Indicates whether the GitHub repository is a public repository. If so, you choose who can see and commit to this repository.
	Required: No
	Type: Boolean
	Update requires: Update with No Interruption

_RepositoryOwner_

	The GitHub user name for the owner of the GitHub repository to be created. If this repository should be owned by a GitHub organization, provide its name.
	Required: No
	Type: String
	Update requires: Update with No Interruption
 
 _OrganizationName_

	If populated with existing Organization Name, creates a Repository on the Organization Account.
	Required: No
	Type: String
	Update requires: Update with No Interruption

### Command
Following command creates CloudFormation stack that creates GitHub repository with above details.
```
aws cloudformation update-stack --region us-east-1 --template-body "file://stack.json" --stack-name "GitHub-Repo-CF-Stack"
```
