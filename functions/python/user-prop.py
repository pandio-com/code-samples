#!/usr/bin/env python

from pulsar import Function

class UserConfigFunction(Function):
    def process(self, input, context):
        logger = context.get_logger()
        wotd = context.get_user_config_value('word-of-the-day')
        if wotd is None:
            logger.warn('No word of the day provided')
        else:
            logger.info("The word of the day is {0}".format(wotd))