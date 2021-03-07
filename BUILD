java_library(
    name = "api_gateway_handler",
    srcs = [
        "src/main/java/com/hmellema/lambdatools/handlers/BaseHandler.java",
        "src/main/java/com/hmellema/lambdatools/handlers/services/ApiGatewayRestHandler.java"
    ],
    deps = [
        "@maven//:com_amazonaws_aws_lambda_java_core",
        "@maven//:com_amazonaws_aws_lambda_java_events",
        "@maven//:com_amazonaws_aws_lambda_java_log4j2",
        "@maven//:org_slf4j_slf4j_api",
        "@maven//:org_slf4j_slf4j_log4j12",
        "@maven//:com_google_code_gson_gson"
    ],
    exports = [
        "@maven//:com_amazonaws_aws_lambda_java_core"
    ],
    visibility = ["//visibility:public"]
)

