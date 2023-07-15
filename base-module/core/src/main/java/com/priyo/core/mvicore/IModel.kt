package com.priyo.core.mvicore

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface IModel<S : IState, I : IIntent, E : IEffect> {
    val intents: Channel<I>
    val uiState: StateFlow<S>
    val uiEffect: Flow<E>
    fun handleIntent()
}
