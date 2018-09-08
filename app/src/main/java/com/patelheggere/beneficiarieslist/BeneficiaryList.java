package com.patelheggere.beneficiarieslist;

import android.app.Application;

public class BeneficiaryList extends Application {
    private static BeneficiaryList mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        // ApiClient.intialise();
       /* if(isDeve()) {
            ApiClient.setBaseUrl(AppConstants.appBaseUrlDev);
        }
        else
        {
            ApiClient.setBaseUrl(AppConstants.appBaseUrl);

        }*/

    }

    public static synchronized BeneficiaryList getInstance() {
        return mInstance;
    }
}