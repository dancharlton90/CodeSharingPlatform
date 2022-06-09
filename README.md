# CodeSharingPlatform

[Code Sharing Platform](https://hyperskill.org/projects/130) from JetBrains Academy - Challenging Difficulty

[My JetBrainsAcademy Profile](https://hyperskill.org/profile/204045764)

REST API with full intergration of Spring Boot:
- Spring Security (authorising and authenticating) 
- Spring Data (JPA using H2 DB)
- Spring Validation

System to allow for posting of code snippets via website (using FreeMarker as template engine) or REST API

Allows for:
- Posting of code to be permantly stored
- Viewing of code posted by others
- Posting of secret code that exists for a limited amount of views and/or time.
- Secret code can only be accesses via link and is not shown in general viewing lists.
- Secret code is deleted from DB once view/time limit is reached.

Uses:
- Spring Boot
- Spring Data (JPA with H2 database)
- Spring Web
- FreeMarker template engine for webview.
