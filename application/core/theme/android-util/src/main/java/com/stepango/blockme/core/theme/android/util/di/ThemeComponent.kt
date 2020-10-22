/*
 * Copyright 2019 vmadalin.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.stepango.blockme.core.theme.android.util.di

import com.stepango.blockme.core.theme.android.util.ThemeUtils
import dagger.Component
import javax.inject.Singleton

/**
 * Theme component for different theme utils dependencies
 *
 * @see Component
 */
@Singleton
@Component(modules = [
    ThemeModule::class
])
interface ThemeComponent {

    /**
     * Provide dependency graph ThemeUtils
     *
     * @return ThemeUtils
     */
    fun themeUtils(): ThemeUtils

    @Component.Factory
    interface Factory {

        fun create(): ThemeComponent
    }
}

interface ThemeComponentProvider {

    val themeComponent: ThemeComponent
}
