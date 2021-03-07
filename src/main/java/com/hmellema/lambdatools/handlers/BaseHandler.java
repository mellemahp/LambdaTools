package com.hmellema.lambdatools.handlers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public abstract class BaseHandler<InputType, OutputType, RequestEventType, ResponseEventType> 
    implements RequestHandler<RequestEventType, ResponseEventType>
{
    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    protected final Gson objectMapper = new Gson();
    protected final Class<InputType> inputTypeClass;

    protected BaseHandler(Class<InputType> inputTypeClass) { 
        this.inputTypeClass = inputTypeClass;
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
