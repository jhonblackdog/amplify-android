/*
 * Copyright 2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.
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

package com.amplifyframework.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.amplifyframework.api.graphql.GraphQLOperation;
import com.amplifyframework.api.graphql.GraphQLRequest;
import com.amplifyframework.api.graphql.GraphQLResponse;
import com.amplifyframework.api.graphql.MutationType;
import com.amplifyframework.api.graphql.SubscriptionType;
import com.amplifyframework.core.Action;
import com.amplifyframework.core.Consumer;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.query.predicate.QueryPredicate;

/**
 * GraphQL behaviors include varying approaches to perform the query,
 * subscribe and mutate GraphQL operations.
 */
public interface GraphQlBehavior {

    /**
     * This is a special helper method for easily calling a list query for
     * all items of the specified model type with no filtering condition.
     *
     * Perform a GraphQL query against a configured GraphQL endpoint.
     * This operation is asynchronous and may be canceled by calling
     * cancel on the returned operation. The response will be provided
     * to the `onResponse` callback. If there is data present
     * in the response, it will be cast as the requested class type.
     * Requires that only one API is configured in your
     * `amplifyconfiguration.json`. Otherwise, emits an ApiException to
     * the provided `onFailure` callback.
     * @param modelClass The class of the Model we are querying on
     * @param onResponse Invoked when a response is available; may contain errors from endpoint
     * @param onFailure Invoked when a response is not available due to operational failures
     * @param <T> The type of data in the response, if available. Must extend Model.
     * @return An {@link ApiOperation} to track progress and provide
     *         a means to cancel the asynchronous operation
     */
    @Nullable
    <T extends Model> GraphQLOperation<T> query(
            @NonNull Class<T> modelClass,
            @NonNull Consumer<GraphQLResponse<Iterable<T>>> onResponse,
            @NonNull Consumer<ApiException> onFailure
    );

    /**
     * This is a special helper method for easily performing GET Queries
     * on Model objects which are autogenerated from your schema.
     *
     * Perform a GraphQL query against a configured GraphQL endpoint.
     * This operation is asynchronous and may be canceled by calling
     * cancel on the returned operation. The response will be provided to
     * the `onResponse` callback. If there is data
     * present in the response, it will be cast as the requested class type.
     * Requires that only one API is configured in your
     * `amplifyconfiguration.json`. Otherwise, emits an ApiException to
     * the provided `onFailure` callback.
     * @param modelClass The class of the Model we are querying on
     * @param objectId The unique ID of the object you want to get
     * @param onResponse Invoked when a response is available; may contain errors from endpoint
     * @param onFailure Invoked when a response is not available due to operational failures
     * @param <T> The type of data in the response, if available. Must extend Model.
     * @return An {@link ApiOperation} to track progress and provide
     *         a means to cancel the asynchronous operation
     */
    @Nullable
    <T extends Model> GraphQLOperation<T> query(
            @NonNull Class<T> modelClass,
            @NonNull String objectId,
            @NonNull Consumer<GraphQLResponse<T>> onResponse,
            @NonNull Consumer<ApiException> onFailure
    );

    /**
     * This is a special helper method for easily performing LIST Queries
     * on Model objects which are autogenerated from your schema.
     *
     * Perform a GraphQL query against a configured GraphQL endpoint.
     * This operation is asynchronous and may be canceled by calling
     * cancel on the returned operation. The response will be provided
     * to the `onResponse` callback.  If there is data present
     * in the response, it will be cast as the requested class type.
     * Requires that only one API is configured in your
     * `amplifyconfiguration.json`. Otherwise, emits an ApiException to
     * the provided `onFailure` callback.
     * @param modelClass The class of the Model we are querying on
     * @param predicate Filtering conditions for the query
     * @param onResponse Invoked when a response is available; may contain errors from endpoint
     * @param onFailure Invoked when a response is not available due to operational failures
     * @param <T> The type of data in the response, if available. Must extend Model.
     * @return An {@link ApiOperation} to track progress and provide
     *         a means to cancel the asynchronous operation
     */
    @Nullable
    <T extends Model> GraphQLOperation<T> query(
            @NonNull Class<T> modelClass,
            @NonNull QueryPredicate predicate,
            @NonNull Consumer<GraphQLResponse<Iterable<T>>> onResponse,
            @NonNull Consumer<ApiException> onFailure
    );

