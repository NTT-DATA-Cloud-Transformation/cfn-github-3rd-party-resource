# Testing Framework

In order to perform unit tests the following command must be run.

```
$ mvn test -DRepositoryName="<repository_name>" -DAccessToken="<access_token>" -DOrganizationOrUserName="<organization_name / user_name>"
```

###### Note: The tests will be skipped while packaging. They will only run when the command mentioned above is run with the appropriate working properties are provided.