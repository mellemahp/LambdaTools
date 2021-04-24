package com.hmellema.lambdatools.handlers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;

import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

import java.util.Map;
import java.util.Optional;

public abstract class BaseHandler<InputType, OutputType, RequestEventType, ResponseEventType> 
    implements RequestHandler<RequestEventType, ResponseEventType>
{
    private static String LOG_ENV_VAR_NAME = "LOG_LEVEL";
    private static String DEFAULT_LOG_LEVEL = "DEBUG";

    protected final Logger log;
    protected final Class<InputType> inputTypeClass;
    protected final Gson objectMapper = new Gson();
    
    protected BaseHandler(Class<InputType> inputTypeClass) {
        log = (Logger) LoggerFactory.getLogger(this.getClass());
        setLoggingLevelFromEnv(log);

        this.inputTypeClass = inputTypeClass;
    }

    // Sets logging level based on environment variable
    // Defaults to DEFAULT LEVEL of log level
    private void setLoggingLevelFromEnv(Logger logger) {
        String envLogLevel = Optional.ofNullable(System.getenv(LOG_ENV_VAR_NAME))
            .orElse(DEFAULT_LOG_LEVEL);
        logger.setLevel(Level.toLevel(envLogLevel));
    }

    // see https://stackoverflow.com/questions/14139437/java-type-generic-as-argument-for-gson
    protected InputType convertToInputType(Map<String, String> inputMap) {
        String jsonString = objectMapper.toJson(inputMap);
        Type inputTypeToken = TypeToken.getParameterized(inputTypeClass).getType();

        return objectMapper.fromJson(jsonString, inputTypeToken);
    }

    protected abstract OutputType handler(
        InputType request,
        Context context
    );
}