    /**
     * Perform a GraphQL query against a configured GraphQL endpoint.
     * This operation is asynchronous and may be canceled by calling
     * cancel on the returned operation. The response will be provided
     * to the `onResponse` callback.  If there is data present
     * in the response, it will be cast as the requested class type.
     * Requires that only one API is configured in your
     * `amplifyconfiguration.json`. Otherwise, emits an ApiException to
     * the provided `onFailure` callback.
     * @param graphQlRequest Wrapper for request details
     * @param onResponse Invoked when a response is available; may contain errors from endpoint
     * @param onFailure Invoked when a response is not available due to operational failures
     * @param <T> The type of data in the response, if available
     * @return An {@link ApiOperation} to track progress and provide
     *         a means to cancel the asynchronous operation
     */
    @Nullable
    <T> GraphQLOperation<T> query(
            @NonNull GraphQLRequest<T> graphQlRequest,
            @NonNull Consumer<GraphQLResponse<Iterable<T>>> onResponse,
            @NonNull Consumer<ApiException> onFailure
    );

    /**
     * This is a special helper method for easily calling a list query for
     * all items of the specified model type with no filtering condition.
     *
     * Perform a GraphQL query against a configured GraphQL endpoint.
     * This operation is asynchronous and may be canceled by calling
     * cancel on the returned operation. The response will be provided
     * to the `onResponse` callback.  If there is data present
     * in the response, it will be cast as the requested class type.
     * @param apiName The name of a configured API
     * @param modelClass The class of the Model we are querying on
     * @param onResponse Invoked when a response is available; may contain errors from endpoint
     * @param onFailure Invoked when a response is not available due to operational failures
     * @param <T> The type of data in the response, if available. Must extend Model.
     * @return An {@link ApiOperation} to track progress and provide
     *         a means to cancel the asynchronous operation
     */
    @Nullable
    <T extends Model> GraphQLOperation<T> query(
            @NonNull String apiName,
            @NonNull Class<T> modelClass,
            @NonNull Consumer<GraphQLResponse<Iterable<T>>> onResponse,
            @NonNull Consumer<ApiException> onFailure
    );

    /**
     * This is a special helper method for easily performing GET Queries
     * on Model objects which are autogenerated from your schema.
     *
     * Perform a GraphQL query against a configured GraphQL endpoint.
     * This operation is asynchronous and may be canceled by calling
     * cancel on the returned operation. The response will be provided to
     * the `onResponse` callback.  If there is data
     * present in the response, it will be cast as the requested class type.
     * @param apiName The name of a configured API
     * @param modelClass The class of the Model we are querying on
     * @param objectId The unique ID of the object you want to get
     * @param onResponse Invoked when a response is available; may contain errors from endpoint
     * @param onFailure Invoked when a response is not available due to operational failures
     * @param <T> The type of data in the response, if available. Must extend Model.
     * @return An {@link ApiOperation} to track progress and provide
     *         a means to cancel the asynchronous operation
     */
    @Nullable
    <T extends Model> GraphQLOperation<T> query(
            @NonNull String apiName,
            @NonNull Class<T> modelClass,
            @NonNull String objectId,
            @NonNull Consumer<GraphQLResponse<T>> onResponse,
            @NonNull Consumer<ApiException> onFailure
    );

