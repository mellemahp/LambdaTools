# import libraries from maven
load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

RULES_JVM_EXTERNAL_TAG = "2.8"
RULES_JVM_EXTERNAL_SHA = "79c9850690d7614ecdb72d68394f994fef7534b292c4867ce5e7dec0aa7bdfad"

http_archive(
    name = "rules_jvm_external",
    strip_prefix = "rules_jvm_external-%s" % RULES_JVM_EXTERNAL_TAG,
    sha256 = RULES_JVM_EXTERNAL_SHA,
    url = "https://github.com/bazelbuild/rules_jvm_external/archive/%s.zip" % RULES_JVM_EXTERNAL_TAG,
)

load("@rules_jvm_external//:defs.bzl", "maven_install")

maven_install(
    artifacts = [
        # common dependencies
        "com.google.code.gson:gson:2.8.6",
        # aws lambda dependencies
        "com.amazonaws:aws-lambda-java-core:1.2.1",
        "com.amazonaws:aws-lambda-java-events:3.7.0",
        "com.amazonaws:aws-lambda-java-log4j2:1.2.0",
        # logging
        "org.apache.logging.log4j:log4j-api:2.13.0",
        "org.apache.logging.log4j:log4j-core:2.13.2",
        "org.apache.logging.log4j:log4j-slf4j18-impl:2.13.0",
        # test deps
        "org.mockito:mockito-all:1.10.19",
        "junit:junit:4.12",
    ],
    repositories = [
        "https://repo1.maven.org/maven2",
    ],
)