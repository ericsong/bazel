package(default_visibility = ["//visibility:public"])

py_library(
    name = "lib",
    srcs = ["lib.py"],
)

py_binary(
    name = "bin",
    srcs = ["bin.py"],
    deps = [":lib"],
)

py_binary(
    name = "run_bin",
    srcs = ["run_bin.py"],
    data = [":bin"],
)

py_binary(
    name = "run_bin_2",
    srcs = ["run_bin_2.py"],
    data = [":run_bin"],
)

filegroup(
    name = "srcs",
    srcs = ["BUILD"] + glob(["**/*.py"]),
)
