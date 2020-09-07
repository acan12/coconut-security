package app.beelabs.com.coconut.model.api.response

import app.beelabs.com.codebase.base.response.BaseResponse
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class HistorySummaryResponse : BaseResponse() {

    var achievementDay: Int? = 3
}