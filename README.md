# RabitMQDemo

A demo of rabbit MQ.  The application publishes messages to the exchange "logs" and listens on a queue "spring-retriever-1" that subscribes to
that exchange.  Both the publishing and consuming are currently using AMQ rather than JMS.  I have commented-out code that will write to the
queue using jMS and consume via a standard JMS MessageListener.  But at this point I have not figured out how to get to the publication to work
with JMS.  if I publish with AMQ the JMS consumer cannot process because of the JMS headers.