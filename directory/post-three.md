# Giving 1% more concentrated effort over a period of time

The math is there, with sustained effort, results become apparent. I'm using this blog to serve as a measure of my knowledge in java development. I'm hoping that at some point this might help others on their journey.

Today, during a pairing session, I had forgotten how to kill the process that is on a certain port. It was kind of agitating but it served as a reminder that we should invite such an experience to be something of good use, something that will serve to increase your understanding of a specific domain of choice given that you are deliberate about it.

Killing the process on port :8080

lsof -i :8080

this will print out the PID which you will then proceed to kill

kill -9 PID

This might feel awkward if you haven't done this stuff before, but worry not, developing an understanding of what the port is and why you need to kill it is important and an early lesson that benefits even experienced developers to revisit. 

This is a quick and simple one for today and remember, as long as you do 1% of concentrated effort, you'll see the results in the long run.

Cheers,
Alvin

