<!--
*** Thanks for checking out the Best-README-Template. If you have a suggestion
*** that would make this better, please fork the repo and create a pull request
*** or simply open an issue with the tag "enhancement".
*** Thanks again! Now go create something AMAZING! :D
-->



<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]


<!-- GETTING STARTED -->
## Getting Started

This is an example of how you may give instructions on setting up your project locally.
To get a local copy up and running follow these simple example steps.

### Prerequisites

This is an example of how to list things you need to use the software and how to install them.
* Eclipse
* Maven
* Java 8

### Start the application

1. Clone the repo
   ```sh
   git clone https://github.com/shravan-murachpersad/mcshares.git
   ```
2. Open the project in eclipse and update the maven project to install the dependencies.

<!-- USAGE EXAMPLES -->
## Api Usage

### Access the api documentation.

To to the following url to access the full api documentation.

```txt
http://localhost:8080/
```

### Token Request

A bearer token is required to use the api.

#### Request

```txt
GET /public/token
Body
username: test
password: 1234
```