#! /usr/bin/env python3

import pika
import re
import json

connection = pika.BlockingConnection(pika.ConnectionParameters(host = 'localhost'))
channel = connection.channel()

def callback(ch, method, properties, body):
    print(" [x] Received %r" % body)
    bodyString = str(body)

    # attempt to get the JSON object from the message, stripping off all the JMS stuff.
    # this works for the message I'm publishing, but seems unlikely to be generic.
    print("bodyString = " + bodyString)
    bodyJson = re.sub(r'^.*{', '{', bodyString)
    bodyJson = re.sub(r'\'$', '', bodyJson)
    print("bodyJson = " + bodyJson)

    # convert the json to a dict.
    message = json.loads(bodyJson)
    print("name = " + message["name"])

    # fail the sucker, to see if it goes to the backout queue.  WHICH IT DOES!
    ch.basic_reject(delivery_tag = method.delivery_tag, requeue=False)

channel.basic_consume(queue='my-topic-subscriber', on_message_callback=callback, auto_ack=False)
print(' [*] Waiting for messages. To exit press CTRL+C')
channel.start_consuming()
