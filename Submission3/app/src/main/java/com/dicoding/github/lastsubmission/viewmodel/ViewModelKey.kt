package com.dicoding.github.lastsubmission.viewmodel

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@MustBeDocumented
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)

@Retention(AnnotationRetention.SOURCE)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)