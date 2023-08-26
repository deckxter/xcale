Dependencies:

- OpenJDK 17.0.8
- Gradle 8.2 / Kotlin (Wrapper)
- DB in memory / H2
- Spring Boot Web (v3.1.2)
- Spring Boot Data JPA (v3.1.2)

To run the app, execute:


./gradlew bootRun


Endpoints available are attached as a Postman Collection in the email

For the requirement to disable a cart after certain time of inactivity
there is a property:

- cart.ttl=0 * * * * *

It's using a format for spring scheduled tab, not the format for crontab.

Now is configured to execute the task every minute.
We are using an interceptor "CartInterceptor", so, after execute any call to the backend 
with the PATH of a "cart" the interceptor should be executed.
If there is an "id" to check, then the "TaskSchedulingService" is called
and a "DeleteCartTaskDefinition" would be scheduled according to the time configured above.

* Pending to develop cases for disable the task, and to schedule the task after the creation of the cart.