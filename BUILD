java_library(
    name = "lambda_logging_deps",
    exports = [
        "@maven//:org_slf4j_slf4j_api"
    ]
)

java_library(
    name = "runtime_logging_deps",
    exports = [
        "@maven//:org_jlib_jlib_awslambda_logback"
    ],
    visibility = ["//visibility:public"]
)

filegroup(
    name = "logging_config",
    srcs = ["resources/logback.xml"],
    visibility = ["//visibility:public"]
)

java_library(
    name = "lambda_core_deps",
    exports = [
        "@maven//:com_amazonaws_aws_lambda_java_core",
        "@maven//:com_amazonaws_aws_lambda_java_events",
    ]
)

java_library(
    name = "api_gateway_handler",
    srcs = [
        "src/main/java/com/hmellema/lambdatools/handlers/BaseHandler.java",
        "src/main/java/com/hmellema/lambdatools/handlers/services/ApiGatewayRestHandler.java"
    ],
    deps = [
        ":lambda_logging_deps",
        ":lambda_core_deps",
        "@maven//:com_google_code_gson_gson"
    ],
    exports = [
        "@maven//:com_amazonaws_aws_lambda_java_core",
        ":lambda_logging_deps"
    ],
    visibility = ["//visibility:public"]
)

