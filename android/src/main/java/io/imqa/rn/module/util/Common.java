package io.imqa.rn.module.util;

import io.imqa.core.CoreContext;

public class Common {

    public static String getTxId(){
        return CoreContext.getInstance() != null ? CoreContext.getInstance().getTxId() : "";
    }

}
