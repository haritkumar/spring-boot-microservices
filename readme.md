## Microservice Architecture

#### Start the service in below sequence

#### 1. Config Server
`http://localhost:8012/actuator`<br>
`http://localhost:8012/movies-ws/default`<br>
`http://localhost:8012/application/default`

Configuration servers that Spring Cloud supports are the following backends:

- GIT
- Vault
- JDBC

These backends can even be mixed so that, depending on the chosen profile, they will use one or the other.

** Auto refresh config **

Actuator dependency should be added in client's `pom.xml` to use.

```xml
<dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

Include refresh endpoint by adding below entry in `application.properties` or `bootstrap.properties`.

```properties
management.endpoints.web.exposure.include=refresh
```

Call refresh endpoint to reload properties without restarting the application. `http://localhost:8080/actuator/refresh` (Use http post method not get)

**@ConfigurationProperties** - Will reload respective properties with actuator refresh call itself.

**@Value** - Will load properties at startup and will not reload with refresh call. - To reload properties annotated with `@Value` you need to,

Restart the application.

It can also be done without restart just by annotating the class with `@RefreshScope` (spring cloud config annotation) that uses `@Value`.


#### 2. Service Discovery
`http://localhost:8010/`

#### 3. Api Gateway
`http://localhost:8011/movies-ws/movie`


### What's Next ? GraphQL

While REST APIs are serving their purpose well, they suffer few major drawbacks which GraphQL seeks to correct: 

1. Fine-grained control over what you get from the server. In a traditional REST API you might make a call like so: 

```
GET /person/1234
```

And you might get something like:

```
{
   "firstName":"Joe",
   "lastName":"Jones",
   "favorites":[..],
   "friends":[...],
   "prefs":[...],
   ... ---
   ...   |  < a bunch of other data
   ... --- 
}
```

Then if you need to add additional piece of data to your response you just add that and hope to god that no client will decide to break because you added a new property. Moreover, some clients much want only a sliver of this response -- say just the first name of the person -- but you are giving them ALL the data whether they like it or not. Aside from the bandwidth cost, you might also have to make multiple roundrips to your database to fetch all that unnecessary data. Doesn't sound too efficient now does it? 

Enter GraphQL. 

You want the person's name and nothing else. No problem :

```
POST /graphql

{
   getPerson (id:"1234") {
      firstName
      lastName
   }
}
```

Which will give you back something like:

```
  {
     "data": {
        "getPerson": {
           "firstName":"Joe",
           "lastName":"Jones"
        }
     }
  }
```

Now, that's a whole lot better. No extra calls to the database to fetch his `favorites`, his `friends` etc.

2. Versioning

In REST world, when you need to add new properties to your response you can either take the chance of adding them and hoping nothing breaks or you can add `/v2/person/:id` endpoint and add your properties there. The problem is that now you have two endpoints which are semantically very similar -- one being a superset of the other. In GraphQL, since clients are *explicitly* asking for what they want back from the server they are not going to break when you add any new properties.

3. Schema

Because the shape of a GraphQL query closely matches the result, you can predict what the query will return without knowing that much about the server. But it's useful to have an exact description of the data we can ask for - what fields can we select? What kinds of objects might they return? What fields are available on those sub-objects? That's where the schema comes in.

Every GraphQL service defines a set of types which completely describe the set of possible data you can query on that service. Then, when queries come in, they are validated and executed against that schema.

4. Documentation

A nice outcome of having to define your schema is that you sort of get the documentation of your API for free. Client developers can now interrogate your API and find what you have available without you having to explicitly write seperate API docs.

5. Tooling 

Another nice outcome of having a schema is having tooling that lets you visualize your API and interact with it in real-time. One such tool is [GraphiQL](https://github.com/graphql/graphiql) which supports auto-completion and other nifty features.
