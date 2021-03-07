java_library(
    name = "lambda_logging_deps",
    exports = [
        "@maven//:com_amazonaws_aws_lambda_java_log4j2",
        "@maven//:org_apache_logging_log4j_log4j_api",
        "@maven//:org_apache_logging_log4j_log4j_core",
        "@maven//:org_apache_logging_log4j_log4j_slf4j18_impl",
        "@maven//:org_slf4j_slf4j_api"
    ],
)

java_library(
    name = "lambda_core_deps",
    exports = [
        "@maven//:com_amazonaws_aws_lambda_java_core",
        "@maven//:com_amazonaws_aws_lambda_java_events",
    ],
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

