# Update GitHub repository
Update properties of GitHub repository created using `Flux7::Service::GitHub` AWS resource.

## Syntax
Change the values in your AWS CloudFormation template to update github repository as required except RepositoryName and OrganizationName. Alway have RepositoryName, RepositoryAccessToken and OrganizationName (if created repository is in Organization) in AWS CloudFormation template as these are required properties. Use the following syntax -


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
   "OrganizationName": String,
   "EnableIssues": Boolean,
   "EnableWiki": Boolean,
   "EnableDownloads": Boolean
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
  EnableIssues: Boolean,
  EnableWiki: Boolean,
  EnableDownloads: Boolean
```

### Properties


_RepositoryName_

	The name of the repository you want to create in GitHub with AWS CloudFormation stack creation.
	Required: Yes
	Type: String
	Update requires: Updates are not Supported
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
 
 _OrganizationName_

	If populated with existing Organization Name, creates a Repository on the Organization Account.
	Required: No
	Type: String
	Update requires: Updates are not Supported
 
 _EnableIssues_
 
 	Indicates whether to enable issues for the GitHub repository. You can use GitHub issues to track information and bugs for your repository.
	Required: No
	Type: Boolean
	Update requires: Update with No Interruption
 
 _EnableWiki_
 
 	Indicates whether to enable wiki for the GitHub repository. You can use GitHub wiki for hosting documentation for your repository in a wiki, so that others can use and contribute to your project.
	Required: No
	Type: Boolean
	Update requires: Update with No Interruption
	
 _EnableDownloads_
 
 	Indicates whether to enable downloads for the GitHub repository. You can use GitHub downloads to publish a package to GitHub Packages to make the package available for others to download and re-use.
	Required: No
	Type: Boolean
	Update requires: Update with No Interruption

### Command
Following command creates CloudFormation stack that creates GitHub repository with above details.
```
aws cloudformation update-stack --region us-east-1 --template-body "file://stack.json" --stack-name "GitHub-Repo-CF-Stack"
```
