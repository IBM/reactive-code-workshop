# The Chirper Client

## Description 
The Chirper client is an example of using Akka Http to access 
a RESTful web service using REST semantics to interact with it. 

As with all things Lightbend, Akka Http is Reactive, non-blocking, completely
asynchronous. 

[*note: if you want to read all about akka http, great documentation can be found here - https://doc.akka.io/docs/akka-http/current/*]

For this exercise, we are going to be using a couple of concepts.
The class we referenced in this exercise is `demo.ChirperClientSimulator`
In order to demonstrate those concepts, we're going to do the following:

1) Inspect the HTTP get helper method to understand how to build and execute HTTP requests in Akka Http
2) Complete an Http post helper method building upon the http get knowledge
3) Investigate using transformation flows with Akka Http responses

Before we begin, give the class a try by typing run
Expect to see some errors...

The errors you see are because we've not yet added any uses and the 
main is trying to get users before they are added. 

Let's fix adding users, but first, about building requests.

### Step 0

Open VS Code  
Find the `akka-streams-chirper-client` folder

Open a Terminal window  

        $ cd ~/akka-streams-chirper-client
        $ sbt 

### Step 1.a - building http requests

Locate the method 

        private CompletionStage<HttpResponse> get(String uri)

inside of our exercise class. You will see some information about this method. 
Important to note is is that this builds an asynchronous and non-blocking request

        final HttpRequest request = HttpRequest.create()
                        .withMethod(HttpMethods.GET())
                        .withUri(uri);

Take a look at the elements and think about how similar it may be to other tools you've used in the past. 
Also look at the differences. 

### Step 1.b - building post request

Locate the method 

        private CompletionStage<HttpResponse> post(String uri, Object entity)  
        
You will see some instructions in the doc on how to update the method to complete it.   You'll need to use the entity object to help you build the request. *hint, the builder has a withEntity method you should use*

### Step 1.c - get user flows

Locate the method: 

        private void getUser(String userId, Graph<SinkShape<Entity.User>, ?> userSink)

You can see this method is a bit more involved. It puts together a number of flows in order to transform the http response. 

Take a look at the flows represented. Try adding your own transforming flow.

Don't worry if you can't think of another flow now. After class, you can play around with this. 

For now, try running the exercise.

**The exercise is still not finished!**

In order for everything to run correctly, you'll need to go back to the main 
method and uncomment the code that is commented. 

After this, you should see everything running just fine. 

In order to stop the main, press any key. 

**And that's it! Akka Http client with streams**

**Keep this terminal window open for the next exercise.** Create a new Terminal window for RxJava part 2.

Back to [the top](README.md)  
Onward to [Reactive Events](ReactiveEventSource.md)
