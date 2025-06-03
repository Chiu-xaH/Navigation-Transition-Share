package com.xah.sample.logic.model.ui


sealed class HomeScreenRoute(val route: String) {
    data object TwoColumnSampleScreen : HomeScreenRoute("/two_column_sample")
    data object SingleColumnSampleScreen : HomeScreenRoute("/single_column_sample")
    data object IconSampleScreen : HomeScreenRoute("/icon_sample")

}