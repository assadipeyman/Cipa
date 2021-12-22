package com.cipa.cipamerchant.data.businessData

import com.cipa.cipamerchant.data.ServiceData.Credit
import com.cipa.cipamerchant.data.ServiceData.Supplier

class BSupplier(
    createDate: String,
    creatorId: Int?,
    id: Int,
    isDeleted: Boolean,
    name: String,
    updateDate: String,
    var credit:Credit?
): Supplier(createDate, creatorId, id, isDeleted, name, updateDate)
