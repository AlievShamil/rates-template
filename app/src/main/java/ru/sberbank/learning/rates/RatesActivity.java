package ru.sberbank.learning.rates;

import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;

import ru.sberbank.learning.rates.storage.CurrenciesStorage;

public class RatesActivity extends Activity implements IsAsyncFinish{

    private ListView mListView;
    private CurrenciesStorage mStorage;
    private CurrenciesLoader mLoader;
    private ValuteAdapter mAdapter;
    private ProgressDialog dialog;
    public static  int dispWidth;
    public static  int dispHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rates);

        Display display = getWindowManager().getDefaultDisplay();
        dispWidth =display.getWidth();
        dispHeight=display.getHeight();

        mListView = (ListView) findViewById(R.id.valute_listView);
        mStorage = ((SimpleApplication)getApplication()).getCurrenciesStorage();

        if(!mStorage.isReady()) {
            mLoader = new CurrenciesLoader(this,mStorage);
            mLoader.execute();
            dialog = ProgressDialog.show(this,"loading","loading currencies",false);
        } else {
            mAdapter = new ValuteAdapter(mStorage);
            mListView.setAdapter(mAdapter);
        }

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               String charCode =  mAdapter.getItem(position).getCharCode();
                Double value = mAdapter.getItem(position).getValue();
                Double nominal = mAdapter.getItem(position).getNominal();
                Intent intent = new Intent(getApplicationContext(),CalculateActivity.class);
                intent.putExtra("charCode",charCode);
                intent.putExtra("value",value);
                intent.putExtra("nominal",nominal);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onDestroy() {
        mLoader.cancel(true);
        super.onDestroy();
    }

    @Override
    public void onAsyncFinish() {
        dialog.dismiss();
        mAdapter = new ValuteAdapter(mStorage);
        mListView.setAdapter(mAdapter);
    }
}
