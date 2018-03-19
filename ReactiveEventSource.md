# Reactive Event Source

You've now seen that you can make a RESTful method request using some Reactive method calls, but.. why? What did we achieve that was better than pick-your-favorite-REST-library?

Let's poke a stick at what we can do now.

With the previous exercise, we registered users to the Chirper service in both RxJava and Akka. What we need next is.. chirps!

Adding a chirp is still a RESTful exercise:

        @POST("/api/chirps/live/{id}")
        addChirp(@Path("id") String id, Chirp chirp)

Where a Chirp looks like this:

        {
          "userId": "string1",
          "message": "string2",
          "timestamp": timestamp,
          "uuid": "uuidstring"
        }

The timestamp and UUID are optional, and will be supplied by the server if we don't provide them (which sounds like a fine idea).

So, let us go forth and chirp many times, in two ways:

* [RxJava](https://github.com/ebullient/rxjava2-chirper-client#reactive-transformation)
* [Akka]()

Back to [REST can be reactive, too](ReactiveREST.md) 
Onward to [Reactive Operations](ReactiveTransformation.md)
