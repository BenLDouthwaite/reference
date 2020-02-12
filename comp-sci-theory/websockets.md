# Websockets

* Allow 2 way comms between browser and server. 
* Event driven response without polling
* Sits on the Application layer
* Only send headers once
  * Brings perf benefits
* Uses the same tcp connection over ws:// or wss://
* Now widely supported
* Can't communicate with rest
* Web balancing / scaling can be difficult