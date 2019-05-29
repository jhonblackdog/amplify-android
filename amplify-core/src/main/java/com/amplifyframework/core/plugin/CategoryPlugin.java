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

package com.amplifyframework.core.plugin;

/**
 * Interface that identifies the implementation
 * of a category plugin. For example, plugins for
 * categories such as Auth, Analytics, Storage, API.
 */
public interface CategoryPlugin extends Plugin {
    /**
     * Enum that specifies the category.
     */
    Category category = null;

    /**
     * @return the category of the plugin
     */
    Category getCategory();
}
