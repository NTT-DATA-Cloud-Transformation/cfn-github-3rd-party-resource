# Delete GitHub repository
Delete the created github repository using `Flux7::Service::GitHub` AWS resource.

## Command
Following command deletes CloudFormation stack and respective created gitHub repository.
```
aws cloudformation delete-stack --stack-name "GitHub-Repo-CF-Stack"
```
