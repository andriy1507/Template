package com.spaceapps.template.ui.hello

data class HelloUiState(
    val pagesData: List<PageData> = emptyList(),
    val secretCode: String = "",
    val pagesCount: Int = 0
)
