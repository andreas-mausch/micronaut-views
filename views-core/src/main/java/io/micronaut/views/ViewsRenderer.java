/*
 * Copyright 2017-2020 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micronaut.views;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.order.Ordered;

/**
 * Interface to be implemented by View Engines implementations.
 * Implemented by either {@link WritableViewsRender} or {@link ReactiveViewsRenderer}
 * @param <T> The model type
 * @author Sergio del Amo
 * @since 1.0
 */
public interface ViewsRenderer<T> extends Ordered {

    /**
     * @param viewName view name to be rendered
     * @return true if a template can be found for the supplied view name.
     */
    boolean canRender(@NonNull String viewName);
}
