#!/usr/bin/env python

from pulsar import Function, SerDe

class MyObject(object):
  def __init__(self):
    self.a = 0
    self.b = 0

class CustomSerDe(SerDe):
  def __init__(self):
    pass

  def serialize(self, object):
    return ("%d,%d" % (object.a, object.b)).encode('utf-8')

  def deserialize(self, input_bytes):
    split = str(input_bytes.decode()).split(',')
    retval = MyObject()
    retval.a = int(split[0])
    retval.b = int(split[1])
    return retval

# Function that deals with custom objects
class CustomObjectFunction(Function):
  def __init__(self):
    pass

  def process(self, input, context):
    retval = MyObject()
    retval.a = input.a + 11
    retval.b = input.b + 24
    return retval
