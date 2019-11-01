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

package com.amplifyframework.storage.operation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.amplifyframework.core.async.AmplifyOperation;
import com.amplifyframework.core.async.Cancelable;
import com.amplifyframework.core.async.Resumable;
import com.amplifyframework.core.category.CategoryType;

/**
 * Base operation type for upload file behavior on the Storage category.
 *
 * @param <R> type of the request object
 */
public abstract class StorageUploadFileOperation<R> extends AmplifyOperation<R> implements Resumable, Cancelable {

    /**
     * Constructs a new AmplifyOperation.
     *
     * @param categoryType            The category in which this operation is
     *                                fulfilling a request
     * @param amplifyOperationRequest The request object of the operation
     */
    public StorageUploadFileOperation(@NonNull CategoryType categoryType, @Nullable R amplifyOperationRequest) {
        super(categoryType, amplifyOperationRequest);
    }
}

