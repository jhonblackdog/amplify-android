/*
 * Copyright 2020 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.amplifyframework.datastore;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelProvider;
import com.amplifyframework.util.Immutable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * A simple immutable implementation of the {@link ModelProvider} contract, which
 * will provide the model classes you pass to it, at the version you pass to it.
 */
public final class SimpleModelProvider implements ModelProvider {
    private final String version;
    private final Set<Class<? extends Model>> modelClasses;

    private SimpleModelProvider(String version, Set<Class<? extends Model>> modelClasses) {
        this.version = version;
        this.modelClasses = modelClasses;
    }

    /**
     * Creates a simple model provider. The provider will return the given
     * version and model classes.
     * @param version Version for the new model provider to return
     * @param classes Model classes for the new model provider to return
     * @return A simple model provider, proving the given model classes, at the given version
     */
    @NonNull
    @SafeVarargs
    @SuppressWarnings("varargs")
    public static SimpleModelProvider instance(@NonNull String version, @NonNull Class<? extends Model>... classes) {
        Objects.requireNonNull(version);
        Objects.requireNonNull(classes);
        return new SimpleModelProvider(version, Immutable.of(new HashSet<>(Arrays.asList(classes))));
    }

    /**
     * Creates a simple model provider. The provider will return the given version
     * and model classes.
     * @param version Version of the new model provider to return
     * @param classes The set of model classes that the provider will provide
     * @return A simple model provider, providing the given versions and model classes
     */
    @NonNull
    public static SimpleModelProvider instance(@NonNull String version, @NonNull Set<Class<? extends Model>> classes) {
        Objects.requireNonNull(version);
        Objects.requireNonNull(classes);
        return new SimpleModelProvider(version, classes);
    }

    /**
     * Creates a {@link SimpleModelProvider} which will provide the given model classes.
     * A random version will be used for the provider.
     * @param modelClasses Classes that the provider will provide
     * @return A SimpleModelProvider that provides the given classes at a new, random version
     */
    @NonNull
    @SafeVarargs
    @SuppressWarnings("varargs")
    public static SimpleModelProvider withRandomVersion(@NonNull final Class<? extends Model>... modelClasses) {
        Objects.requireNonNull(modelClasses);
        return SimpleModelProvider.builder()
            .version(UUID.randomUUID().toString())
            .addModels(modelClasses)
            .build();
    }

    @NonNull
    @Override
    public Set<Class<? extends Model>> models() {
        return modelClasses;
    }

    @NonNull
    @Override
    public String version() {
        return version;
    }

    /**
     * Begin building a new SimpleModelProvider.
     * @return A SimpleModelProvider builder
     */
    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(@Nullable Object thatObject) {
        if (this == thatObject) {
            return true;
        }
        if (thatObject == null || getClass() != thatObject.getClass()) {
            return false;
        }

        SimpleModelProvider that = (SimpleModelProvider) thatObject;

        if (!version.equals(that.version)) {
            return false;
        }
        return this.modelClasses.equals(that.modelClasses);
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    @Override
    public int hashCode() {
        int result = version.hashCode();
        result = 31 * result + modelClasses.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "SimpleModelProvider{" +
            "version='" + version + '\'' +
            ", modelClasses=" + modelClasses +
            '}';
    }

    /**
     * Configures and builds instances of SimpleModelProvider.
     */
    static final class Builder {
        private Set<Class<? extends Model>> modelClasses;
        private String version;

        Builder() {
            this.modelClasses = new HashSet<>();
        }

        <T extends Model> Builder addModel(@NonNull Class<T> modelClass) {
            Objects.requireNonNull(modelClass);
            Builder.this.modelClasses.add(modelClass);
            return Builder.this;
        }

        @SafeVarargs
        final Builder addModels(@NonNull Class<? extends Model>... modelClasses) {
            Objects.requireNonNull(modelClasses);
            for (Class<? extends Model> clazz : modelClasses) {
                Objects.requireNonNull(clazz);
                Builder.this.addModel(clazz);
            }
            return Builder.this;
        }

        Builder version(@NonNull String version) {
            Builder.this.version = Objects.requireNonNull(version);
            return Builder.this;
        }

        @SuppressLint("SyntheticAccessor")
        SimpleModelProvider build() {
            return SimpleModelProvider.instance(version, modelClasses);
        }
    }
}
