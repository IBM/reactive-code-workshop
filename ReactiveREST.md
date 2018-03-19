# REST can be Reactive, too

REST interactions have a well-known semantic. So let's start with that.

There are some [containers running in the background](README.md#about-chirper) of the VM that provide a Twitter-like Chirper service, and we'll be using that during this lab to get up to our elbows in code.

To start, we'll need some users! Adding users to the Chirper service is a simple REST call (Java-like pseudo-code here):

      @GET("/api/users/{id}")
      User getUser(String userId);

      @POST("/api/users")
      void createUser(User user);

A User looks like this (JSON):

      {
        "userId": "string1",
        "name": "string2",
        "friends": ["string3"]
      }

We also need to add friends, like so:

      @POST("/api/users/{id}/friends")
      void addFriend(String userId);

So, let us go forth and add some users (and their friends), in two ways:

* [RxJava](https://github.com/ebullient/rxjava2-chirper-client#rest-can-be-reactive-too)
* [Akka](akka/ReactiveREST.md)

Back to [the top](README.md)  
Onward to [Reactive Events](ReactiveEventSource.md)
