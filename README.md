# iam-tools
Simple REST-based Role Based Access Control system written in Spring Boot and deployed on Kubernetes through Helm.

## API Definition

### POST `/iam/createAccount`

Creates an account in the system.

#### Request Body Parameters

* `fullName`: Your full name for identification purposes.
* `shortname`: This will serve as your username. Must be at least 6 characters long and must be any number of lowercase letters followed by any number of digits, ex. zawada1 is valid but andy-zawada is not.
* `email`: Your email address.
* `password`: Your password for the API.

___note:___ This is just hosted for demonstration purposes and is not guaranteed to be secure. Please refrain from using sensitive information in these fields.

#### Response Codes
* `200 OK`: The account is created, no response body will be returned.
* `400 BAD REQUEST`: The username does not meet length or composition requirements, already exists, or the password is a zero-length string.

### POST `/iam/addRole`

Adds a role to the user specified. The requesting shortname must be the owner of the role to make the request.

#### Request Body Parameters

* `user`: The account of the user the requestor intends to modify.
* `roleName`: The role the requestor wishes to add the user to.
* `shortname`: The requestor's shortname.
* `password`: The requestor's password.

#### Response Codes
* `200 OK`: The role has been added to the user, no response body will be returned.
* `400 BAD REQUEST`: The user or the role specified do not exist.
* `401 UNAUTHORIZED`: The requestors credentials are invalid.
* `403 FORBIDDEN`: The requestor is not an owner of the role specified.

### POST `/iam/removeRole`

Removes a role from the user specified. The requesting shortname must be the owner of the role to make the request.

#### Request Body Parameters

* `user`: The account of the user the requestor intends to modify.
* `roleName`: The role the requestor wishes to remove the user from.
* `shortname`: The requestor's shortname.
* `password`: The requestor's password.

#### Response Codes
* `200 OK`: The role has been removed from the user, no response body will be returned.
* `400 BAD REQUEST`: The user or the role specified do not exist.
* `401 UNAUTHORIZED`: The requestors credentials are invalid.
* `403 FORBIDDEN`: The requestor is not an owner of the role specified.

### POST `/iam/createRole`

Creates a role in the system. The requesting shortname will become the owner of this new role.

#### Request Body Parameters

* `roleName`: The name of the role to be created. Th
* `description`: The plaintext description of what the group is for.
* `shortname`: The requestor's shortname.
* `password`: The requestor's password.

#### Response Codes
* `200 OK`: The role has been added created, no response body will be returned.
* `400 BAD REQUEST`: The role name is empty, or does not meet kebab-case specification 
* `401 UNAUTHORIZED`: The requestors credentials are invalid.

### POST `/iam/deleteRole`

### GET `/iam/authenticate`

### GET `/iam/getMembers`

### GET `/iam/getAllRoles`

### GET `/iam/getRoles`
