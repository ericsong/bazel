// Copyright 2017 The Bazel Authors. All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.devtools.build.lib.rules.cpp;

import com.google.common.collect.ImmutableSet;
import com.google.devtools.build.lib.analysis.RuleContext;
import com.google.devtools.build.lib.analysis.RuleErrorConsumer;
import com.google.devtools.build.lib.analysis.TransitiveInfoCollection;
import com.google.devtools.build.lib.analysis.config.BuildConfigurationValue;
import com.google.devtools.build.lib.concurrent.ThreadSafety.Immutable;
import com.google.devtools.build.lib.packages.AspectDescriptor;
import com.google.devtools.build.lib.packages.StructImpl;
import com.google.devtools.build.lib.rules.cpp.CcToolchainFeatures.FeatureConfiguration;
import com.google.devtools.build.lib.rules.cpp.CppConfiguration.HeadersCheckingMode;
import com.google.devtools.build.lib.skyframe.serialization.autocodec.SerializationConstant;

/**
 * Null-object like {@link CppSemantics} implementation. Only to be used in tests that don't depend
 * on semantics details.
 */
@Immutable
public final class MockCppSemantics implements CppSemantics {
  @SerializationConstant public static final MockCppSemantics INSTANCE = new MockCppSemantics();

  private MockCppSemantics() {}

  @Override
  public void finalizeCompileActionBuilder(
      BuildConfigurationValue configuration,
      FeatureConfiguration featureConfiguration,
      CppCompileActionBuilder actionBuilder,
      RuleErrorConsumer ruleErrorConsumer) {}

  @Override
  public boolean allowIncludeScanning() {
    return false;
  }

  @Override
  public HeadersCheckingMode determineHeadersCheckingMode(RuleContext ruleContext) {
    return HeadersCheckingMode.LOOSE;
  }

  @Override
  public HeadersCheckingMode determineStarlarkHeadersCheckingMode(
      RuleContext context, CppConfiguration cppConfig, CcToolchainProvider toolchain) {
    return HeadersCheckingMode.LOOSE;
  }

  @Override
  public boolean needsDotdInputPruning(BuildConfigurationValue configuration) {
    return true;
  }

  @Override
  public void validateAttributes(RuleContext ruleContext) {}

  @Override
  public boolean needsIncludeValidation() {
    return true;
  }

  @Override
  public StructImpl getCcSharedLibraryInfo(TransitiveInfoCollection dep) {
    return null;
  }

  @Override
  public void validateLayeringCheckFeatures(
      RuleContext ruleContext,
      AspectDescriptor aspectDescriptor,
      CcToolchainProvider ccToolchain,
      ImmutableSet<String> unsupportedFeatures) {}

  @Override
  public boolean createEmptyArchive() {
    return false;
  }

  @Override
  public boolean shouldUseInterfaceDepsBehavior(RuleContext ruleContext) {
    boolean experimentalCcInterfaceDeps =
        ruleContext.getFragment(CppConfiguration.class).experimentalCcInterfaceDeps();
    if (!experimentalCcInterfaceDeps
        && ruleContext.attributes().isAttributeValueExplicitlySpecified("interface_deps")) {
      ruleContext.attributeError("interface_deps", "requires --experimental_cc_interface_deps");
    }
    return experimentalCcInterfaceDeps;
  }
}
