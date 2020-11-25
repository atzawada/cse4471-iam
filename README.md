# iam-tools
Simple REST-based Role Based Access Control system written in Spring Boot and deployed on Kubernetes through Helm.

## Using the API

The API can be accessed via [Postman](https://www.postman.com/). The environment and collection files can be found in the `postman` folder. In the Postman client click `Import` at the top left corner of the screen and select all the files in that directory. Then select your environment in the top right corner in the screen and set it to `iam-tools beta`. From there you can now run the requests in the `iam-tools` collection. You can modify the parameters by going to `body` for the POST requests or `Params` for the GET requests.

The beta base URL is https://server.atzawada.io:8888

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

---

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

---

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

---

### POST `/iam/createRole`

Creates a role in the system. The requesting shortname will become the owner of this new role.

#### Request Body Parameters

* `roleName`: The name of the role to be created. must be specified in kebab-case and be all lowercase letters ex application-group is permissible but APPLICATION_GROUP is not.
* `description`: The plaintext description of what the group is for.
* `shortname`: The requestor's shortname.
* `password`: The requestor's password.

#### Response Codes

* `200 OK`: The role has been added created, no response body will be returned.
* `400 BAD REQUEST`: The role name is empty, or does not meet kebab-case specification. 
* `401 UNAUTHORIZED`: The requestors credentials are invalid.

---

### POST `/iam/deleteRole`

Removes a role from the system. 

#### Request Body Parameters

* `roleName`: The name of the role to be deleted.
* `shortname`: The requestor's shortname.
* `password`: The requestor's password.

#### Response Codes

* `200 OK`: The role has been added created, no response body will be returned.
* `400 BAD REQUEST`: The role does not exist. 
* `401 UNAUTHORIZED`: The requestors credentials are invalid.
* `403 FORBIDDEN`: The requestor is not the owner of the role to be deleted.

---

### GET `/iam/authenticate`

Checks to see if a user is a member of a role.

#### Request Query Parameters

* `user`: The name of user to check against.
* `roleName`: The name of the role to check against.
* `shortname`: The requestor's shortname.
* `password`: The requestor's password.

#### Response Codes

* `200 OK`: The request has processed successfully. Returns a boolean value for the result of the request.
* `400 BAD REQUEST`: The role or user does not exist. 
* `401 UNAUTHORIZED`: The requestors credentials are invalid.

---

### GET `/iam/getMembers`

Retrieves the list of users that are members of a role.

#### Request Query Parameters

* `roleName`: The name of the role to check against.
* `shortname`: The requestor's shortname.
* `password`: The requestor's password.

#### Response Codes

* `200 OK`: The request has processed successfully. The response will contain the shortname of the owner and a list of shortnames of all the members:
```json
{
    "owner": "",
    "users": []
}
```
* `400 BAD REQUEST`: The role does not exist. 
* `401 UNAUTHORIZED`: The requestors credentials are invalid.

---

### GET `/iam/getAllRoles`

Gets a list of all roles and their owners.

#### Request Query Parameters

* `shortname`: The requestor's shortname.
* `password`: The requestor's password.

#### Response Codes

* `200 OK`: The request has processed successfully. The response will contain an object that contains KV pairs of shortnames and owners:
```json
{
    "role-one": "owner1",
    "role-two": "owner2"
}
```
* `401 UNAUTHORIZED`: The requestors credentials are invalid.

---

### GET `/iam/getRoles`

Gets a list of role memberships for a user.

#### Request Query Parameters

* `roleName`: The name of the role to check against.
* `shortname`: The requestor's shortname.
* `password`: The requestor's password.

#### Response Codes

* `200 OK`: The request has processed successfully. The response will contain the shortname of the user and an object containing KV pairs of their roles and the role owners:
```json
{   "roles": {
        "role-one": "owner1",
        "role-two": "owner2"
    },
    "user":""
}
```
* `400 BAD REQUEST`: The user does not exist. 
* `401 UNAUTHORIZED`: The requestors credentials are invalid.
