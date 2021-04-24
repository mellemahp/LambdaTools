package com.hmellema.lambdatools.handlers.services;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent.ProxyRequestContext;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.amazonaws.services.lambda.runtime.Context;

import org.slf4j.MDC;

import java.util.HashMap;
import java.util.Map;

import com.hmellema.lambdatools.handlers.BaseHandler;

public abstract class ApiGatewayRestHandler<InputType, OutputType>
  extends BaseHandler<
    InputType, OutputType,
    APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent
  > 
{

  protected ApiGatewayRestHandler(Class<InputType> inputTypeClass) {
    super(inputTypeClass);
  }

  private APIGatewayProxyResponseEvent initializeResponse(Context context) {
    HashMap<String, String> headers = new HashMap<>();
    headers.put("Content-Type", "application/json");
    headers.put("x-OriginalAwsRequestId", context.getAwsRequestId());

    APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
    response.setIsBase64Encoded(false);
    response.setHeaders(headers);

    return response;
  }

  private void setLogParameters(APIGatewayProxyRequestEvent event) {
    ProxyRequestContext requestContext = event.getRequestContext();
    MDC.put("restRoute", requestContext.getPath());
    MDC.put("httpMethod", requestContext.getHttpMethod());
    MDC.put("restApiId", requestContext.getApiId());
  }

  @Override
  public APIGatewayProxyResponseEvent handleRequest(
    APIGatewayProxyRequestEvent event,
    Context context
  ) {
    setLogParameters(event);
    APIGatewayProxyResponseEvent response = initializeResponse(context);

    Map<String, String> requestBody = constructInputMap(event);
    InputType inputTypeRequest = convertToInputType(requestBody);
    OutputType result = handler(inputTypeRequest, context);

    String jsonString = objectMapper.toJson(result);
    response.setBody(jsonString);
    response.setStatusCode(200);
    return response;
  }

  private Map<String, String> constructInputMap(APIGatewayProxyRequestEvent request) {
    Map<String, String> inputMap = new HashMap<>();

    if (request.getQueryStringParameters() != null) {
      inputMap.putAll(request.getQueryStringParameters());
    }
    
    if (request.getPathParameters() != null) {
      inputMap.putAll(request.getPathParameters());
    }
    if (request.getBody() != null) {
      Map<String, String>bodyMap = objectMapper.fromJson(request.getBody(), Map.class);
      inputMap.putAll(bodyMap);
    }

    return inputMap;
  }
}
