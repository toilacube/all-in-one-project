# A RestAPI back-end service for an e-commerce website (Coolmate)

## Table of contents:
- [ Hightlight Features](#head1)
- [ To-do](#head2)
- [ Database Schema](#head3)
- [ How i Dockerize this project and local database](#head4)

<a id="head1"></a>
## Hightlight Features:
- Apply Servlet Authentication Architecture to do Authentication and Authorization with Jwt and Google OAuth.
- Basic unit test using JUnit and Mockito (for repository and service layer).
- Dockerize the project and the database.
- InMemoryCache using Caffeine to store Products listing.

<a id="head2"></a>
## To-do:
- [ ] Idempotent
- [ ] Database pooling
- [ ] Debounce and throttle
- [ ] Cloud
- [ ] Exception, error, auth handler,...

<a id="head3"></a>
## Database Schema

![coolmate_diagram.png](coolmate_diagram.png)

<a id="head4"></a>
## [How i Dockerize this project and local database](https://toilacube.hashnode.dev/i-should-have-learned-docker-earlier)