    /**
     * This is a special helper method for easily performing LIST Queries
     * on Model objects which are autogenerated from your schema.
     *
     * Perform a GraphQL query against a configured GraphQL endpoint.
     * This operation is asynchronous and may be canceled by calling
     * cancel on the returned operation. The response will be provided
     * to the `onResponse` callback. If there is data present
     * in the response, it will be cast as the requested class type.
     * @param apiName The name of a configured API
     * @param modelClass The class of the Model we are querying on
     * @param predicate Filtering conditions for the query
     * @param onResponse Invoked when a response is available; may contain errors from endpoint
     * @param onFailure Invoked when a response is not available due to operational failures
     * @param <T> The type of data in the response, if available. Must extend Model.
     * @return An {@link ApiOperation} to track progress and provide
     *         a means to cancel the asynchronous operation
     */
    @Nullable
    <T extends Model> GraphQLOperation<T> query(
            @NonNull String apiName,
            @NonNull Class<T> modelClass,
            @NonNull QueryPredicate predicate,
            @NonNull Consumer<GraphQLResponse<Iterable<T>>> onResponse,
            @NonNull Consumer<ApiException> onFailure
    );

    /**
     * Perform a GraphQL query against a configured GraphQL endpoint.
     * This operation is asynchronous and may be canceled by calling
     * cancel on the returned operation. The response will be provided
     * to the `onResponse` callback.  If there is data present
     * in the response, it will be cast as the requested class type.
     * @param apiName The name of a configured API
     * @param graphQlRequest Wrapper for request details
     * @param onResponse Invoked when a response is available; may contain errors from endpoint
     * @param onFailure Invoked when a response is not available due to operational failures
     * @param <T> The type of data in the response, if available
     * @return An {@link ApiOperation} to track progress and provide
     *         a means to cancel the asynchronous operation
     */
    @Nullable
    <T> GraphQLOperation<T> query(
            @NonNull String apiName,
            @NonNull GraphQLRequest<T> graphQlRequest,
            @NonNull Consumer<GraphQLResponse<Iterable<T>>> onResponse,
            @NonNull Consumer<ApiException> onFailure
    );

    /**
     * This is a special helper method for easily performing Mutations
     * on Model objects which are autogenerated from your schema.
     *
     * Perform a GraphQL mutation against a configured GraphQL endpoint.
     * This operation is asynchronous and may be canceled by calling
     * cancel on the returned operation. The response will be provided
     * to the `onResponse` callback, and via Hub.  If there is data
     * present in the response, it will be cast as the requested class
     * type. Requires that only one API is configured in your
     * `amplifyconfiguration.json`. Otherwise, emits an ApiException to
     * the provided `onFailure` callback.
     *
     * @param model An instance of the Model with the values to mutate
     * @param mutationType  What type of mutation to perform (e.g. Create, Update, Delete)
     * @param onResponse Invoked when a response is available; may contain errors from endpoint
     * @param onFailure Invoked when a response is not available due to operational failures
     * @param <T> The type of data in the response, if available. Must extend Model.
     * @return An {@link ApiOperation} to track progress and provide
     *         a means to cancel the asynchronous operation
     */
    @Nullable
    <T extends Model> GraphQLOperation<T> mutate(
            @NonNull T model,
            @NonNull MutationType mutationType,
            @NonNull Consumer<GraphQLResponse<T>> onResponse,
            @NonNull Consumer<ApiException> onFailure
    );

    /**
     * This is a special helper method for easily performing Mutations
     * on Model objects which are autogenerated from your schema.
     *
     * Perform a GraphQL mutation against a configured GraphQL endpoint.
     * This operation is asynchronous and may be canceled by calling
     * cancel on the returned operation. The response will be provided
     * to the response `onResponse` callback.  If there is data
     * present in the response, it will be cast as the requested class
     * type. Requires that only one API is configured in your
     * `amplifyconfiguration.json`. Otherwise, emits an ApiException to
     * the provided `onFailure` callback.
     *
     * @param model An instance of the Model with the values to mutate
     * @param predicate Conditions on the current data to determine whether to go through
     *                  with an UPDATE or DELETE operation
     * @param mutationType  What type of mutation to perform (e.g. Create, Update, Delete)
     * @param onResponse Invoked when a response is available; may contain errors from endpoint
     * @param onFailure Invoked when a response is not available due to operational failures
     * @param <T> The type of data in the response, if available. Must extend Model.
     * @return An {@link ApiOperation} to track progress and provide
     *         a means to cancel the asynchronous operation
     */
    @Nullable
    <T extends Model> GraphQLOperation<T> mutate(
            @NonNull T model,
            @NonNull QueryPredicate predicate,
            @NonNull MutationType mutationType,
            @NonNull Consumer<GraphQLResponse<T>> onResponse,
            @NonNull Consumer<ApiException> onFailure
    );

