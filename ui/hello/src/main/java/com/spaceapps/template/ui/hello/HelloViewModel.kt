package com.spaceapps.template.ui.hello

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

private const val URL_PREFIX =
    "https://www.host.playersgetready.com/games/%d0%b3%d1%80%d0%b0-%d0%b4%d0%bb%d1%8f-%d1%89%d0%b8%d1%80%d0%b8%d1%85-%d0%bf%d0%be%d0%b1%d0%b0%d1%87%d0%b5%d0%bd%d1%8c/?id=69181&code="
private const val URL_SUFFIX = "&connect=89011"

class HelloViewModel() : ViewModel() {

    private val pagesData: List<PageData> =
        listOf(
            PageData(R.drawable.making_presents, R.string.making_presents),
            PageData(R.drawable.cuddling_pets, R.string.cuddling_pets),
            PageData(R.drawable.dancing_with_you, R.string.dancing_with_you),
            PageData(R.drawable.having_fun, R.string.having_fun),
            PageData(R.drawable.hugging_you, R.string.hugging_you),
            PageData(R.drawable.kissing_you, R.string.kissing_you),
            PageData(R.drawable.love_your_hair, R.string.love_your_hair),
            PageData(R.drawable.sleeping_with_you, R.string.sleeping_with_you),
            PageData(R.drawable.taking_your_photos, R.string.taking_your_photos),
            PageData(R.drawable.you_need_contact, R.string.you_need_contact),
            PageData(R.drawable.your_presents, R.string.your_presents),
            PageData(R.drawable.traveling_with_you, R.string.traveling_with_you),
            PageData(R.drawable.making_memories, R.string.making_memories),
            PageData(R.drawable.texting_with_you, R.string.texting_with_you),
            PageData(R.drawable.taking_care_of_you, R.string.taking_care_of_you),
            PageData(R.drawable.making_you_happy, R.string.making_you_happy),
            PageData(R.drawable.dreaming_about_future, R.string.dreaming_about_future),
            PageData(R.drawable.celebrating_another_year, R.string.celebrating_another_year),
            PageData(R.drawable.want_to_marry, R.string.want_to_marry),
        )

    private val pendingActions = MutableSharedFlow<HelloAction>()

    private val _uiState = MutableStateFlow(
        value = HelloUiState(
            pagesData = pagesData,
            pagesCount = pagesData.size + 1,
            secretCode = ""
        )
    )
    val uiState = _uiState.asStateFlow().stateIn(
        started = SharingStarted.WhileSubscribed(5),
        initialValue = HelloUiState(),
        scope = viewModelScope,
    )

    private val _uiEvents = MutableSharedFlow<HelloUiEvent>()
    val uiEvents = _uiEvents.asSharedFlow()

    init {
        collectActions()
    }

    private fun collectActions() = viewModelScope.launch {
        pendingActions.collect { action ->
            when (action) {
                HelloAction.ButtonClicked -> onButtonClick()
                is HelloAction.CodeEntered -> onCodeEntered(action.code)
            }
        }
    }

    private fun onCodeEntered(code: String) =
        viewModelScope.launch {
            _uiState.emit(_uiState.value.copy(secretCode = code))
        }

    private fun onButtonClick() =
        viewModelScope.launch {
            val code = uiState.value.secretCode
            if (isCodeValid(code)) {
                _uiEvents.emit(
                    HelloUiEvent.OnButtonClick(URL_PREFIX + code + URL_SUFFIX),
                )
            } else {
                _uiEvents.emit(HelloUiEvent.ShowToast(R.string.looks_there_is_something_wrong))
            }
        }

    private fun isCodeValid(code: String) = code.length == 15
    fun onActionSubmit(action: HelloAction) = viewModelScope.launch {
        pendingActions.emit(action)
    }
}
