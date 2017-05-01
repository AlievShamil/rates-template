package ru.sberbank.learning.rates;

import android.app.Application;
import ru.sberbank.learning.rates.storage.CurrenciesStorage;

/**
 * Created by DevCom on 29.04.2017.
 */

public class SimpleApplication extends Application {
    private CurrenciesStorage currenciesStorage = new CurrenciesStorage();

    public CurrenciesStorage getCurrenciesStorage() {
        return currenciesStorage;
    }
}
