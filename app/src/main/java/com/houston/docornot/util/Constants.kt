package com.houston.docornot.util

object Constants {

    const val ERROR_UNAUTHORIZED_TEXT = "Пользователь не авторизован"
    const val ERROR_FORBIDDEN_TEXT = "Неверный запрос"
    const val ERROR_INTERNET_TEXT = "Проверьте подключение к интернету"

    const val SUCCESS_STATUS_CODE = 200
    const val UNAUTHORIZED_STATUS_CODE = 401
    const val FORBIDDEN_STATUS_CODE = 403
    const val NO_INTERNET_STATUS_CODE = -1

    const val SEARCH_DEBOUNCE_DELAY = 300L
    const val CLICK_DEBOUNCE_DELAY = 1000L
}