    /**
     * Perform a GraphQL mutation against a configured GraphQL endpoint.
     * This operation is asynchronous and may be canceled by calling
     * cancel on the returned operation. The response will be provided
     * to the `onResponse` callback.  If there is data
     * present in the response, it will be cast as the requested class
     * type. Requires that only one API is configured in your
     * `amplifyconfiguration.json`. Otherwise, emits an ApiException to
     * the provided `onFailure` callback.
     *
     * @param graphQlRequest Wrapper for request details
     * @param onResponse Invoked when a response is available; may contain errors from endpoint
     * @param onFailure Invoked when a response is not available due to operational failures
     * @param <T> The type of data in the response, if available
     * @return An {@link ApiOperation} to track progress and provide
     *         a means to cancel the asynchronous operation
     */
    @Nullable
    <T> GraphQLOperation<T> mutate(
            @NonNull GraphQLRequest<T> graphQlRequest,
            @NonNull Consumer<GraphQLResponse<T>> onResponse,
            @NonNull Consumer<ApiException> onFailure
    );

    /**
     * This is a special helper method for easily performing Mutations
     * on Model objects which are autogenerated from your schema.
     *
     * Perform a GraphQL mutation against a configured GraphQL endpoint.
     * This operation is asynchronous and may be canceled by calling
     * cancel on the returned operation. The response will be provided
     * to the `onResponse` callback. If there is data
     * present in the response, it will be cast as the requested class
     * type.
     *
     * @param apiName The name of a configured API
     * @param model An instance of the Model with the values to mutate
     * @param mutationType  What type of mutation to perform (e.g. Create, Update, Delete)
     * @param onResponse Invoked when a response is available; may contain errors from endpoint
     * @param onFailure Invoked when a response is not available due to operational failures
     * @param <T> The type of data in the response, if available. Must extend Model.
     * @return An {@link ApiOperation} to track progress and provide
     *         a means to cancel the asynchronous operation
     */
    @Nullable
    <T extends Model> GraphQLOperation<T> mutate(
            @NonNull String apiName,
            @NonNull T model,
            @NonNull MutationType mutationType,
            @NonNull Consumer<GraphQLResponse<T>> onResponse,
            @NonNull Consumer<ApiException> onFailure
    );

    /**
     * This is a special helper method for easily performing Mutations
     * on Model objects which are autogenerated from your schema.
     *
     * Perform a GraphQL mutation against a configured GraphQL endpoint.
     * This operation is asynchronous and may be canceled by calling
     * cancel on the returned operation. The response will be provided
     * to the `onResponse` callback.  If there is data
     * present in the response, it will be cast as the requested class
     * type.
     *
     * @param apiName The name of a configured API
     * @param model An instance of the Model with the values to mutate
     * @param predicate Conditions on the current data to determine whether to go through
     *                  with an UPDATE or DELETE operation
     * @param mutationType  What type of mutation to perform (e.g. Create, Update, Delete)
     * @param onResponse Invoked when a response is available; may contain errors from endpoint
     * @param onFailure Invoked when a response is not available due to operational failures
     * @param <T> The type of data in the response, if available. Must extend Model.
     * @return An {@link ApiOperation} to track progress and provide
     *         a means to cancel the asynchronous operation
     */
    @Nullable
    <T extends Model> GraphQLOperation<T> mutate(
            @NonNull String apiName,
            @NonNull T model,
            @NonNull QueryPredicate predicate,
            @NonNull MutationType mutationType,
            @NonNull Consumer<GraphQLResponse<T>> onResponse,
            @NonNull Consumer<ApiException> onFailure
    );

