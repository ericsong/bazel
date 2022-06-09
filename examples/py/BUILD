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
    name = "grandchild",
    srcs = ["grandchild.py"],
)

py_binary(
    name = "child",
    srcs = ["child.py"],
    data = [":grandchild"],
)

py_binary(
    name = "parent",
    srcs = ["parent.py"],
    data = [":child"],
)

filegroup(
    name = "srcs",
    srcs = ["BUILD"] + glob(["**/*.py"]),
)
