package com.hardeepsingh.yelpify.extensions

import com.hardeepsingh.yelpify.model.Business
import com.hardeepsingh.yelpify.model.BusinessDetail

private const val COMMA = ", "
private const val SPACE = " "

/**
 * Generate Address from Business using city, state and zip code
 */
fun Business.generateAddress(): String {
    var address: String
    with(location) { address = display_address.firstOrNull() + COMMA + city + SPACE + state + SPACE + zip_code }
    return address
}

/**
 * Generate Category string from business separated by comma
 */
fun Business.generateCategory() = categories.joinToString(COMMA) { it.title }


/**
 * Generate Address from BusinessDetail using city, state and zip code
 */
fun BusinessDetail.generateAddress(): String {
    var address: String
    with(location) { address = display_address.firstOrNull() + COMMA + city + SPACE + state + SPACE + zip_code }
    return address
}

/**
 * Generate Category string from BusinessDetail separated by comma
 */
fun BusinessDetail.generateCategory() = categories.joinToString(COMMA) { it.title }