    /**
     * Perform a GraphQL mutation against a configured GraphQL endpoint.
     * This operation is asynchronous and may be canceled by calling
     * cancel on the returned operation. The response will be provided
     * to the `onResponse` callback. If there is data
     * present in the response, it will be cast as the requested class type.
     *
     * @param apiName The name of a configured API
     * @param graphQlRequest Wrapper for request details
     * @param onResponse Invoked when a response is available; may contain errors from endpoint
     * @param onFailure Invoked when a response is not available due to operational failures
     * @param <T> The type of data in the response, if available
     * @return An {@link ApiOperation} to track progress and provide
     *         a means to cancel the asynchronous operation
     */
    @Nullable
    <T> GraphQLOperation<T> mutate(
            @NonNull String apiName,
            @NonNull GraphQLRequest<T> graphQlRequest,
            @NonNull Consumer<GraphQLResponse<T>> onResponse,
            @NonNull Consumer<ApiException> onFailure
    );

    /**
     * This is a special helper method for easily subscribing to events
     * on Model objects which are autogenerated from your schema.
     *
     * Initiates a GraphQL subscription against a configured GraphQL
     * endpoint. The operation is on-going and emits a stream of
     * {@link GraphQLResponse}s to the provided `onNextResponse` callback.
     * The subscription may be canceled by calling {@link GraphQLOperation#cancel()} on the
     * returned object.
     *
     * Requires that only one API is configured in your `amplifyconfiguration.json`.
     * Otherwise, emits an ApiException to the provided `onSubscriptionFailure` callback.
     *
     * @param modelClass The class of the Model we are subscribing to
     * @param subscriptionType
     *        The type of events for which notifications are requested
     *        (e.g. OnCreate, OnUpdate, OnDelete)
     * @param onSubscriptionEstablished
     *        Called when a subscription has been established over the network
     * @param onNextResponse
     *        Consumes a stream of responses on the subscription. This may be
     *        called 0..n times per subscription.
     * @param onSubscriptionFailure
     *        Called when the subscription stream terminates with a failure.
     *        Note that items passed via onNextResponse may themselves contain
     *        errors in the response from the endpoint, but the subscription
     *        may continue to be active even after these are received.
     *        This is a terminal event following 0..n many calls to onNextResponse.
     * @param onSubscriptionComplete
     *        Called when a subscription has ended gracefully (without failure).
     *        This is a terminal event following 0..n many calls to onNextResponse.
     * @param <T> The type of data expected in the subscription stream. Must extend Model.
     * @return A {@link GraphQLOperation} representing this ongoing subscription
     */
    @Nullable
    <T extends Model> GraphQLOperation<T> subscribe(
            @NonNull Class<T> modelClass,
            @NonNull SubscriptionType subscriptionType,
            @NonNull Consumer<String> onSubscriptionEstablished,
            @NonNull Consumer<GraphQLResponse<T>> onNextResponse,
            @NonNull Consumer<ApiException> onSubscriptionFailure,
            @NonNull Action onSubscriptionComplete
    );

    /**
     * Initiates a GraphQL subscription against a configured GraphQL
     * endpoint. The operation is on-going and emits a stream of
     * {@link GraphQLResponse}s to the provided `onNextResponse` callback.
     * The subscription may be canceled by calling {@link GraphQLOperation#cancel()} on
     * the returned object.
     *
     * Requires that only one API is configured in your
     * `amplifyconfiguration.json`. Otherwise, emits an ApiException to
     * the provided `onSubscriptionFailure` callback.
     *
     * @param graphQlRequest Wrapper for request details
     * @param onSubscriptionEstablished
     *        Called when a subscription has been established over the network
     * @param onNextResponse
     *        Consumes a stream of responses on the subscription. This may be
     *        called 0..n times per subscription.
     * @param onSubscriptionFailure
     *        Called when the subscription stream terminates with a failure.
     *        Note that items passed via onNextResponse may themselves contain
     *        errors in the response from the endpoint, but the subscription
     *        may continue to be active even after these are received.
     *        This is a terminal event following 0..n many calls to onNextResponse.
     * @param onSubscriptionComplete
     *        Called when a subscription has ended gracefully (without failure).
     *        This is a terminal event following 0..n many calls to onNextResponse.
     * @param <T> The type of data expected in the subscription stream
     * @return An {@link GraphQLOperation} representing this ongoing subscription
     */
    @Nullable
    <T> GraphQLOperation<T> subscribe(
            @NonNull GraphQLRequest<T> graphQlRequest,
            @NonNull Consumer<String> onSubscriptionEstablished,
            @NonNull Consumer<GraphQLResponse<T>> onNextResponse,
            @NonNull Consumer<ApiException> onSubscriptionFailure,
            @NonNull Action onSubscriptionComplete
    );

