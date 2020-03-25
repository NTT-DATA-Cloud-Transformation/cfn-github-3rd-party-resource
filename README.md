# Resource provider Flux7::GitHub::Repository

## Resource Providers

To use third-party resources in your infrastructure and applications, now you can develop the resource providers for use within CloudFormation. The resource provider includes resource specification and handlers control API interactions with the underlying AWS or third-party services. 

> :bulb: Complete documentation is at @ https://docs.aws.amazon.com/cloudformation-cli/latest/userguide/resource-types.html

## Repository Structure
```
.
├── doc_source
├── flux7-github-repository.json
├── pom.xml
├── README.md
└── src
    ├── main/java/com/flux7/github/repository
    └── test/java/com/flux7/github/repository

```
- **flux7-github-repository.json:**
    It's resource provider schema file, it includes model of resource provider.
    
- **pom.xml:**
    This file contains information about the project and configuration details used by Maven to build the project.

- **README.md:**
    This file has information about other files in the repository.

- **doc_source:**
    This directory contains documentation about How to use `Flux7::GitHub::Repository` custom resource.

- **src/main/java/com/flux7/github/repository:**
    This directory contains files (CallbackContext, handlers) that actually implement the resource's functionality.

- **src/test/java/com/flux7/github/repository:**
    This directory contains files that performs unit tests for handlers.

