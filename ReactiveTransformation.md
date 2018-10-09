# Reactive Transformation

You've now seen that you can make a RESTful method request using some Reactive method calls, but.. why? What did we achieve that was better than pick-your-favorite-REST-library?

Let's poke a stick at what we can do now.

With the previous exercise, we created not one, but at least two sources of more or less endless chirps!  And if we get those running in their own terminal windows, they'll continue to spew chirps, which will be very useful as we explore how to use reactive techniques to consume and transform incoming events.

Now, these chirps are a proper stream! We can't use REST anymore. For this lab, we'll be using WebSockets to create a connection that will remain open to receive incoming chirps.

We'll be using an endpoint like this: `/api/activity/:userId/live` which will return a stream of Chirps that look like this:

        {
          "userId": "string1",
          "message": "string2",
          "timestamp": timestamp,
          "uuid": "uuidstring"
        }

So, let us go forth and mangle chirps, in two ways:

* [RxJava](https://github.com/ebullient/rxjava2-chirper-client#reactive-transformation)
* [Akka](akka/ReactiveTransformation.md)

Back to [Reactive Events](ReactiveEventSource.md)  
Onward to [Coda](README.md#in-summary)
