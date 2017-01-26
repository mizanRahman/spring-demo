/**
 * Created by mac on 2/11/16.
 */


import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.FileAppender

import static ch.qos.logback.classic.Level.DEBUG
import static ch.qos.logback.classic.Level.INFO


scan('5 minutes')
setupAppenders()
setupLoggers()

def setupAppenders() {
    def logfileDate = timestamp('yyyy-MM-dd')
    // hostname is a binding variable injected by Logback.
    def filePatternFormat = "%d{HH:mm:ss.SSS} %-5level [${hostname}] %logger - %msg%n"

    //TODO: file logging is not working...need to fix
    appender('logfile', FileAppender) {
        file = "simple.${logfileDate}.log"
        encoder(PatternLayoutEncoder) {
            pattern = filePatternFormat
        }
    }

    appender("FILE", FileAppender) {
        file = "testFile.log"
        append = true
        encoder(PatternLayoutEncoder) {
            pattern = "%level %logger - %msg%n"
        }
    }

    appender('systemOut', ConsoleAppender) {
        encoder(PatternLayoutEncoder) {
            pattern = filePatternFormat
        }
    }
}

def setupLoggers() {
    logger 'com.example', DEBUG, ['FILE']
    root DEBUG, ['systemOut']
}

def getLogLevel() {
    (isDevelopmentEnv() ? DEBUG : INFO)
}

def isDevelopmentEnv() {
    def env =  System.properties['app.env'] ?: 'DEV'
    env == 'DEV'
}