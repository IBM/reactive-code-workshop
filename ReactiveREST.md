# REST can be Reactive, too

REST interactions have a well-known semantic. So let's start with that.

There are some [containers running in the background](README.md#about-chirper) of the VM that provide a Twitter-like Chirper service, and we'll be using that during this lab to get up to our elbows in code.

To start, we'll need some users! Adding users to the Chirper service is a simple REST call (Java-like pseudo-code here):

      User getUser(String userId);

A User looks like this (JSON):

      {
        "userId": "string1",
        "name": "string2",
        "friends": ["string3"]
      }

So, let us go forth and add some users, in two ways:

* [RxJava]()
* [Akka]()
