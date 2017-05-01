package ru.sberbank.learning.rates;

import android.content.Context;
import android.os.AsyncTask;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;

import ru.sberbank.learning.rates.networking.CurrenciesList;
import ru.sberbank.learning.rates.storage.CurrenciesStorage;

/**
 * Created by DevCom on 29.04.2017.
 */

public class CurrenciesLoader extends AsyncTask<Void, Void, CurrenciesList> {
    public static final String CBR = "http://www.cbr.ru/scripts/XML_daily.asp";

    private WeakReference<IsAsyncFinish> mContext;
    private WeakReference<CurrenciesStorage> mStorage;

    public CurrenciesLoader(IsAsyncFinish context, CurrenciesStorage currenciesStorage) {
        mContext = new WeakReference<>(context);
        mStorage = new WeakReference<>(currenciesStorage);
    }

    @Override
    protected CurrenciesList doInBackground(Void... params) {
        CurrenciesList list;
        try {
            URL url = new URL(CBR);
            list = CurrenciesList.readFromStream(url.openStream());
            return list;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(CurrenciesList currenciesList) {
        CurrenciesStorage storage = mStorage.get();
        IsAsyncFinish application = mContext.get();

        if(storage!=null) {
            storage.setLoadedList(currenciesList);
        }
        if(application!=null) {
            application.onAsyncFinish();
        }

        super.onPostExecute(currenciesList);
    }
}
