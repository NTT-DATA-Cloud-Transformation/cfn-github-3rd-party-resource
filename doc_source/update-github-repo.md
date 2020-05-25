# Update GitHub repository
Update properties of GitHub repository created using `Flux7::GitHub::Repository` AWS resource.

## Syntax
Change the values in your AWS CloudFormation template to update github repository as required except RepositoryName and OrganizationOrUserName. Alway have RepositoryName, PersonalAccessToken and OrganizationOrUserName in AWS CloudFormation template as these are required properties. Use the following syntax -


### JSON
File `stack.json` -
```
{
 "Type": "Flux7::GitHub::Repository",
 "Properties": {
   "RepositoryName": String,
   "RepositoryDescription": String,
   "PersonalAccessToken": String,
   "IsPrivate": Boolean,
   "OrganizationOrUserName": String,
   "EnableIssues": Boolean,
   "EnableWiki": Boolean,
   "EnableDownloads": Boolean
 }
}
```

### YAML
File `stack.yaml` -
```
Type: Flux7::GitHub::Repository
Properties:
  RepositoryName: String
  RepositoryDescription: String
  PersonalAccessToken: String
  IsPrivate: Boolean
  OrganizationOrUserName: String
  EnableIssues: Boolean
  EnableWiki: Boolean
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
 
_PersonalAccessToken_

	The GitHub user's personal access token for the GitHub repository.
	Required: Yes
	Type: String
	Update requires: Update with No Interruption
 
_IsPrivate_

	Indicates whether the GitHub repository is a public repository. If so, you choose who can see and commit to this repository.
	Required: No
	Type: Boolean
	Update requires: Update with No Interruption
 
 _OrganizationOrUserName_

	Must be populated with existing Organization Name or the User Name of the owner, in order to create a repo in Organization Account or Personal Account respectively.
	Required: Yes
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

### Example
Content of `stack.yaml`
```
Resources:
  MyGithubRepo:
    Type: Flux7::GitHub::Repository
    Properties:
      RepositoryName: Github-Test
      RepositoryDescription: "Github Test repository"
      PersonalAccessToken: "{{resolve:secretsmanager:<Secret-Manager-Name>:SecretString:<Secret-Key-Name>}}"
      IsPrivate: false
      OrganizationOrUserName: Flux7Labs
      EnableIssues: true
      EnableWiki: false
      EnableDownloads: true
```
Where PersonalAccessToken is stored in Secretsmanager with `Secret-Manager-Name` as secrete manger name and `Secret-Key-Name` as secrete key.

Execute following command to update CloudFormation stack that updates GitHub repository resource with above details.
```
aws cloudformation update-stack --region us-east-1 --template-body "file://stack.yaml" --stack-name "GitHub-Repo-CF-Stack"
```