    /**
     * This is a special helper method for easily subscribing to events
     * on Model objects which are autogenerated from your schema.
     *
     * Initiates a GraphQL subscription against a configured GraphQL
     * endpoint. The operation is on-going and emits a stream of
     * {@link GraphQLResponse}s to the provided `onNextResponse` callback.
     * The subscription may be canceled by calling {@link GraphQLOperation#cancel()}
     * on the returned object.
     *
     * @param apiName The name of a previously configured GraphQL API
     * @param modelClass The class of the Model we are subscribing to
     * @param subscriptionType
     *        The type of events for which notifications are requested
     *        (e.g. OnCreate, OnUpdate, OnDelete)
     * @param onSubscriptionEstablished
     *        Called when a subscription has been established over the network
     * @param onNextResponse
     *        Consumes a stream of responses on the subscription. This may be
     *        called 0..n times per subscription.
     * @param onSubscriptionFailure
     *        Called when the subscription stream terminates with a failure.
     *        Note that items passed via onNextResponse may themselves contain
     *        errors in the response from the endpoint, but the subscription
     *        may continue to be active even after these are received.
     *        This is a terminal event following 0..n many calls to onNextResponse.
     * @param onSubscriptionComplete
     *        Called when a subscription has ended gracefully (without failure).
     *        This is a terminal event following 0..n many calls to onNextResponse.
     * @param <T> The type of data expected in the subscription stream. Must extend Model.
     * @return An {@link GraphQLOperation} representing this ongoing subscription
     */
    @Nullable
    <T extends Model> GraphQLOperation<T> subscribe(
            @NonNull String apiName,
            @NonNull Class<T> modelClass,
            @NonNull SubscriptionType subscriptionType,
            @NonNull Consumer<String> onSubscriptionEstablished,
            @NonNull Consumer<GraphQLResponse<T>> onNextResponse,
            @NonNull Consumer<ApiException> onSubscriptionFailure,
            @NonNull Action onSubscriptionComplete
    );

    /**
     * Initiates a GraphQL subscription against a configured GraphQL
     * endpoint. The operation is on-going and emits a stream of
     * {@link GraphQLResponse}s to the provided `onNextResponse` callback.
     * The subscription may be canceled by calling {@link GraphQLOperation#cancel()}
     * on the returned object.
     *
     * @param apiName The name of a configured API
     * @param graphQlRequest Wrapper for request details
     * @param onSubscriptionEstablished
     *        Called when a subscription has been established over the network
     * @param onNextResponse
     *        Consumes a stream of responses on the subscription. This may be
     *        called 0..n times per subscription.
     * @param onSubscriptionFailure
     *        Called when the subscription stream terminates with a failure.
     *        Note that items passed via onNextResponse may themselves contain
     *        errors in the response from the endpoint, but the subscription
     *        may continue to be active even after these are received.
     *        This is a terminal event following 0..n many calls to onNextResponse.
     * @param onSubscriptionComplete
     *        Called when a subscription has ended gracefully (without failure).
     *        This is a terminal event following 0..n many calls to onNextResponse.
     * @param <T> The type of data expected in the subscription stream
     * @return An {@link GraphQLOperation} representing this ongoing subscription
     */
    @Nullable
    <T> GraphQLOperation<T> subscribe(
            @NonNull String apiName,
            @NonNull GraphQLRequest<T> graphQlRequest,
            @NonNull Consumer<String> onSubscriptionEstablished,
            @NonNull Consumer<GraphQLResponse<T>> onNextResponse,
            @NonNull Consumer<ApiException> onSubscriptionFailure,
            @NonNull Action onSubscriptionComplete
    );
}
