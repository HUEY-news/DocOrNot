package com.houston.docornot.util

object Constants {

    const val ERROR_LOGIN_TEXT = "Неудачная попытка авторизации"
    const val ERROR_BAD_REQUEST_TEXT = "Не верный запрос"
    const val ERROR_UNAUTHORIZED_TEXT = "Пользователь не авторизован"
    const val ERROR_FORBIDDEN_TEXT = "Недостаточно прав для выполнения запроса"
    const val ERROR_NO_INTERNET_TEXT = "Проверьте подключение к интернету"

    const val SUCCESS_STATUS_CODE = 200
    const val BAD_REQUEST_STATUS_CODE = 400
    const val UNAUTHORIZED_STATUS_CODE = 401
    const val FORBIDDEN_STATUS_CODE = 403
    const val NO_INTERNET_STATUS_CODE = -1

    const val SEARCH_DEBOUNCE_DELAY = 500L
    const val CLICK_DEBOUNCE_DELAY = 1000L
}