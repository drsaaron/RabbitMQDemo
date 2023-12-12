# RabitMQDemo

A demo of rabbit MQ using JMS.  The application publishes messages to the topic "my-logs".  (On the rabbit site, this uses the jms.durable.topic exchange
with a binding to the subscriber queue with routing key "my-logs".)  A JMS message listener listens on queue "spring-retriever-1" to process
the messages.
