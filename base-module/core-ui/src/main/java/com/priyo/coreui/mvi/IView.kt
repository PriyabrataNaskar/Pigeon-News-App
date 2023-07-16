package com.priyo.coreui.mvi

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 * Base View Interface to be implemented by every activity
 */

interface IView<I : IIntent, S : IState, E : IEffect> {
    fun observeUiState(state: S)
    fun observeUiEffect(effect: E)
    fun triggerEvent(onEvent: I)
}

interface MVIView<I : IIntent, S : IState, E : IEffect> : IView<I, S, E> {
    fun observerInView(
        lifecycleOwner: LifecycleOwner,
        uiState: Flow<S>,
        uiEffect: Flow<E>,
    ) {
        lifecycleOwner.lifecycleScope.launch {
            launch {
                uiState.flowWithLifecycle(lifecycleOwner.lifecycle)
                    .collect { observeUiState(it) }
            }
            launch {
                uiEffect.flowWithLifecycle(lifecycleOwner.lifecycle)
                    .collect { observeUiEffect(it) }
            }
        }
    }
}
